package postRequestTests;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ValidatePOSTRequestTest {


    @Test
    public void validateSuccessResponse (){

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");

        JSONObject requestParam = new JSONObject();
        requestParam.put("name", "morpheus");
        requestParam.put("job", "leader");

        request.body(requestParam.toString());
        Response response = request.post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), 201);

    }

    @Test
    public void validateRegistrationOfUserTest() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("email", "sydney@fife");
            requestJson.put("password", "pistol");
        } catch (JsonException e) {
            e.printStackTrace();

        }

        request.body(requestJson.toString());
        Response responseRegister = request.post("https://reqres.in/api/register");
        System.out.println("Body:" + responseRegister.getBody().toString());
        Assert.assertEquals(responseRegister.getStatusCode(), 201);
    }
}
