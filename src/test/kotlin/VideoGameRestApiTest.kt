import VideoGame.VGDto
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.springframework.http.MediaType

class VideoGameRestApiTest : ApiTestBase() {

    @Test
    fun testCreateAndGetWithNewFormat() {

        val name = "League"
        val releaseDate = "01.01.2006"
        val genre = "MOBA"
        val dto = VGDto(null, name, releaseDate, genre)

        //no news
        RestAssured.given().accept(MediaType.APPLICATION_JSON_VALUE)
                .get()
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(0))

        //create a news
        val id = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .post()
                .then()
                .statusCode(201)
                .extract().asString()

        //should be 1 news now
        RestAssured.given().accept(MediaType.APPLICATION_JSON_VALUE)
                .get()
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(1))

        //1 news with same data as the POST
        RestAssured.given().accept(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("name", name)
                .get("/{name}")
                .then()
                .statusCode(200)
                .body("name", CoreMatchers.equalTo(name))
                .body("releaseDate", CoreMatchers.equalTo(releaseDate))
                .body("genre", CoreMatchers.equalTo(genre))
    }

}