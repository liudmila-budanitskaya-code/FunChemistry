package site.budanitskaya.chemistryquiz.fine.di

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.database.converters.AnswersConverter
import site.budanitskaya.chemistryquiz.fine.database.converters.StringConverter
import site.budanitskaya.chemistryquiz.fine.datasource.ChipsDatasource
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionRepository
import site.budanitskaya.chemistryquiz.fine.domain.generateQuestionsList
import site.budanitskaya.chemistryquiz.fine.domain.generateReactionEntitiesList
import site.budanitskaya.chemistryquiz.fine.firebasehelper.FirebaseAuthHelper
import site.budanitskaya.chemistryquiz.fine.firebasehelper.FirebaseAuthHelperImpl
import site.budanitskaya.chemistryquiz.fine.navigator.AppNavigator
import site.budanitskaya.chemistryquiz.fine.navigator.AppNavigatorImpl

class ServiceLocator(applicationContext: Context) {
    val database = Room.databaseBuilder(
        applicationContext,
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

    val chipsDatasource = ChipsDatasource(database)

    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    val questionsRepository = QuestionRepository(database)

    fun provideNavigator(activity: MainActivity): AppNavigator {
        return AppNavigatorImpl(activity)
    }

    fun provideFirebaseAuthHelper(activity: MainActivity): FirebaseAuthHelper {
        return FirebaseAuthHelperImpl(activity)
    }





    /*val loggerLocalDataSource = LoggerLocalDataSource(logsDatabase.logDao())

    fun provideDateFormatter() = DateFormatter()

    fun provideNavigator(activity: FragmentActivity): AppNavigator {
        return AppNavigatorImpl(activity)
    }*/

}