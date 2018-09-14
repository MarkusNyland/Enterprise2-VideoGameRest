package VideoGame

class VGConverter {

    companion object {
        fun transform(entity: VGEntity): VGDto {

            return VGDto(id = entity.id?.toString(),
                    name = entity.name,
                    releaseDate = entity.releaseDate,
                    genre = entity.genre
            )
        }

        fun transform(entities: Iterable<VGEntity>): List<VGDto> {
            return entities.map { transform(it) }
        }

    }
}