package utilities;

import org.xml.sax.SAXException;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

 jdk.internal.org.xml.sax.SAXParseException;

public class XMLExceptions extends Exception{

	/**
	 * default Serial Version ID added
	 * source: https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
	 */
	private static final long serialVersionUID = 1L;

	//main should extend runtime exception
	public XMLExceptions(String message){
		super(message);
	}


	private String getParseExceptionInfo(ParseException exception) {
		String systemId = exception.getSystemId();
		
		String message = exception.getMessage();

		if (systemId == null) {
			systemId = "null";
		}

		String errorInfo = "SystemID=" + systemId + " Line =" + exception.getLineNumber() + ": " + message;
		return errorInfo;
	}



	public void error(SAXParseException exception) throws SAXException {
		String message = "Error: " + getParseExceptionInfo(exception);
		throw new SAXException(message);
	}


    
    
    
    


}
