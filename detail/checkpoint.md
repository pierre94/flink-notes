# checkpoint笔记

## checkpoint的建议

- Checkpoint 间隔不要太短
    - 过短的间对于底层分布式文件系统而言，会带来很大的压力。
    - Flink 作业处理 record 与执行 checkpoint 存在互斥锁，过于频繁的checkpoint，可能会影响整体的性能。
- 合理设置超时时间