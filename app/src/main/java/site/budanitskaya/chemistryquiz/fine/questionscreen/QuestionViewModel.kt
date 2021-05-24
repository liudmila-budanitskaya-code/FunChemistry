package site.budanitskaya.chemistryquiz.fine.questionscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.QuizItem
import site.budanitskaya.chemistryquiz.fine.database.Question
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase.Companion.getInstance
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionRepository
import site.budanitskaya.chemistryquiz.fine.generateQuizItems

class QuestionViewModel : ViewModel() {

    val quizItems by lazy {
        generateQuizItems()
    }


/*    lateinit var questions: LiveData<List<Question>>

    private val questionRepository by lazy {
        QuestionRepository(getInstance(MainApplication.getApplication()!!))
    }

    fun getQuestionsToPut(quizItems: List<QuizItem>): List<Question> {
        val questions = mutableListOf<Question>()
         quizItems.forEach {
            it.toQuestion()
             questions.add(it.toQuestion())
        }
        return questions
    }


    fun insertQuestions(questions: List<Question>) {
        viewModelScope.launch {
            questionRepository.insertAll(questions)
        }
    }

    fun getQuestionList(): LiveData<List<Question>> {
        viewModelScope.launch {
            questions = questionRepository.getQuestionList()
        }
        return questions
    }*/
}
