import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
//2
@Repository
interface VideoGameRepository : CrudRepository<VideoGameEntity,Long> {

    fun findAllByGenre(genre: String): Iterable<VideoGameEntity>

}