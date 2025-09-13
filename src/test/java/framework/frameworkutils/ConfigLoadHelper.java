package framework.frameworkutils;

import framework.projectconstants.DriverType;
import framework.projectconstants.EnvTypes;

import java.util.Properties;

public class ConfigLoadHelper {
    private final Properties properties;
    private static ConfigLoadHelper configLoadHelper;

    public ConfigLoadHelper() {
        String env = System.getProperty("env",String.valueOf(EnvTypes.STAGING));
        switch (EnvTypes.valueOf(env)){
            case STAGING ->
                properties = PropertyHelper.propertyLoader("src/test/resources/stg_config.properties");
            case PRODUCTION ->
                    properties = PropertyHelper.propertyLoader("src/test/resources/prod_config.properties");
            default ->
                    throw new IllegalStateException("Invalid env type " + env);
        }
    }
    public static ConfigLoadHelper getInstance(){
        if (configLoadHelper == null){
            configLoadHelper = new ConfigLoadHelper();
        }
        return configLoadHelper;
    }
    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if (prop != null)
            return prop;
        else throw new RuntimeException("Property baseUrl is not specified in the stg_config.properties file");
    }

}
