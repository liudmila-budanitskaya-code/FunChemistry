package site.budanitskaya.chemistryquiz.fine.database.entities

import androidx.room.*
import site.budanitskaya.chemistryquiz.fine.database.constants.DbConstants
import site.budanitskaya.chemistryquiz.fine.database.converters.StringConverter

@Entity(tableName = DbConstants.DATABASE_REACTION_NAME)
@TypeConverters(StringConverter::class)
data class ReactionEntity(
    @PrimaryKey(autoGenerate = true)
    var reactionId: Long = 0L,
    @ColumnInfo(name = DbConstants.REACTION_FIRST_COLUMN_TITLE)
    var reagents: List<String> = DbConstants.REACTION_DEFAULT_FIRST_COLUMN_TITLE,
    @ColumnInfo(name = DbConstants.REACTION_SECOND_COLUMN_TITLE)
    var products: List<String> = DbConstants.REACTION_DEFAULT_SECOND_COLUMN_TITLE
)

