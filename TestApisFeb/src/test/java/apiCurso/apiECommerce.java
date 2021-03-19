package apiCurso;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class apiECommerce {

private static String baseURL;
    private static String token;
    private static String user_uuid;
    private static String account_id;
    private static String address_id;

    @Before
    public void obtener_var_ent() {
        baseURL = System.getenv("base_url");
        //Tokens
        //Variables de entorno
        //Calculos
        //Datos
    }

    @Test
    public void obtener_categorias() {
        RestAssured.baseURI = String.format("https://%s/nga/api/v1/public/categories/insert", baseURL);

        Response response = given()
                .log().all()
                .queryParam("lang","es")
                .get();

        String bodyResponse = response.getBody().asString();

        System.out.println("\nResponse --------------------------------\n");

        System.out.println("Response Body: " + bodyResponse);
        System.out.println("Status: " + response.statusCode());
        System.out.println("Headers: " + response.getHeader("Content-Type"));
        System.out.println("Time: " + response.getTime());

        System.out.println("\nTest --------------------------------");

        assertEquals(200, response.getStatusCode());
        //assertEquals("stack", JsonPath.read(bodyResponse, "$.type"));
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("categories"));
        assertTrue(response.getTime() < 925);

    }

    @Test
    public void obtener_urls() {
        RestAssured.baseURI = String.format("https://%s/urls/v1/public/ad-listing", baseURL);
String bodyRequest = "{\n" +
        "    \"filters\": [\n" +
        "        {\n" +
        "            \"category\": \"1000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"1020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"1040\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"1060\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"1080\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"2000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"2020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"2040\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"2120\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"2080\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"2060\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"5000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"5040\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"5080\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"5020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"5060\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"3000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"3040\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"3020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"3060\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"3100\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"3080\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"3120\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"6000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"6020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"6040\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"6060\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4040\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4060\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4100\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4080\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4120\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"4140\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8040\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8060\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8080\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8100\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8120\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8140\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8160\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8180\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8200\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8220\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"8240\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"9000\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"9020\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"category\": \"9040\"\n" +
        "        }\n" +
        "    ]\n" +
        "}";
        Response response = given()
                .log().all()
                .queryParam("lang", "es")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        //Test
        assertEquals(200, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("urls"));
        assertTrue(response.getTime() < 925);
    }

    @Test
    public void crear_usuario() {
        RestAssured.baseURI = String.format("https://%s/nga/api/v1.1/private/accounts", baseURL);

        String user_name = "chico." + (Math.floor(Math.random() * 7539 + 2));
        String email = user_name + "@mailinator.com";
        double password = (Math.floor(Math.random() * 75639 + 2));

        String datos = email + ":" + password;
String encodeAuth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));

String bodyRequest = "{\n" +
        "    \"account\": {\n" +
        "        \"email\": \""+email+"\",\n" +
        "        \"name\": \""+user_name+"\",\n" +
        "        \"phone\": 3777649172\n" +
        "    }\n" +
        "}";

Response response = given()
        .log().all()
        .contentType("application/json")
        .queryParam("lang", "es")
        .header("Authorization", "Basic " + encodeAuth)
        .body(bodyRequest)
        .post();

String bodyResponse = response.getBody().asString();
System.out.println("Body Response: " + bodyResponse);

