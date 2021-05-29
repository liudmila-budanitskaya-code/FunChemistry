package site.budanitskaya.chemistryquiz.fine.questionscreen

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.QuizItem
import site.budanitskaya.chemistryquiz.fine.database.Question
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase.Companion.getInstance
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionRepository
import site.budanitskaya.chemistryquiz.fine.domain.mapQuestionsToQuizItems
import site.budanitskaya.chemistryquiz.fine.domain.toQuizItem

class QuestionViewModel : ViewModel() {

    val questionRepository by lazy {
        QuestionRepository(getInstance(MainApplication.getApplication()!!))
    }

    var quizItems: MutableList<QuizItem>

    init {
        runBlocking {
            quizItems =
                mapQuestionsToQuizItems(questionRepository.getQuestionList().toMutableList())
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
    }

    fun insertQuestions(questions: List<Question>) {
        viewModelScope.launch {
            questionRepository.insertAll(questions)
        }
    }

    fun setQuestion(currentTopic: String) {
        currentQuestion = getRandomQuestionByTopic(currentTopic).toQuizItem()
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
    }

    fun questionIncremented(currentTopic: String) {
        _questionIndex.value = _questionIndex.value?.plus(1)
        setQuestion(currentTopic)
    }

    fun getRowCount(): Int {
        var rowCount: Int
        runBlocking {
            rowCount = questionRepository.getRowCount()
        }
        return rowCount
    }

    fun getRandomQuestionByTopic(currentTopic: String): Question {
        var question: Question
        runBlocking {
            question = questionRepository.getQuestionByTopic(currentTopic).random()
        }
        return question
    }
}
