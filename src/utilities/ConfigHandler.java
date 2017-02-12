package utilities;

import java.io.IOException;
import java.util.Properties;

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

}
