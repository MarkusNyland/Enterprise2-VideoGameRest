import io.swagger.annotations.ApiModelProperty

data class Dto (

        @ApiModelProperty("Id of the game")
        var id: String? = null,

        @ApiModelProperty("Name of the game")
        var name: String? = null,

        @ApiModelProperty("date it was released")
        var releaseDate: String? = null,

        @ApiModelProperty("type of game genre ")
        var genre: String? = null

        )