package flabs.functional;


/**
 * The Either type represents a value of one of two possible types (a disjoint union). 
 * The Either type is often used as an alternative to scala.Option where Left represents failure (by convention) and Right is akin to Some. 
 */
public class Either<A,B> {
    
    private final A left;
    private final B right;

    private Either(A left,  B right) {
        Preconditions.checkArgument((left != null && right == null) || (left == null && right != null)); 
        this.left = left;
        this.right = right;
    }
    
    /** Construct a left value of either.*/
    public static <A,B> Either<A,B> left(A value) {
        return new Either<A,B>(value, null);
    }
    
    /** Construct a left value of either.*/
    public static <A,B> Either<A,B> right(B value) {
        return new Either<A,B>(null, value);
    }
    
    
    /**Returns true if this either is a left, false otherwise.*/
    public boolean isLeft() {
        return left != null;
        
    }
    
    /**Returns true if this either is a right, false otherwise.*/
    public boolean  isRight() {
        return right != null;
    }

    public A getLeft() {
        return left;
    }
    
    public B getRight() {
        return right;
    }
}
