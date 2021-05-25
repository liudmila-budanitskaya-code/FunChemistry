package site.budanitskaya.chemistryquiz.fine.questionscreen

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.QuizItem
import site.budanitskaya.chemistryquiz.fine.database.Question
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase.Companion.getInstance
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionRepository
import site.budanitskaya.chemistryquiz.fine.generateQuizItems

class QuestionViewModel : ViewModel() {

    val questionRepository by lazy {
        QuestionRepository(getInstance(MainApplication.getApplication()!!))
    }

    var quizItems: MutableList<QuizItem>

    init {
        runBlocking {
            quizItems = mapQuestionsToQuizItems(questionRepository.getQuestionList().toMutableList())
            delay(0L)
        }
    }

    lateinit var currentQuestion: QuizItem
    lateinit var answers: MutableList<String>


    private var _questionIndex = MutableLiveData<Int>(0)
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    val numQuestions by lazy {
        Math.min((quizItems.size + 1) / 2, 3)
    }

    fun shuffleQuestions() {
        quizItems.shuffle()
        _questionIndex.value = 0
        setQuestion()
    }

    fun insertQuestions(questions: List<Question>) {
        viewModelScope.launch {
            questionRepository.insertAll(questions)
        }
    }

    fun getQuestionList(quizItems: List<QuizItem>): List<Question> {
        val questions = mutableListOf<Question>()
        quizItems.forEach {
            it.toQuestion()
            questions.add(it.toQuestion())
        }
        return questions
    }

    fun mapQuestionsToQuizItems(questions: List<Question>): MutableList<QuizItem> {
        val quizItems = mutableListOf<QuizItem>()
        questions.forEach {
            it.toQuizItem()
            quizItems.add(it.toQuizItem())
        }
        return quizItems
    }
/*        viewModelScope.launch {
            questionsList = questionRepository.getQuestionList().value!!
        }*/


    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    fun setQuestion() {
        currentQuestion = quizItems[questionIndex.value!!]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
    }

    fun questionIncremented() {
        _questionIndex.value = _questionIndex.value?.plus(1)
    }
}
