package project.Ecommerce.files.AddToCart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ATC_RequestSer {

    @JsonProperty("_id")
    private String id;
    private ATC_AnItem product;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public ATC_AnItem getProduct() {
        return product;
    }
    public void setProduct(ATC_AnItem product) {
        this.product = product;
    }
    
}
