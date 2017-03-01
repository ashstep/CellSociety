package utilities;

import org.xml.sax.SAXException;

import jdk.internal.org.xml.sax.SAXParseException;

public class XMLExceptions extends Exception{

	/**
	 * default Serial Version ID added
	 * source: https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
	 */
	private static final long serialVersionUID = 1L;

	public XMLExceptions(String message){
		super(message);
	}


	private String getParseExceptionInfo(SAXParseException exception) {
		String systemId = exception.getSystemId();
		
		String message = exception.getMessage();

		if (systemId == null) {
			systemId = "null";
		}

		String errorInfo = "Error at line =" + exception.getLineNumber() + ": " + message;
		return errorInfo;
	}
	public void error(SAXParseException exception) throws SAXException {
		String message = "Error: " + getParseExceptionInfo(exception);
		throw new SAXException(message);
	}
}
