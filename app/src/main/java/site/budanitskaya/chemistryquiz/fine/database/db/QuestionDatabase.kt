package site.budanitskaya.chemistryquiz.fine.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.database.daos.QuestionDatabaseDao
import site.budanitskaya.chemistryquiz.fine.database.daos.ReactionsDatabaseDao
import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.database.entities.ReactionEntity
import site.budanitskaya.chemistryquiz.fine.di.ServiceLocator

@Database(entities = [Question::class, ReactionEntity::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDatabaseDao
    abstract fun reactionDao(): ReactionsDatabaseDao
}