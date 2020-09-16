package cn.bear2.flink.demo.io;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DemoRunner {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.addSource(new MyDataSource(), "custom source").returns(Types.STRING).name("source").print();

//        ds.addSink(new SinkFunction<Tuple2<String, Integer>>() {
//            @Override
//            public void invoke(Tuple2<String, Integer> value) throws Exception {
//                System.out.println(String.format("Get:\t(%s,%d)", value.f0, value.f1));
//            }
//        });
        env.execute();
    }
}
