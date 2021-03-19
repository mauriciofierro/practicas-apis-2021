package apiCurso;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class apiReqres {
    private static String baseURL;

    @Before
    public void obtener_var_ent() {
        baseURL = System.getenv("base_url");
    }

    @Test
    public void get_user_list() {
        RestAssured.baseURI = String.format("https://%s/api/users/", baseURL);

        Response response = given()
                .log().all()
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].email"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("data"));
        assertTrue(response.getTime() < 500);

    }

    @Test
    public void get_user_list_page_2() {
        RestAssured.baseURI = String.format("https://%s/api/users", baseURL);

        Response response = given()
                .log().all()
                .queryParam("page", 2)
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].email"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("data"));
        assertTrue(response.getTime() < 500);

    }

    @Test
    public void get_unknown() {
        RestAssured.baseURI = String.format("https://%s/api/unkown", baseURL);

        Response response = given()
                .log().all()
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].pantone_value"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("data"));
        assertTrue(response.getTime() < 500);
    }

    @Test
    public void get_single_user() {
        RestAssured.baseURI = String.format("https://%s/api/users/5", baseURL);

        Response response = given()
                .log().all()
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data.email"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("data"));
        assertTrue(response.getTime() < 600);
    }

    @Test
    public void get_single_user_error() {
        RestAssured.baseURI = String.format("https://%s/api/users/44", baseURL);

        Response response = given()
                .log().all()
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(404, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(response.getTime() < 600);
    }

    @Test
    public void create_user() {
        RestAssured.baseURI = String.format("https://%s/api/users/", baseURL);

        String bodyRequest = "{\n" +
                "    \"name\": \"maurice\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = given()
                .log().all()
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(201, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.id"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("createdAt"));
        assertTrue(response.getTime() < 600);

    }

    @Test
    public void get_unknown_error() {
        RestAssured.baseURI = String.format("https://%s/api/unkown/44", baseURL);

        Response response = given()
                .log().all()
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(404, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(response.getTime() < 600);
    }

    @Test
    public void edit_user_put() {
        RestAssured.baseURI = String.format("https://%s/api/users/2", baseURL);

        String bodyRequest = "{\n" +
                "    \"name\": \"Maurice\",\n" +
                "    \"job\": \"QA Lead\"\n" +
                "}";

        Response response = given()
                .log().all()
                .body(bodyRequest)
                .put();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.updatedAt"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("name"));
        assertTrue(response.getTime() < 700);

    }

    @Test
    public void edit_user_patch() {
        RestAssured.baseURI = String.format("https://%s/api/users/6", baseURL);

        String bodyRequest = "{\n" +
                "    \"name\": \"Maurice\",\n" +
                "    \"job\": \"QA Lead\"\n" +
                "}";

        Response response = given()
                .log().all()
                .body(bodyRequest)
                .patch();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.updatedAt"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("name"));
        assertTrue(response.getTime() < 700);

    }

    @Test
    public void delete_user() {
        RestAssured.baseURI = String.format("https://%s/api/users/5", baseURL);

        Response response = given()
                .log().all()
                .delete();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(204, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(response.getTime() < 700);

    }

    @Test
    public void register_user() {
        RestAssured.baseURI = String.format("https://%s/api/register/", baseURL);

        String bodyRequest = "{\n" +
                "    \"email\": \"janet.weaver@reqres.in\",\n" +
                "    \"password\": \"hehelol\"\n" +
                "}";

        Response response = given()
                .log().all()
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.token"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("id"));
        assertTrue(response.getTime() < 600);

    }

    @Test
    public void register_user_error_email_or_password() {
        RestAssured.baseURI = String.format("https://%s/api/register/", baseURL);

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json; charset=utf-8")
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(400, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.error"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("error"));
        assertTrue(response.getTime() < 600);

    }

    @Test
    public void register_user_error_email() {
        RestAssured.baseURI = String.format("https://%s/api/register/", baseURL);

        String bodyRequest = "{\n" +
                "    \"email\": \"eevee.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(400, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.error"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("error"));
        assertTrue(response.getTime() < 600);
    }

    @Test
    public void user_login() {
        RestAssured.baseURI = String.format("https://%s/api/login/", baseURL);

        String bodyRequest = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslick\"\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.token"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("token"));
        assertTrue(response.getTime() < 600);
    }

    @Test
    public void user_login_email_error() {
        RestAssured.baseURI = String.format("https://%s/api/login/", baseURL);

        String bodyRequest = "{\n" +
                "    \"email\": \"eevee.holt@reqres.in\",\n" +
                "    \"password\": \"cityslick\"\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(400, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.error"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("error"));
        assertTrue(response.getTime() < 600);
    }

    @Test
    public void user_login_password_error() {
        RestAssured.baseURI = String.format("https://%s/api/login/", baseURL);

        String bodyRequest = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"\"\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(400, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.error"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("error"));
        assertTrue(response.getTime() < 600);
    }

    @Test
    public void get_user_list_delay() {
        RestAssured.baseURI = String.format("https://%s/api/users", baseURL);

        Response response = given()
                .log().all()
                .queryParam("delay", 5)
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].email"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("data"));
        assertTrue(response.getTime() < 5700);

    }

}
