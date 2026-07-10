package project.FakeStore.testFile;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import project.FakeStore.body.GetDetails;
import project.FakeStore.body.SetUpResponse;

public class FakeStoreTest {
    public static String strResponse;
    public static String accessToken;
    public static String Pojo_token;
    public static String getResponseAccessToken;
    public static SetUpResponse sr;
    public static GetDetails gd;
    

    // mvn test -Dtest=FakeStoreTest
    
    @BeforeTest
    public static void setup(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        strResponse = given()//.log().all()
                    .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                    .formParam("grant_type", "client_credentials")
                    .formParam("scope", "trust")
                    .when().post("oauthapi/oauth2/resourceOwner/token")
                    .then().statusCode(200)//.log().all()
                    .extract().response().asString();

                     
        JsonPath js = new JsonPath(strResponse);
        accessToken = js.getString("access_token");
        
        // System.out.println();
        // System.out.println("<<<-------->>>");
        // System.out.println("<<<-------->>>");
        // System.out.println("--> Token is: " + accessToken);   
        // System.out.println("<<<-------->>>");
        // System.out.println("<<<-------->>>");
        // System.out.println();   


        sr = given()
                            .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                    .formParam("grant_type", "client_credentials")
                    .formParam("scope", "trust")
                    .when().post("oauthapi/oauth2/resourceOwner/token")
                    .then().statusCode(200).extract().as(SetUpResponse.class);

        Pojo_token = sr.getAccess_token();

    }

    @Test(description = "Loging with accessToken", enabled = false)
    public static void loginUsingAccessToken(){
        
        getResponseAccessToken = given()
                                .queryParam("access_token", accessToken)
                                .when().get("oauthapi/getCourseDetails")
                                .then().statusCode(401).log().all().extract().response().asString();

        // System.out.println();
        // System.out.println("<<<-------->>>");
        // System.out.println("<<<-------->>>");
        // System.out.println("--> accessResponse is: " + getResponseAccessToken);   
        // System.out.println("<<<-------->>>");
        // System.out.println("<<<-------->>>");
        // System.out.println(); 

    }

    @Test(description = "Login with PojoToken")
    public static void loginUsingPojoToken(){

        gd = given()
                                .queryParam("access_token", Pojo_token)
                                .when().get("oauthapi/getCourseDetails")
                                .then().statusCode(401).log().all().extract().as(GetDetails.class);

        String instructorr = gd.getInstructor();
        System.out.println();
        System.out.println("<<<-------->>>");
        System.out.println("<<<-------->>>");
        System.out.println("--> accessResponse is: " + instructorr);   
        System.out.println("<<<-------->>>");
        System.out.println("<<<-------->>>");
        System.out.println();                     
    }

    

}
