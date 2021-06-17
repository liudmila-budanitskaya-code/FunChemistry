package site.budanitskaya.chemistryquiz.fine.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import site.budanitskaya.chemistryquiz.fine.database.converters.AnswersConverter
import site.budanitskaya.chemistryquiz.fine.database.converters.StringConverter
import site.budanitskaya.chemistryquiz.fine.database.daos.QuestionDatabaseDao
import site.budanitskaya.chemistryquiz.fine.database.daos.ReactionsDatabaseDao
import site.budanitskaya.chemistryquiz.fine.database.db.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.utils.generateQuestionsList
import site.budanitskaya.chemistryquiz.fine.utils.generateReactionEntitiesList
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun questionDao(database: QuestionDatabase): QuestionDatabaseDao {
        return database.questionDao()
    }

    @Provides
    fun provideReactionDao(database: QuestionDatabase): ReactionsDatabaseDao {
        return database.reactionDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): QuestionDatabase {
        return Room.databaseBuilder(
            appContext,
            QuestionDatabase::class.java, "database"
        ).addCallback(object : RoomDatabase.Callback() {
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
        }).build()
    }
}