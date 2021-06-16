package site.budanitskaya.chemistryquiz.fine.datasource

import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.database.ReactionEntity
import site.budanitskaya.chemistryquiz.fine.database.ReactionsDatabaseDao

class ChipsDatasource(private val database: QuestionDatabase) : ReactionsDatabaseDao {


    override suspend fun insert(reaction: ReactionEntity) {
        database.reactionDao()?.insert(reaction)
    }

    override suspend fun update(reaction: ReactionEntity) {
        database.reactionDao()?.update(reaction)
    }

    override suspend fun delete(reaction: ReactionEntity) {
        database.reactionDao()?.delete(reaction)
    }

    override suspend fun getReactionList(): List<ReactionEntity> {
        return database.reactionDao()?.getReactionList()!!
    }

    override suspend fun insertAll(reactions: List<ReactionEntity>) {
        database.reactionDao()?.insertAll(reactions)!!
    }
}

