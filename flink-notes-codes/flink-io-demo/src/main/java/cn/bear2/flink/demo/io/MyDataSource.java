package cn.bear2.flink.demo.io;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;

import java.util.Random;

public class MyDataSource implements SourceFunction {
    boolean running = true;

    @Override
    public void run(SourceContext ctx) throws Exception {
        Random random = new Random(System.currentTimeMillis());

        while (running) {
            Thread.sleep(500);
            String key = "info-" + (char) ('A' + random.nextInt(3));
            ctx.collect(key);
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
