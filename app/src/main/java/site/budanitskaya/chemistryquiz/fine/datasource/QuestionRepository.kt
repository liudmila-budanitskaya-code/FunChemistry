package site.budanitskaya.chemistryquiz.fine.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import site.budanitskaya.chemistryquiz.fine.database.Question
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabaseDao
import site.budanitskaya.chemistryquiz.fine.generateQuizItems
import site.budanitskaya.chemistryquiz.fine.questionscreen.toQuestion

class QuestionRepository(private val questionDataBase: QuestionDatabase) : QuestionDatabaseDao {

    override suspend fun insert(question: Question) {

    }

    override suspend fun update(question: Question) {

    }

    override suspend fun delete(question: Question) {

    }

    override fun getQuestionList(): LiveData<List<Question>> {
        return questionDataBase.questionDao()?.getQuestionList()!!
    }

    override suspend fun insertAll(questions: List<Question>) {
        questionDataBase.questionDao()?.insertAll(questions)
    }
}