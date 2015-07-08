package flabs.accessor;


public interface LookupService <I, O>   {
    O find(final I input);
}
