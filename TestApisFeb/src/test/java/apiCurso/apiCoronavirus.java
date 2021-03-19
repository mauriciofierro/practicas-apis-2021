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

    @Test
    public void test_summary_by_region_api() {
        RestAssured.baseURI = String.format("http://%s/api/v1/summary/region",baseURL);

        Response response = given()
                .log().all()
                .queryParam("region", "Mexico")
                .queryParam("sub_area", "1")
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
        assertTrue(bodyResponse.contains("tested"));
    }

    @Test
    public void fail_test_region_by_day_api() {
        RestAssured.baseURI = String.format("https://%s/api/v1/spots/day",baseURL);

        Response response = given()
                .log().all()
                .queryParam("region", "us")
                .header("Accept", "*/*")
                .get();

        String bodyResponse = response.getBody().asString();

        System.out.println("\nResponse --------------------------------\n");



        System.out.println("Response Body: " + bodyResponse);
        System.out.println("Status: " + response.statusCode());
        System.out.println("Headers: " + response.getHeader("Content-Type"));

        System.out.println("\nTest --------------------------------");

        assertEquals(500, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("link"));
    }

    @Test
    public void test_region_by_week_api() {
        RestAssured.baseURI = String.format("https://%s/api/v1/spots/week",baseURL);

        Response response = given()
                .log().all()
                .queryParam("region", "russia")
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
        assertTrue(bodyResponse.contains("tested"));
    }

    @Test
    public void test_region_by_month_api() {
        RestAssured.baseURI = String.format("https://%s/api/v1/spots/week",baseURL);

        Response response = given()
                .log().all()
                .queryParam("region", "russia")
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
        assertTrue(bodyResponse.contains("tested"));
    }

    @Test
    public void test_region_by_year_api() {
        RestAssured.baseURI = String.format("https://%s/api/v1/spots/year",baseURL);

        Response response = given()
                .log().all()
                .queryParam("region", "china")
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
        assertTrue(bodyResponse.contains("tested"));
    }

    @Test
    public void test_region_daily_hist_api() {
        RestAssured.baseURI = String.format("https://%s/api/v1/spots/region",baseURL);

        Response response = given()
                .log().all()
                .queryParam("region", "china")
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
        assertTrue(bodyResponse.contains("tested"));
    }

    @Test
    public void test_world_daily_hist_api() {
        RestAssured.baseURI = String.format("https://%s/api/v1/spots/summary",baseURL);

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
        assertEquals("collection", JsonPath.read(bodyResponse, "$.type"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("tested"));
    }
}
