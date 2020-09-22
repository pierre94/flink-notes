package cn.bear2.flink.demo.io.source.heap;

import java.util.Objects;

public class HeapMetrics {

    public static final String OLD_GEN = "PS Old Gen";
    public static final String EDEN = "PS Eden Space";
    public static final String SURVIVOR = "PS Survivor Space";

    public String area;
    /**
     * Bytes used for the old generation of the heap.
     */
    public long used;
    /**
     * Maximum bytes allocated for the old generation of the heap.
     */
    public long max;
    /**
     * Ratio of used out of the maximum old generation heap.
     */
    public double ratio;

    /**
     * ID of the Flink job.
     */
    public Integer jobId;

    /**
     * Host the Flink job is running on.
     */
    public String hostname;

    public HeapMetrics() {
    }

    public HeapMetrics(String area, long used, long max, double ratio, Integer jobId, String hostname) {
        this.area = area;
        this.used = used;
        this.max = max;
        this.ratio = ratio;
        this.jobId = jobId;
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "HeapMetrics{" +
                "area=" + area +
                ", used=" + used +
                ", max=" + max +
                ", ratio=" + ratio +
                ", jobId=" + jobId +
                ", hostname='" + hostname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HeapMetrics heapMetrics = (HeapMetrics) o;
        return used == heapMetrics.used &&
                max == heapMetrics.max &&
                Double.compare(heapMetrics.ratio, ratio) == 0 &&
                Objects.equals(area, heapMetrics.area) &&
                Objects.equals(jobId, heapMetrics.jobId) &&
                Objects.equals(hostname, heapMetrics.hostname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, used, max, ratio, jobId, hostname);
    }
}