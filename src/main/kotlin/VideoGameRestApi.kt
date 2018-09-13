import com.google.common.base.Throwables
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.ConstraintViolationException
import javax.validation.Valid


@Api(value = "/videoGames", description = "Handling of creating and retrieving news")
@RequestMapping(
        path = ["/videoGames"], // when the url is "<base>/news", then this class will be used to handle it
        produces = [(MediaType.APPLICATION_JSON_VALUE)] // states that, when a method returns something, it is in Json
)
@RestController
class VideoGameRestApi {

    @Autowired
    private lateinit var crud: VideoGameRepository

}