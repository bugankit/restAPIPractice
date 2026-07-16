package project.Ecommerce.test.Register;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import project.Ecommerce.files.Login.Request.RegisterRequstSer;
import project.Ecommerce.files.Login.Response.RegisterResponseDeSer;

// To run this file, write mvn test -Dtest=Register in terminal

public class Register {

    public static String validMessage;
    public static String actualValidMessage = "Registered Successfully";
    public static RegisterResponseDeSer responseDeSer;

    @Test(description = "TC01 Validate Register for application with valid credentials", dataProvider = "validRegisterData")
    public static void validRegister(String userEmail, String userPassword){

        RegisterRequstSer regReq = new RegisterRequstSer();
        regReq.setFirstName("Tatteshwar");
        regReq.setLastName("Mehto");
        regReq.setUserEmail(userEmail);
        regReq.setUserRole("customer");
        regReq.setOccupation("student");
        regReq.setGender("male");
        regReq.setUserMobile("6262179962");
        regReq.setUserPassword(userPassword);
        regReq.setConfirmPassword(userPassword);
        regReq.setRequired(true);

        RequestSpecification req = new RequestSpecBuilder()
                                    .setBaseUri("https://rahulshettyacademy.com/api")
                                    .setContentType(ContentType.JSON)
                                    .build();


        responseDeSer = given()
                            .spec(req)
                            .body(regReq)
                        .when()
                            .post("ecom/auth/register")
                        .then()
                            .statusCode(200)
                        .extract().as(RegisterResponseDeSer.class);


        validMessage = responseDeSer.getMessage();
        
        Assert.assertEquals(validMessage, actualValidMessage);

        System.out.println();
        System.out.println();
        System.out.println(validMessage);
        System.out.println();
        System.out.println();

    }

    @DataProvider(name = "validRegisterData")
    public Object[][] getValidData(){
        return new Object[][]{
            {"GundiyaBhau_Jabba1@gmail.com", "Gunidya@123"}
        };
    }
    
}
