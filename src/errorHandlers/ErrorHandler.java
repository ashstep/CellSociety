package utilities;


/**
 * Errors that cannot be dealt with
 * source: https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
 */
public class ErrorHandler extends Throwable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String output;
    
    public ErrorHandler(String message) {
        this.output = message;
    }
    
}
