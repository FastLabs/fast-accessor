package functional;


public interface ActionRule<P, C> {
    ProcessingAction apply(P subject, C context);
}
