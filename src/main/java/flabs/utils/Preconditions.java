package flabs.utils;

/**
 * Copy of some methods from Guava in order not to introduce dependency on it here 
 */
public class Preconditions {
    
    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression) {
      if (!expression) {
        throw new IllegalArgumentException();
      }
    }

}
