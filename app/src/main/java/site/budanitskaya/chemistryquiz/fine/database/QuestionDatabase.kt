package site.budanitskaya.chemistryquiz.fine.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDatabaseDao?

    companion object {
        private lateinit var INSTANCE: QuestionDatabase
        fun getInstance(context: Context): QuestionDatabase {
            synchronized(QuestionDatabase::class.java) {
                val instance: QuestionDatabase
                if (!::INSTANCE.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuestionDatabase::class.java, "question_table"
                    ).allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return INSTANCE
            }
        }
    }
}