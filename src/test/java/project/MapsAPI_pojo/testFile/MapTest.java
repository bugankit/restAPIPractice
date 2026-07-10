package project.MapsAPI_pojo.testFile;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import project.MapsAPI_pojo.body.AdddPlaceBody;
import project.MapsAPI_pojo.body.Location;

import static io.restassured.RestAssured.*;

import java.util.*;

public class MapTest {

    // mvn test -Dtest=MapTest
    
    // public static String addResponse;
    public static String placeID;

    public static String updateMsg = "Address successfully updated";

    @Test
    public static void setup(){
        
        Location location = new Location();
        location.setLat(38.383494);
        location.setLng(33.427362);

        AdddPlaceBody apb = new AdddPlaceBody();
        apb.setLocation(location);
        apb.setAccuracy(50);
        apb.setAddress("29, side layout, cohen 09");
        apb.setLanguage("French-IN");
        apb.setName("Tattu's Villa");
        apb.setPhone_number("(+91) 983 893 3937");
        apb.setTypes(Arrays.asList("shoe park", "shop"));
        apb.setWebsite("http://rahulshettyacademy.com");

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String addResponse = given().log().all()
                    .queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body(apb)
                    .when().post("maps/api/place/add/json")
                    .then().log().all().statusCode(500).extract().response().asString();


        // JsonPath js = new JsonPath(addResponse);
        // placeID = js.getString("place_id");

        System.out.println("<<<-------->>>");
        System.out.println("<<<-------->>>");
        System.out.println(addResponse);   
        System.out.println("<<<-------->>>");
        System.out.println("<<<-------->>>");         
    }
    
}
