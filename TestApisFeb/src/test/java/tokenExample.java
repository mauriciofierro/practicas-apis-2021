import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class tokenExample {
    @Test
    public void generateToken() {
String url = String.format("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts?lang=es");

Response resp = given().header("Authorization", "Basic Y2hpY28uMTA5MkBtYWlsaW5hdG9yLmNvbToxMjM0NQ==").post(url);

String bodyResponse = resp.getBody().asString();

System.out.println("Response Body: " + bodyResponse);

    }
}
