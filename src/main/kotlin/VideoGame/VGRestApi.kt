package VideoGame

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//3

@Api(value = "/videoGames", description = "Handling of creating and retrieving video games")
@RequestMapping(
        path = ["/videoGames"],
        produces = [(MediaType.APPLICATION_JSON_VALUE)]
)
@RestController
class VGRestApi {

    @Autowired
    private lateinit var crud: VGRepository

    @ApiOperation("Get all the games")
    @GetMapping
    fun getAllGames(
            @ApiParam("The games genre")
            @RequestParam("genre", required = false)
            genre: String?): ResponseEntity<List<VGDto>> {

        val list = if (genre.isNullOrBlank()) {
            crud.findAll()
        } else {
            crud.findAllByGenre(genre!!)
        }

        return ResponseEntity.ok(VGConverter.transform(list))
    }

    @ApiOperation("Create a game")
    @PostMapping
    fun createGame(
            @ApiParam("Spesify name, releaseDate and genre. Do NOT spesify id")
            @RequestBody
            dto: VGDto) : ResponseEntity<Long> {

        if (!dto.id.isNullOrBlank()){
            return ResponseEntity.status(400).build()
        }

        if (dto.name.isNullOrBlank() || dto.releaseDate.isNullOrBlank() || dto.genre.isNullOrBlank()){
            return ResponseEntity.status(400).build()
        }

        val id: Long?
        try {
            id = crud.createVideoGame(dto.name!!, dto.releaseDate!!, dto.genre!!)
        }
        catch (e: Exception) {
            throw e
        }

        return ResponseEntity.status(200).build()

    }
}