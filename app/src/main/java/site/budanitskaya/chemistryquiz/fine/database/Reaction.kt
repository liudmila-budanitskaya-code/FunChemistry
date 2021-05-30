package site.budanitskaya.chemistryquiz.fine.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "reaction_table")
data class Reaction(
    @PrimaryKey(autoGenerate = true)
    var questionId: Long = 0L,
    @ColumnInfo(name = "reaction_title")
    var reactionTitle: String = "default_reaction_title",
)