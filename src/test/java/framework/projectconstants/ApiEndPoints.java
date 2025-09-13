package framework.projectconstants;

public enum ApiEndPoints {
    STORE("/store"),
    ACCOUNT("/account"),
    ADD_TO_CART("/?wc-ajax=add_to_cart"),
    CHECKOUT("/checkout"),
    ACCOUNT_EDIT_BILLING_ADDRESS("/account/edit-address/billing/");

    public final String url;

    ApiEndPoints(String url) {
        this.url = url;
    }
}
