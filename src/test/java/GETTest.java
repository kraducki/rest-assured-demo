import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    // Simple GET Request without any assertion
    @Test
    public void readAllProducts() {
        given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200);

    }

    // Simple GET Request with assertion
    @Test
    public void readAllProductsWithResponseExtraction() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> titles = json.getList("title");
        assertEquals(100, titles.size());

    }

    // Simple GET Request to read one title
    @Test
    public void readOneProduct() {

        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", json.get("title"));
        assertEquals("3.9", json.get("rating.rate").toString());

    }

    // Path Variable
    @Test
    public void readOneProductPathVariable() {
        Response response = given()
                .pathParams("id", 1)
                .when()
                .get(BASE_URL + "/" + POSTS + "/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", json.get("title"));
        assertEquals("3.9", json.get("rating.rate").toString());
    }

    @Test
    public void readOneProductQueryParam() {
        Response response = given()
                .queryParam("title", "qui est esse")
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        System.out.println(json.getList("title").size());

    }


}
