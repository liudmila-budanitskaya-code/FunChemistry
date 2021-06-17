package site.budanitskaya.chemistryquiz.fine.datasource

import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.database.db.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.database.daos.QuestionDatabaseDao
import site.budanitskaya.chemistryquiz.fine.database.daos.ReactionsDatabaseDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepository @Inject constructor(private val questionDatabaseDao: QuestionDatabaseDao?) :
    QuestionDatabaseDao {

    override suspend fun insert(question: Question) {

    }

    override suspend fun update(question: Question) {

    }

    override suspend fun delete(question: Question) {

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