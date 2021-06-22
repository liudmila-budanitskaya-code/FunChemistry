package site.budanitskaya.chemistryquiz.fine.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import site.budanitskaya.chemistryquiz.fine.database.daos.QuestionDatabaseDao
import site.budanitskaya.chemistryquiz.fine.database.daos.ReactionsDatabaseDao
import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.database.entities.ReactionEntity


@Database(entities = [Question::class, ReactionEntity::class], version = 1, exportSchema = false)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDatabaseDao
    abstract fun reactionDao(): ReactionsDatabaseDao
}