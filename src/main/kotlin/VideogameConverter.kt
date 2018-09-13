class VideogameConverter {

    companion object {
        fun transform(entity: VideoGameEntity): Dto {

            return Dto(id = entity.id?.toString(),
                       name = entity.name,
                       releaseDate = entity.releaseDate,
                       genre = entity.genre
            )
        }

        fun transform(entities: Iterable<VideoGameEntity>): List<Dto> {
            return entities.map { transform(it) }
        }

    }
}