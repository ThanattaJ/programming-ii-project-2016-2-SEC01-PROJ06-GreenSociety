package bike;
public class SelectItemException extends Exception{

    public SelectItemException() {
        super("Please Select Item");
    }
    
    public SelectItemException(String msg) {
        super(msg);
    }
}
