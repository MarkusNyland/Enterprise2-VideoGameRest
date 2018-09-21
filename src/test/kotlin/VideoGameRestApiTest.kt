import VideoGame.VGDto
import io.restassured.RestAssured
import io.restassured.RestAssured.delete
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.springframework.http.MediaType

class VideoGameRestApiTest : ApiTestBase() {

    private fun createSomeGames() {
        createGame("a", "20.04.1887", "Fighting")
        createGame("b", "05.07.2908", "Racing")
        createGame("c", "07.09.2011", "Racing")
        createGame("d", "17.08.2004", "Arcade")
        createGame("e", "27.02.2017", "Fighting")
        createGame("f", "05.11.2015", "Fighting")
    }

    private fun createGame(name: String, releaseDate: String, genre: String) {
        given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(VGDto(null, name, releaseDate, genre))
                .post()
                .then()
                .statusCode(201)
    }

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
        RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
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

    @Test
    fun testCreateWithWrongType(){

        given().contentType(MediaType.APPLICATION_XML_VALUE)
                .body("")
                .post()
                .then()
                .statusCode(415)

        given().accept(MediaType.APPLICATION_JSON_VALUE)
                .get()
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(0))
    }

    @Test
    fun testGetWithGenreParameter(){
        createSomeGames()

        given().param("genre", "Fighting")
                .get()
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(3))
    }

    @Test
    fun testDoubleDelete(){
        val name = "League"
        val releaseDate = "01.01.2006"
        val genre = "MOBA"
        val dto = VGDto(null, name, releaseDate, genre)

        given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .post()
                .then()
                .statusCode(201)

        given().accept(MediaType.APPLICATION_JSON_VALUE)
                .get()
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(1))

        delete("/$name").then().statusCode(204)

        delete("/$name").then().statusCode(404)
    }

}