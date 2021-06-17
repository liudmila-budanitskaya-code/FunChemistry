package site.budanitskaya.chemistryquiz.fine.datasource

import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.database.daos.QuestionDatabaseDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionDatasource @Inject constructor(private val questionDatabaseDao: QuestionDatabaseDao?) :
    QuestionDatabaseDao {

    override suspend fun insert(question: Question) {
        questionDatabaseDao?.insert(question)
    }

    override suspend fun update(question: Question) {
        questionDatabaseDao?.update(question)
    }

    override suspend fun delete(question: Question) {
        questionDatabaseDao?.delete(question)
    }

    override suspend fun getQuestionList(): List<Question> {
        return questionDatabaseDao?.getQuestionList()!!
    }

    override suspend fun insertAll(questions: List<Question>) {
        questionDatabaseDao?.insertAll(questions)
    }

    override suspend fun getRowCount(): Int {
        return questionDatabaseDao?.getRowCount()!!
    }

    override suspend fun getQuestionByTopic(currentTopic: String): List<Question> {
        return questionDatabaseDao?.getQuestionByTopic(currentTopic)!!
    }
}