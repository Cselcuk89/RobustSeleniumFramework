package framework.dataproviders;

import framework.apiObjects.Product;
import framework.frameworkutils.JacksonHelper;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {
    @DataProvider(name = "getFeaturedProducts",parallel = true)
    public Object[] getFeaturedProducts() throws IOException {
        return JacksonHelper.deserializeJson("products.json", Product[].class);
    }
}
