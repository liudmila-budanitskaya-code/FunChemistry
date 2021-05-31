package site.budanitskaya.chemistryquiz.fine.chemicalchips

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReactionsDatabaseDao {

    @Insert
    suspend fun insert(reaction: ReactionEntity)

    @Update
    suspend fun update(reaction: ReactionEntity)

    @Delete
    suspend fun delete(reaction: ReactionEntity)

    @Query("SELECT * FROM reaction_table")
    fun getReactionList(): List<ReactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reactions: List<ReactionEntity>)

}