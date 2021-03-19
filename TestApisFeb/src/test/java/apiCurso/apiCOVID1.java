package apiCurso;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class apiCOVID1 {

    private static String baseURL;

    @Before
    public void obtener_var_ent() {
        baseURL = System.getenv("base_url");
    }

    @Test
    public void get_query_by_country() {
    RestAssured.baseURI = String.format("https://%s/country", baseURL);

    Response response = given()
            //.config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames())).asString()
            .log().all()
            .header("Accept", "*/*")
            .queryParams("name", "mexico")
            .queryParams("format", "json")
            .get();

    String bodyResponse = response.getBody().asString();
    System.out.println("Body Response: \n" + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertEquals("MX", JsonPath.read(bodyResponse, "$.code"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("lastUpdated"));
        assertTrue(response.getTime() < 500);
}

}
