package apiCurso;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class apiGoREST {
    private static String baseURL;
    private static String a_token;
    private static int user_id;
    private static int post_id;

    @Before
    public void obtener_var_ent() {
        baseURL = System.getenv("base_url");
        a_token = System.getenv("token");
        //Tokens
        //Variables de entorno
        //Calculos
        //Datos
    }

    @Test
    public void get_all_users() {
        RestAssured.baseURI = String.format("https://%s/public-api/users", baseURL);

        Response response = given()
                .log().all()
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].email"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("data"));
        assertTrue(response.getTime() < 925);

    }

    @Test
    public void create_users() {
        RestAssured.baseURI = String.format("https://%s/public-api/users", baseURL);

        String bodyRequest = "{\n" +
                "    \"name\": \"Zelda Fitzgerald\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"email\": \"zelda.fitzgerald@emerson.com\",\n" +
                "    \"status\": \"Active\"\n" +
                "}";

        //System.out.println(bodyRequest);

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        user_id = JsonPath.read(bodyResponse, "$.data.id");

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data.email"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void get_single_user() {
        RestAssured.baseURI = String.format("https://%s/public-api/users/%s", baseURL, user_id);

        Response response = given()
                .log().all()
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data.email"));
        assertEquals("Active", JsonPath.read(bodyResponse, "$.data.status"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void patch_user() {
        RestAssured.baseURI = String.format("https://%s/public-api/users/%s", baseURL, user_id);

        String bodyRequest = "{\n" +
                "    \"name\": \"Zelda Williams\"\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .patch();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data.email"));
        assertEquals("Active", JsonPath.read(bodyResponse, "$.data.status"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void post_user_publication() {
        RestAssured.baseURI = String.format("https://%s/public-api/users/%s/posts", baseURL, user_id);

        String bodyRequest = "{\n" +
                "    \"title\": \"Whose name is the Princess's?\",\n" +
                "    \"body\": \"It's because of F. Scott Fitzgerlad's wife, of course!\"\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        post_id = JsonPath.read(bodyResponse, "$.data.id");

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data.body"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void ppget_user_publication() {
        RestAssured.baseURI = String.format("https://%s/public-api/users/%s/posts", baseURL, user_id);

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].body"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void post_user_qcomments() {
        RestAssured.baseURI = String.format("https://%s/public-api/posts/%s/comments", baseURL, post_id);

        String bodyRequest = "{\n" +
                "    \"name\":\"Chuchita Perez\",\n" +
                "    \"email\":\"the.chuchs@no-reply.com\",\n" +
                "    \"body\": \"I did not know that! Thank you for enlightening us!\"\n" +
                "\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data.body"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void ppget_pub_comments() {
        RestAssured.baseURI = String.format("https://%s/public-api/posts/%s/comments", baseURL, post_id);

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].email"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void post_users_todos() {
        RestAssured.baseURI = String.format("https://%s/public-api/users/%s/todos", baseURL, user_id);

        String bodyRequest = "{\n" +
                "    \"title\": \"Brush your teeth before you go to bed!\",\n" +
                "    \"completed\": true\n" +
                "}";

        System.out.println(bodyRequest);

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertEquals(true, JsonPath.read(bodyResponse, "$.data.completed"));
        assertNotNull(JsonPath.read(bodyResponse, "$.data.title"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

    @Test
    public void ppget_users_todos() {
        RestAssured.baseURI = String.format("https://%s/public-api/users/%s/todos", baseURL, user_id);

        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + a_token)
                .header("Content-Type", "application/json")
                .get();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertEquals(true, JsonPath.read(bodyResponse, "$.data[0].completed"));
        assertNotNull(JsonPath.read(bodyResponse, "$.data[0].title"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("created_at"));

    }

}
