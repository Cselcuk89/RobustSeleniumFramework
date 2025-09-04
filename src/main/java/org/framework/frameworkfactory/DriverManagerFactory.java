package org.framework.frameworkfactory;

import org.framework.frameworkenums.DriverType;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType driverType) {
        switch (driverType) {
            case CHROME -> {
                return new ChromeDriverManager();
            }
            case FIREFOX -> {
                return new FirefoxDriverManager();
            }
            case EDGE -> {
                return new EdgeDriverManager();
            }
            default -> throw new IllegalStateException("Invalid driver: " + driverType);
        }
    }
}
