package VideoGame

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
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
}