package site.budanitskaya.chemistryquiz.fine.database.daos

import androidx.room.*
import site.budanitskaya.chemistryquiz.fine.database.entities.Question

@Dao
interface QuestionDatabaseDao {

    @Insert
    suspend fun insert(question: Question)

    @Update
    suspend fun update(question: Question)

    @Delete
    suspend fun delete(question: Question)

    @Query("SELECT * FROM question_table")
    suspend fun getQuestionList(): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<Question>)

    @Query("SELECT COUNT(question_title) FROM question_table")
    suspend fun getRowCount(): Int

    @Query("SELECT * FROM question_table WHERE topic =:currentTopic")
    suspend fun getQuestionByTopic(currentTopic: String): List<Question>
}