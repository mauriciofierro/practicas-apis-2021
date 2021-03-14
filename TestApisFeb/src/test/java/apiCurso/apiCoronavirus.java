package apiCurso;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;



public class apiCoronavirus {
    private static String baseURL;

    @Before
    public void obtener_var_ent() {
        baseURL = System.getenv("base_url");
        //Tokens
        //Variables de entorno
        //Calculos
        //Datos
    }

    @Test
    public void test_ping_api() {
        RestAssured.baseURI = String.format("https://%s/api/v1/ping/", baseURL);

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .get();

        String bodyResponse = response.getBody().asString();

        System.out.println("\nResponse --------------------------------\n");



        System.out.println("Response Body: " + bodyResponse);
        System.out.println("Status: " + response.statusCode());
        System.out.println("Headers: " + response.getHeader("Content-Type"));

        System.out.println("\nTest --------------------------------");

        assertEquals(200, response.getStatusCode());
        //assertEquals("stack", JsonPath.read(bodyResponse, "$.type"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("pong"));
    }

    @Test
    public void test_summary_api() {
        RestAssured.baseURI = String.format("https://api.quarantine.country/api/v1/summary/latest/");

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .get();

        String bodyResponse = response.getBody().asString();

        System.out.println("\nResponse --------------------------------\n");



        System.out.println("Response Body: " + bodyResponse);
        System.out.println("Status: " + response.statusCode());
        System.out.println("Headers: " + response.getHeader("Content-Type"));

        System.out.println("\nTest --------------------------------");

        assertEquals(200, response.getStatusCode());
        assertEquals("stack", JsonPath.read(bodyResponse, "$.type"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("recovered"));
    }
}
