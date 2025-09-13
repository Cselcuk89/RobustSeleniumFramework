package framework.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiRequest extends SpecBuilder {
    public static Response postApi(String endpoint, Headers headers,
                                   HashMap<String,Object> formParams, Cookies cookies){
        return given(getRequestSpec())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                .when()
                .post(endpoint)
                .then().spec(getResponseSpec())
                .extract().response();
    }
    public static Response get(String endPoint, Cookies cookies) {
        return given(getRequestSpec()).
                cookies(cookies).
                when().
                get(endPoint).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

}
