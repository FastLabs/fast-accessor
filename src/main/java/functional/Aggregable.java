package functional;


public interface Aggregable <I, O>{
    void aggregate(I... elements);
    void merge();
    O aggregated();
}
