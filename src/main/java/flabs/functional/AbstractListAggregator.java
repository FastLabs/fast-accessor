package flabs.functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractListAggregator<I> implements Aggregable<I, List<I>> {
    protected final Map<Integer, List<I>> aggregated = new HashMap<>();
    protected final List<I> unique = new ArrayList<>();
    @Override
    public void aggregate(I... elements) {
        for (I source : elements) {
            final Integer hash = getHash(source);
            List<I> existing = aggregated.get(hash);
            if (existing == null) {
                existing = new ArrayList<>();
                existing.add(source);
                unique.add(source);
                aggregated.put(hash, existing);
            } else {
                existing.add(source);
            }
        }
    }

    protected abstract Integer getHash (I element);
    protected abstract void apply(I source, I subject);

    @Override
    public void merge() {
        merge(unique);
    }

    public void merge(List<I> result) {
        for (I queried : result) {
            final Integer hash = getHash(queried);
            final List<I> subjects = aggregated.get(hash);
            if (subjects != null) {
                beforeMerge(queried);
                for (I subject : subjects) {
                    apply(queried, subject);
                }
            }
        }
    }

    /**
     * Invoked before merging the original entity with the one returned from the database
     * there are some use cases when we want to lookup other fields for the returned
     * @param returned
     */
    protected void beforeMerge(I returned) {

    }

    public List<I> getUnique() {
        return unique;
    }

    @Override
    public List<I> aggregated() {
        final ArrayList<I> x = new ArrayList<>(200); //TODO: somehow accumulate the count of the results
        for(List<I> entry: aggregated.values()) {
            x.addAll(entry);
        }
        return x;
    }
}
