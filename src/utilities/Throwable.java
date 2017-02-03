package utilities;

import java.io.Serializable;

public class Throwable extends Object implements Serializable {
	
	/**
	 * Default serial number added
	 */
	private static final long serialVersionUID = 1L;

	public Throwable(){}

	public Throwable(String message){}
	
	public Throwable(String message,
	         Throwable cause){}
	
	public String getMessage(){
		return null;
	}


}
