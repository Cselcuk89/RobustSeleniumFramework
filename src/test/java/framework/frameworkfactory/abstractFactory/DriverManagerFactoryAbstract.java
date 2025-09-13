package framework.frameworkfactory.abstractFactory;


import framework.projectconstants.DriverType;

public class DriverManagerFactoryAbstract {
    public static DriverManagerAbstract getManager(DriverType driverType){
        switch (driverType){
            case CHROME -> {
                return new ChromeDriverManagerAbstract();
            }
            case FIREFOX -> {
                return new FirefoxDriverManagerAbstract();
            }
            case EDGE -> {
                return new EdgeDriverManagerAbstract();
            }
            default -> throw new IllegalStateException("Invalid driver: " + driverType);
        }

    }
}
