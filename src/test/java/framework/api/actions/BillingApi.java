package framework.api.actions;

import framework.projectconstants.ApiEndPoints;
import framework.projectconstants.Codes;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import framework.apiObjects.BillingAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

public class BillingApi {
    private Cookies cookies;

    public BillingApi(Cookies cookies) {
        this.cookies = cookies;
    }
    public Response addBillingAddress(BillingAddress billingAddress){
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("billing_first_name",billingAddress.getFirstName() );
        formParams.put("billing_last_name", billingAddress.getLastName());
        formParams.put("billing_country", Codes.getCountryCode(billingAddress.getCountry()));
        formParams.put("billing_address_1", billingAddress.getAddressLineOne());
        formParams.put("billing_city", billingAddress.getCity());
        formParams.put("billing_state", Codes.getStateCode(billingAddress.getState()));
        formParams.put("billing_postcode", billingAddress.getPostalCode());
        formParams.put("billing_company", billingAddress.getCompany());
        formParams.put("billing_phone", billingAddress.getPhone());
        formParams.put("woocommerce-edit-address-nonce", fetchEditBillingAddressNonceValueUsingJsoup());
        formParams.put("action", "edit_address");
        formParams.put("save_address", "Save address");
        formParams.put("billing_email", billingAddress.getEmail());
        Response response = ApiRequest.postApi(
                ApiEndPoints.ACCOUNT_EDIT_BILLING_ADDRESS.url, headers, formParams, cookies);

        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to edit the address of the account -" + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;

    }
    private String fetchEditBillingAddressNonceValueUsingJsoup() {
        Response response = getBillingAddress();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-edit-address-nonce");
        assert element != null;
        return element.attr("value");
    }

    private Response getBillingAddress() {
        Response response = ApiRequest.get(ApiEndPoints.ACCOUNT_EDIT_BILLING_ADDRESS.url, cookies);
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account, HTTP Status Code: " + response.getStatusCode());
        }
        return response;
    }
}
