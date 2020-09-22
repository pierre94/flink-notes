package cn.bear2.flink.demo.io;

import cn.bear2.flink.demo.io.source.heap.HeapMetrics;
import cn.bear2.flink.demo.io.source.heap.HeapMonitorSource;

import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;

import org.apache.flink.core.fs.Path;


public class DemoRunner {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String hdfsPath = "hdfs:///tmp/flink-heap-stats";
        boolean sink2hdfs = false;
        env.enableCheckpointing(10_000);
        env.setParallelism(1);

        DataStream<HeapMetrics> source = env.addSource(new HeapMonitorSource(100)).name("heap source");
        source.map(HeapMetrics::toString).print();

        if (sink2hdfs) {
            StreamingFileSink<String> sfs = StreamingFileSink
                    .forRowFormat(new Path(hdfsPath), new SimpleStringEncoder<String>("UTF-8"))
                    .build();
            source.map(data -> data.toString()).addSink(sfs).name("hdfs sink");
        }

        env.execute();
    }
}
