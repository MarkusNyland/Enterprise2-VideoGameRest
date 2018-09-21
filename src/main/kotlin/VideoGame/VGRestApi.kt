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
    fun get(
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

    @ApiOperation("Get a game by name")
    @GetMapping(path = ["/{name}"])
    fun getGame(
            @ApiParam("Name of a game")
            @PathVariable("name")
            pathName: String?) : ResponseEntity<VGDto> {

        val dto: VGEntity
        try {
            val id = crud.findByName(pathName!!).id!!
            dto = crud.findById(id).get()
        }
            catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        return ResponseEntity.ok(VGConverter.transform(dto))
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

        try {
            crud.createVideoGame(dto.name!!, dto.releaseDate!!, dto.genre!!)
        }
        catch (e: Exception) {
            throw e
        }

        return ResponseEntity.status(201).build()

    }

    @ApiOperation("Delete a game with a specific name")
    @DeleteMapping(path = ["/{name}"])
    fun deleteGame(
            @ApiParam("Name of a game")
            @PathVariable("name")
            pathName: String?): ResponseEntity<VGDto>{

        val id: Long
        try {
            id = crud.findByName(pathName!!).id!!
        } catch (e: Exception) {
            return ResponseEntity.status(404).build()
        }

        crud.deleteById(id)
        return ResponseEntity.status(204).build()
    }
}