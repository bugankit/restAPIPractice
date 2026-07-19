package project.Ecommerce.test.GET_AllItems;


import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import project.Ecommerce.files.GET_AllProducts.Product;
import project.Ecommerce.files.GET_AllProducts.ProductResponse;
import project.Ecommerce.files.Login.Request.LoginRequestSer;
import project.Ecommerce.files.Login.Response.LoginResponseDeser;

// To run this file, Please write mvn test -Dtest=GET_AllItems

public class GET_AllItems {

    public static String token;
    public static String userId;
    public static String message;
    public static String actualMessage = "Login Successfully";
    public static String getAllProductsResponse;
    public static LoginResponseDeser respDes;

    public static ProductResponse pr;

    @Test(dataProvider = "validData", priority = 0)
    public static void login(String userEmail, String userPassword){

        RequestSpecification resp = new RequestSpecBuilder()
                                    .setBaseUri("https://rahulshettyacademy.com/api")
                                    .setContentType(ContentType.JSON)
                                    .build();


        LoginRequestSer requ = new LoginRequestSer();
        requ.setUserEmail(userEmail);
        requ.setUserPassword(userPassword);
        
        
        respDes = given()
                                        .spec(resp)
                                        .body(requ)
                                    .when()
                                        .post("ecom/auth/login")
                                    .then()
                                        .statusCode(200)
                                        .extract().as(LoginResponseDeser.class);

        token = respDes.getToken();

        message = respDes.getMessage();

        Assert.assertEquals(message, actualMessage);

    }

    @Test(dependsOnMethods = "login", priority = 1, description = "TC01 - Validate Get All product response Body")
    public static void getAllProducts(){

        token = respDes.getToken();
        System.out.println();
        System.out.println();
        System.out.println(token);
        System.out.println();
        System.out.println();


        RequestSpecification getReq = new RequestSpecBuilder()
                                        .setBaseUri("https://rahulshettyacademy.com/api")
                                        .setContentType(ContentType.JSON)
                                        .build();

        // Using Striung

        // getAllProductsResponse = given().log().all()
        //                             .spec(getReq)
        //                             .header("Authorization", token)
        //                         .when()
        //                             .post("ecom/product/get-all-products")
        //                         .then().log().all()
        //                             .statusCode(200)
        //                             .extract().response().asString();

        pr = given().log().all()
                                    .spec(getReq)
                                    .header("Authorization", token)
                                .when()
                                    .post("ecom/product/get-all-products")
                                .then().log().all()
                                    .statusCode(200)
                                    .extract().response().as(ProductResponse.class);

        for (Product p : pr.getData()){
            System.out.println();
            System.out.println(p.getId());
            System.out.println(p.getProductAddedBy());
            System.out.println(p.getProductCategory());
            System.out.println(p.getProductDescription());
            System.out.println(p.getProductFor());
            System.out.println();        


        }                          
    }

    @DataProvider(name = "validData")
    public Object[][] getvalidValidData(){
        return new Object[][]{
            {"GundiyaBhau_Jabba1@gmail.com", "Gunidya@123"}
        };
    }    
}
