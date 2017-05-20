package bike;
public class CanCountException extends Exception{

    public CanCountException() {
        super("Your CP is not enough,Plese select item again");
    }

    public CanCountException(String msg) {
        super(msg);
    }
    
}
