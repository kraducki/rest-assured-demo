package crud;

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
    public void readAllPosts() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Print response's details
        System.out.println(response.getTime());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    // Simple GET Request with assertion
    @Test
    public void readAllPostsWithResponseExtraction() {
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
    public void readOnePost() {

        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", json.get("title"));
    }

    // Path Variable
    @Test
    public void readOnePostPathVariable() {
        Response response = given()
                .pathParams("id", 1)
                .when()
                .get(BASE_URL + "/" + POSTS + "/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", json.get("title"));
    }

    // Query Param
    @Test
    public void readOnePostQueryParam() {
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
