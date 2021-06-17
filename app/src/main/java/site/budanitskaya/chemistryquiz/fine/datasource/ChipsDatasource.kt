package site.budanitskaya.chemistryquiz.fine.datasource

import site.budanitskaya.chemistryquiz.fine.database.entities.ReactionEntity
import site.budanitskaya.chemistryquiz.fine.database.daos.ReactionsDatabaseDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ChipsDatasource @Inject constructor(private val reactionsDatabaseDao: ReactionsDatabaseDao?) :
    ReactionsDatabaseDao {

    override suspend fun insert(reaction: ReactionEntity) {
        reactionsDatabaseDao?.insert(reaction)
    }

    override suspend fun update(reaction: ReactionEntity) {
        reactionsDatabaseDao?.update(reaction)
    }

    override suspend fun delete(reaction: ReactionEntity) {
        reactionsDatabaseDao?.delete(reaction)
    }

    override suspend fun getReactionList(): List<ReactionEntity> {
        return reactionsDatabaseDao?.getReactionList()!!
    }

    override suspend fun insertAll(reactions: List<ReactionEntity>) {
        reactionsDatabaseDao?.insertAll(reactions)!!
    }
}

