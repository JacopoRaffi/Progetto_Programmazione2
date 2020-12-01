package Social;

public class ViolatedInvariantException extends RuntimeException{
    public ViolatedInvariantException(String errorMessage){
        super(errorMessage);
    }
}
