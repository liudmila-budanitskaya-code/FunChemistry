package site.budanitskaya.chemistryquiz.fine.ui.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionDatasource
import site.budanitskaya.chemistryquiz.fine.utils.mapQuestionsToQuizItems
import site.budanitskaya.chemistryquiz.fine.lists.topics
import site.budanitskaya.chemistryquiz.fine.utils.toQuizItem
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(val preference: SharedPreferences, val questionDatasource: QuestionDatasource) : ViewModel() {

    var numOfOpenLevels = preference.getInt("key_level", 1)

    private var _totalScore = MutableLiveData(0)
    val totalScore: LiveData<Int>
        get() = _totalScore

    fun resetScoreToZero(){
        _totalScore.value = 0
    }


    fun updateLevel(id: Int) {
        if(totalScore.value == 4){
            val oldLevel =
                preference.getInt("key_level", 0)
            preference.edit()
                .putInt("key_level", oldLevel + 1).apply()
            val newTopic = topics.filter { it.id == id + 1 }
            if (!newTopic[0].isOpen) {
                newTopic[0].isOpen = true
            }
        }
    }

    fun addToTotalScore() {
        _totalScore.value = _totalScore.value?.plus(1)
    }

    fun subtractFromScore() {
        _totalScore.value = _totalScore.value?.minus(1)
    }


    var quizItems: MutableList<QuizItem>

    init {
        runBlocking {
            quizItems =
                mapQuestionsToQuizItems(questionDatasource.getQuestionList().toMutableList())
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
            questionDatasource.insertAll(questions)
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

    fun getRandomQuestionByTopic(currentTopic: String): Question {
        var question: Question
        runBlocking {
            question = questionDatasource.getQuestionByTopic(currentTopic).random()
        }
        return question
    }
}