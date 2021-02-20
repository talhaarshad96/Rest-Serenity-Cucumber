package Utilities;
//package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader
{
    public static Properties LoadProperties()
    {
        try
        {
            InputStream inStream = PropertyReader.class.getClassLoader().getResourceAsStream("Config/config.properties");

            Properties prop = new Properties();
            prop.load(inStream);
            return prop;
        }
        catch (IOException e)
        {
            System.out.println("File not found");
            return null;
        }
    }
}


