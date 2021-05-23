package site.budanitskaya.chemistryquiz.fine.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDatabaseDao {

    @Insert
    suspend fun insert(question: Question)

    @Update
    suspend fun update(question: Question)

    @Delete
    suspend fun delete(question: Question)

    @Query("SELECT * FROM question_table")
    fun getQuestionList(): LiveData<List<Question>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<Question>)
}