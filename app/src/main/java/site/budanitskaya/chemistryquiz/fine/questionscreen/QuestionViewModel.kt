package site.budanitskaya.chemistryquiz.fine.questionscreen

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
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

    val questions by lazy {
        getQuestionList()
    }

    val quizItems by lazy {
        generateQuizItems()
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

    fun getQuestionList(): List<Question> {
        val questions = mutableListOf<Question>()
        quizItems.forEach {
            it.toQuestion()
            questions.add(it.toQuestion())
        }
        return questions
/*        viewModelScope.launch {
            questionsList = questionRepository.getQuestionList().value!!
        }*/
    }

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


/*


    fun getQuestionList(): LiveData<List<Question>> {
        viewModelScope.launch {
            questions = questionRepository.getQuestionList()
        }
        return questions
    }*/
}


/*
class QuestionViewModel : ViewModel() {




    val quizItems by lazy {
        extractQuizItems()
    }

    */
/*

    fun insertQuestions(questions: List<Question>) {
        viewModelScope.launch {
            questionRepository.insertAll(questions)
        }
    }

    fun extractQuestions(quizItems: List<QuizItem>): List<Question> {
        val questions = mutableListOf<Question>()
        quizItems.forEach {
            it.toQuestion()
            questions.add(it.toQuestion())
        }
        return questions
    }*//*


    fun extractQuizItems(): List<QuizItem> {
        val quizItems = mutableListOf<QuizItem>()
        questions.forEach {
            it.toQuizItem()
            quizItems.add(it.toQuizItem())
        }
        return quizItems
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
        */
/*quizItems.toMutableList().shuffle()*//*

        _questionIndex.value = 0
        setQuestion()
    }

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


*/
/*    lateinit var questions: LiveData<List<Question>>








    fun getQuestionList(): LiveData<List<Question>> {
        viewModelScope.launch {
            questions = questionRepository.getQuestionList()
        }
        return questions
    }*//*

}*/