System.out.println("Datos: " + datos);
System.out.println("Encode: "+ encodeAuth);

        assertEquals(401, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("error"));
        assertTrue(response.getTime() < 1300);
    }

    @Test
    public void aobtener_token_usuario_valido() {
        RestAssured.baseURI = String.format("https://%s/nga/api/v1.1/private/accounts", baseURL);

        String user_name = "chico." + 1092;
        String email = user_name + "@mailinator.com";
        //String full_account_id;
        int password = 12345;

        String datos = email + ":" + password;
        String encodeAuth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));


        Response response = given()
                .log().all()
                .contentType("application/json")
                .queryParam("lang", "es")
                .header("Authorization", "Basic " + encodeAuth)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        token = JsonPath.read(bodyResponse, "$.access_token");
        user_uuid = JsonPath.read(bodyResponse, "$.account.uuid");
        account_id = JsonPath.read(bodyResponse, "$.account.account_id");


        System.out.println(token);
        System.out.println(user_uuid);
        System.out.println(account_id);

        assertEquals(200, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("access_token"));
        assertTrue(response.getTime() < 5000);
    }

    @Test
    public void modificar_usuario() {
        RestAssured.baseURI = String.format("https://%s/nga/api/v1/private/accounts/11116129", baseURL);

        String bodyRequest = "{\n" +
                "    \"account\": {\n" +
                "        \"name\": \"Francisco Gonzalez\",\n" +
                "        \"phone\": \"7441928376\",\n" +
                "        \"locations\": [\n" +
                "            {\n" +
                "                \"code\": \"11\",\n" +
                "                \"key\": \"region\",\n" +
                "                \"label\": \"Ciudad de México\",\n" +
                "                \"locations\": [\n" +
                "                    {\n" +
                "                        \"code\": \"294\",\n" +
                "                        \"key\": \"municipality\",\n" +
                "                        \"label\": \"Cuajimalpa de Morelos\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"professional\": false,\n" +
                "        \"phone_hidden\": false\n" +
                "    }\n" +
                "}";

        Response response = given()
                .log().all()
                .queryParam("lang", "es")
                .header("Authorization", "tag:scmcoord.com,2013:api " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body(bodyRequest)
                .patch();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertEquals(true, JsonPath.read(bodyResponse, "$.account.email_verified"));
        assertTrue(bodyResponse.contains("uuid"));
        assertTrue(response.getTime() < 800);
    }

    @Test
    public void nueva_direccion() {
        RestAssured.baseURI = String.format("https://%s/addresses/v1/create", baseURL);

        System.out.println("UUID: " + user_uuid);
        System.out.println("Token: " + token);

        String datos = user_uuid + ":" + token;
        String encodeAuth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encode Auth: "+ encodeAuth);

        Response response = given()
                .log().all()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + encodeAuth)
                .formParam("contact", "Srita. Fdez.")
                .formParam("phone","4467946135")
                .formParam("rfc", "MERS851125XXX")
                .formParam("zipCode", "45050")
                .formParam("exteriorInfo", "Chilpayatito D-105")
                .formParam("region", "5")
                .formParam("municipality", "51")
                .formParam("area", "28514")
                .formParam("alias", "Le Poisson")
                .post();

    //System.out.println(datos);
        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        address_id = JsonPath.read(bodyResponse, "$.addressID");

        assertEquals(201, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("addressID"));
        assertTrue(response.getTime() < 800);

    }

    @Test
    public void crear_anuncio() {
        RestAssured.baseURI = String.format("https://%s/accounts/%s/up", baseURL, user_uuid);

        String datos = user_uuid + ":" + token;
        String encodeAuth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));

        String bodyRequest = "{\n" +
                "    \"images\": \"8591150986.jpg\",\n" +
                "    \"category\": \"4100\",\n" +
                "    \"subject\": \"Figuras Amiibo, Varias. Zelda, Mario, Pokémon.\",\n" +
                "    \"body\": \"Figuras interactivas y coleccionables de Nintendo. Varios personajes. Pregunta por disponibilidad.\",\n" +
                "    \"is_new\": \"0\",\n" +
                "    \"price\": \"350\",\n" +
                "    \"region\": \"11\",\n" +
                "    \"municipality\": \"294\",\n" +
                "    \"area\": \"119537\",\n" +
                "    \"phone_hidden\": \"true\",\n" +
                "    \"show_phone\": \"false\"\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Authorization", "Basic " + encodeAuth)
                .header("x-source", "PHOENIX_DESKTOP")
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();

        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("ad_id"));
        assertTrue(response.getTime() < 2500);
    }

    @Test
    public void xeliminar_direccion() {
        RestAssured.baseURI = String.format("https://%s/addresses/v1/delete/%s", baseURL, address_id);

        System.out.println("UUID: " + user_uuid);
        System.out.println("Token: " + token);

        String datos = user_uuid + ":" + token;
        String encodeAuth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));
        //System.out.println("Encode Auth: "+ encodeAuth);

        Response response = given()
                .log().all()
                .header("Accept", "*/*")
                .header("Authorization", "Basic " + encodeAuth)
                .delete();

        //System.out.println(datos);
        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(200, response.getStatusCode());
        assertNotNull(bodyResponse);
        assertTrue(bodyResponse.contains("message"));
        assertTrue(response.getTime() < 800);

    }

}
