package crud;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POSTTest {

    @Test
    public void createNewPost() {

        String jsonBody = "{\n" +
                "    \"userId\": 101,\n" +
                "    \"title\": \"testTitle\",\n" +
                "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("101", json.get("userId").toString());
        assertEquals("testTitle", json.get("title"));
    }
}
