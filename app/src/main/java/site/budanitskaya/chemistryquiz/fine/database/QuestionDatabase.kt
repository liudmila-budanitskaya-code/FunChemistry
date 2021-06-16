package site.budanitskaya.chemistryquiz.fine.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.di.ServiceLocator
import site.budanitskaya.chemistryquiz.fine.domain.generateQuestionsList
import site.budanitskaya.chemistryquiz.fine.domain.generateReactionEntitiesList

@Database(entities = [Question::class, ReactionEntity::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDatabaseDao?
    abstract fun reactionDao(): ReactionsDatabaseDao?

    companion object {
        private lateinit var INSTANCE: QuestionDatabase
        fun getInstance(context: Context): QuestionDatabase {
            synchronized(QuestionDatabase::class.java) {
                val instance: QuestionDatabase
                if (!::INSTANCE.isInitialized) {
                    instance = buildDatabase(MainApplication.applicationContext())
                    INSTANCE = instance
                }
                return INSTANCE
            }
        }

        private fun buildDatabase(context: Context) = ServiceLocator(context).database

    }
}