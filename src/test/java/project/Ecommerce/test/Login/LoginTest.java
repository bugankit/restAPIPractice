package project.Ecommerce.test.Login;


import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import project.Ecommerce.files.Login.Request.LoginRequestSer;
import project.Ecommerce.files.Login.Response.LoginResponseDeser;

// To run this file, please use mvn test -Dtest=LoginTest

public class LoginTest {

    public static String token;
    public static String userId;
    public static String message;
    public static String actualMessage = "Login Successfully";

    @Test(dataProvider = "validLoginData", description = "TC01 Validate Login with correct userid and password")
    public static void postLoginValid(String userEmail, String userPassword){

        RequestSpecification resp = new RequestSpecBuilder()
                                    .setBaseUri("https://rahulshettyacademy.com/api")
                                    .setContentType(ContentType.JSON)
                                    .build();


        LoginRequestSer requ = new LoginRequestSer();
        requ.setUserEmail(userEmail);
        requ.setUserPassword(userPassword);
        
        
        LoginResponseDeser respDes = given()
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
        
        System.out.println();
        System.out.println();
        System.out.println(token);
        System.out.println();
        System.out.println();


        System.out.println();
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.println();


    }

    @DataProvider(name = "validLoginData")
    public Object[][] getvalidValidData(){
        return new Object[][]{
            {"GundiyaBhau_Jabba1@gmail.com", "Gunidya@123"}
        };
    }

    
}
