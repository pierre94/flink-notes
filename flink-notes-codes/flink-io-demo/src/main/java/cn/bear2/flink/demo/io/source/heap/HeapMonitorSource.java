package cn.bear2.flink.demo.io.source.heap;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.net.InetAddress;

// study from https://github.com/cloudera/flink-tutorials/blob/master/flink-simple-tutorial/src/main/java/com/cloudera/streaming/examples/flink/HeapMonitorSource.java

public class HeapMonitorSource extends RichParallelSourceFunction<HeapMetrics>{
    private static final Logger LOG = LoggerFactory.getLogger(HeapMonitorSource.class);

    private final long sleepMillis;
    private volatile boolean running = true;

    public HeapMonitorSource(long sleepMillis) {
        this.sleepMillis = sleepMillis;
    }

    @Override
    public void run(SourceContext ctx) throws Exception {
        LOG.info("starting HeapMonitorSource");
        int subtaskIndex = this.getRuntimeContext().getIndexOfThisSubtask();
        String hostname = InetAddress.getLocalHost().getHostName();

        while (running) {
            Thread.sleep(sleepMillis);
            for (MemoryPoolMXBean mpBean : ManagementFactory.getMemoryPoolMXBeans()) {
                if (mpBean.getType() == MemoryType.HEAP) {
                    MemoryUsage memoryUsage = mpBean.getUsage();
                    long used = memoryUsage.getUsed();
                    long max = memoryUsage.getMax();

                    synchronized (ctx.getCheckpointLock()) {
                        ctx.collect(new HeapMetrics(mpBean.getName(), used, max, (double) used / max, subtaskIndex, hostname));
                    }
                }
            }
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
