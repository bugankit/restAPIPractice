package project.Ecommerce.files.GET_AllProducts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    
    @JsonProperty("_id")
    private String id;

    private String productName;
    private String productCategory;
    private String productSubCategory;
    private int productPrice;
    private String productDescription;
    private String productImage;
    private String productRating;
    private String productTotalOrders;
    private boolean productStatus;
    private String productFor;
    private String productAddedBy;

    @JsonProperty("__v")
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getProductTotalOrders() {
        return productTotalOrders;
    }

    public void setProductTotalOrders(String productTotalOrders) {
        this.productTotalOrders = productTotalOrders;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductFor() {
        return productFor;
    }

    public void setProductFor(String productFor) {
        this.productFor = productFor;
    }

    public String getProductAddedBy() {
        return productAddedBy;
    }

    public void setProductAddedBy(String productAddedBy) {
        this.productAddedBy = productAddedBy;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
}
