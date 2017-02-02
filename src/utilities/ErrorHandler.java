package utilities;


/**
 * Errors that cannot be dealt with
 * source: https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
 */
public class ErrorHandler extends Throwable {
    private String output;
    
    public ErrorHandler(String output) {
        this.output = output;
    }
    
}
