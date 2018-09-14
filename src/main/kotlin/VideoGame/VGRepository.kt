package VideoGame

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import org.springframework.transaction.annotation.Transactional

//2
@Repository
interface VGRepository : CrudRepository<VGEntity,Long>, VGRepositoryCustom {

    fun findAllByGenre(genre: String): Iterable<VGEntity>

}

@Transactional
interface VGRepositoryCustom {

    fun createVideoGame(name: String, releaseDate: String, genre: String): Long
}

@Repository
@Transactional
class VGRepositoryImpl : VGRepositoryCustom {

    @Autowired
    private lateinit var em: EntityManager

    override fun createVideoGame(name: String, releaseDate: String, genre: String): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}