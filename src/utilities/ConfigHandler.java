package utilities;

import java.io.IOException;
import java.util.Properties;

//class that handles configuration settings for simulation builder
/**
 * 
 * @author Juan
 *
 */
public class ConfigHandler
{
	private Properties simProps;
	
	public ConfigHandler()
	{
		simProps = new Properties();
		try {
			simProps.load(getClass().getClassLoader().getResourceAsStream("sim.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public String getBoundsType()
	{
		return simProps.getProperty("gridBounds");
	}

	public String getShapeType()
	{
		return simProps.getProperty("cellShape");
	}
	
	public boolean getLineSetting()
	{
		return simProps.getProperty("cellOutline").equals("on");
	}

	public String getGridBuilderType()
	{
		return simProps.getProperty("gridBuilderType");
	}
}
