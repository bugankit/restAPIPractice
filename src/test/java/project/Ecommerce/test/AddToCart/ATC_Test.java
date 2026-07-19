package project.Ecommerce.test.AddToCart;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import project.Ecommerce.files.AddToCart.ATC_AnItem;
import project.Ecommerce.files.AddToCart.ATC_RequestSer;
import project.Ecommerce.files.AddToCart.ATC_responseDesr;
import project.Ecommerce.files.Login.Request.LoginRequestSer;
import project.Ecommerce.files.Login.Response.LoginResponseDeser;

// To run this file, Please write "mvn test -Dtest=ATC_Test"

public class ATC_Test {

    public static String token;
    public static String userId;
    public static String actualMessage = "Login Successfully";
    public static String message;
    public static String ATC_successMessage;
    public static String actual_ATC_successMessage = "Product Added To Cart";

    public static LoginResponseDeser respDes;
    public static ATC_RequestSer atc_reqSer;
    public static ATC_AnItem anItem;


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
        userId = respDes.getUserId();

        message = respDes.getMessage();

        Assert.assertEquals(message, actualMessage);

    }

    @Test(dependsOnMethods = "login", description = "TC01_ Add an Item to cart")
    public static void AddItemsToCart(){


        anItem = new ATC_AnItem();

        anItem.setId("6960eae1c941646b7a8b3ed3");
        anItem.setProductName("ADIDAS ORIGINAL");
        anItem.setProductCategory("electronics");
        anItem.setProductSubCategory("mobiles");
        anItem.setProductPrice(11500);
        anItem.setProductDescription("Apple iphone");
        anItem.setProductImage("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1767959265156.jpg");
        anItem.setProductRating("0");
        anItem.setProductTotalOrders("0");
        anItem.setProductStatus(true);
        anItem.setProductFor("women");
        anItem.setProductAddedBy("admin");
        anItem.setVersion(0);
        


        atc_reqSer = new ATC_RequestSer();
        atc_reqSer.setId(userId);
        atc_reqSer.setProduct(anItem);

        RequestSpecification respp = new RequestSpecBuilder()
                                    .setBaseUri("https://rahulshettyacademy.com/api")
                                    .setContentType(ContentType.JSON)
                                    .build();

        ATC_responseDesr responseDesr = given()
                                            .log().all()
                                            .spec(respp)
                                            .body(atc_reqSer)
                                            .header("Authorization", token)
                                        .when()
                                            .post("ecom/user/add-to-cart")
                                        .then().log().all()
                                            .statusCode(200)
                                            .extract().response().as(ATC_responseDesr.class);

        

        ATC_successMessage = responseDesr.getMessage();

        System.out.println();
        System.out.println(ATC_successMessage);
        System.out.println();


        // Assert.assertEquals(ATC_successMessage,actual_ATC_successMessage);

    }

    @DataProvider(name = "validData")
    public Object[][] getvalidValidData(){
        return new Object[][]{
            {"GundiyaBhau_Jabba1@gmail.com", "Gunidya@123"}
        };
    }    
    
}
