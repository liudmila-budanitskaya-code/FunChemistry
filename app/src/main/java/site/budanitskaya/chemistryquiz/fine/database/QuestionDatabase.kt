package site.budanitskaya.chemistryquiz.fine.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import site.budanitskaya.chemistryquiz.fine.chemicalchips.*
import site.budanitskaya.chemistryquiz.fine.domain.generateQuestionsList

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
                    instance = buildDatabase(context.applicationContext)
                    INSTANCE = instance
                }
                return INSTANCE
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuestionDatabase::class.java, "database"
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.beginTransaction()
                    val reactionEntitiesList = generateReactionEntitiesList()
                    for(i in reactionEntitiesList.indices){
                        val reactionValues = ContentValues().apply {
                            put("reagents", StringConverter().toOneString(reactionEntitiesList[i].reagents))
                            put("products", StringConverter().toOneString(reactionEntitiesList[i].products))
                        }
                        db.insert("reaction_table", SQLiteDatabase.CONFLICT_ABORT, reactionValues)
                    }

                    val questionsList = generateQuestionsList()
                    for (i in questionsList.indices) {
                        val values = ContentValues().apply {
                            put("question_title", questionsList[i].questionTitle)
                            put(
                                "answer_options",
                                AnswersConverter().fromAnswers(
                                    questionsList[i].answers
                                )
                            )
                            put(
                                "topic",
                                    questionsList[i].topic
                            )
                            put(
                                "explanation",
                                questionsList[i].explanation
                            )
                        }
                        db.insert("question_table", SQLiteDatabase.CONFLICT_ABORT, values);
                    }


                    db.setTransactionSuccessful();
                    db.endTransaction()
                }
            }).allowMainThreadQueries().build()
    }
}