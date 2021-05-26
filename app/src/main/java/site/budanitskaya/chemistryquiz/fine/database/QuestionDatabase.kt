package site.budanitskaya.chemistryquiz.fine.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import site.budanitskaya.chemistryquiz.fine.generateQuizItems
import site.budanitskaya.chemistryquiz.fine.questionscreen.generateQuestionsList
import site.budanitskaya.chemistryquiz.fine.questionscreen.mapQuizItemsToQuestionsList

@Database(entities = [Question::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDatabaseDao?

    companion object {
        private lateinit var INSTANCE: QuestionDatabase
        fun getInstance(context: Context): QuestionDatabase {
            synchronized(QuestionDatabase::class.java) {
                val instance: QuestionDatabase
                if (!::INSTANCE.isInitialized) {
                    instance = buildDatabase(context.applicationContext)
                    INSTANCE = instance
                }
                return INSTANCE
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuestionDatabase::class.java, "question_table"
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.beginTransaction()
                    val list = generateQuestionsList()
                    for (i in list.indices) {
                        val values = ContentValues().apply {
                            put("question_title", list[i].questionTitle)
                            put(
                                "answer_options",
                                AnswersConverter().fromAnswers(
                                    list[i].answers
                                )
                            )
                            put(
                                "topic",
                                    list[i].topic
                            )
                        }
                        db.insert("question_table", SQLiteDatabase.CONFLICT_ABORT, values);
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction()

                }
            }).build()
    }
}