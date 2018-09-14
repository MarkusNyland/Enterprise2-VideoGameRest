package VideoGame

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import org.springframework.transaction.annotation.Transactional
import javax.persistence.TypedQuery



//2
@Repository
interface VGRepository : CrudRepository<VGEntity,Long>, VGRepositoryCustom {

    fun findAllByGenre(genre: String): Iterable<VGEntity>

    fun findAllByName(name: String): Iterable<VGEntity>

    fun findByName(name: String): VGEntity

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
        val entity = VGEntity(name, releaseDate, genre)
        em.persist(entity)
        return entity.id!!
    }

}