package VideoGame

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
//1

@Entity
class VGEntity (

        /**
         * In Spring we can use JPA, and so have @VGEntity objects to model our database.
         *
         * Note how in Kotlin we can avoid most of the boilerplate, like getters/setters.
         *
         * One negative side though is the use of @get: to annotate the properties, which is
         * needed to specify that we want to annotate the "get" method generated for such property.
         *
         * If for example you use @NotBlank instead of @get:NotBlank, such annotation would
         * be applied on the constructor parameter, not on the field :(
         *
         * Created by arcuri82 on 16-Jun-17.
         */

        @get:NotBlank @get:Size(max = 64)
        var name: String,

        @get:NotBlank @get:Size(max = 20)
        var releaseDate: String,

        @get:NotBlank @get:Size(max = 64)
        var genre: String,

        @get:Id @get:GeneratedValue
        var id: Long? = null

        /*
                           Note how we need to explicitly state that id can be null (eg when entity
                           is not in sync with database).
                           The "= null" is used to provide a default value if caller does not
                           provide one.
                        */
)





