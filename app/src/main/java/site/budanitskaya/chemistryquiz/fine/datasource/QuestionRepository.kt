package site.budanitskaya.chemistryquiz.fine.datasource

import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.database.db.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.database.daos.QuestionDatabaseDao

class QuestionRepository(private val questionDataBase: QuestionDatabase) : QuestionDatabaseDao {

    override suspend fun insert(question: Question) {

    }

    override suspend fun update(question: Question) {

    }

    override suspend fun delete(question: Question) {

    }

    override suspend fun getQuestionList(): List<Question> {
        return questionDataBase.questionDao()?.getQuestionList()!!
    }

    override suspend fun insertAll(questions: List<Question>) {
        questionDataBase.questionDao()?.insertAll(questions)
    }

    override suspend fun getRowCount(): Int {
        return questionDataBase.questionDao()?.getRowCount()!!
    }

    override suspend fun getQuestionByTopic(currentTopic: String): List<Question> {
        return questionDataBase.questionDao()?.getQuestionByTopic(currentTopic)!!
    }


}