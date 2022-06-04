package site.budanitskaya.chemistryquiz.fine.database.entities

import androidx.room.*
import site.budanitskaya.chemistryquiz.fine.database.constants.DbConstants
import site.budanitskaya.chemistryquiz.fine.database.converters.AnswersConverter


@Entity(tableName = DbConstants.DATABASE_QUESTION_NAME)
@TypeConverters(AnswersConverter::class)
data class Question(
    @PrimaryKey(autoGenerate = true)
    var questionId: Long = 0L,
    @ColumnInfo(name = DbConstants.FIRST_COLUMN_TITLE)
    var questionTitle: String = DbConstants.DEFAULT_FIRST_COLUMN_TITLE,
    @ColumnInfo(name = DbConstants.SECOND_COLUMN_TITLE)
    var answers: List<String> = DbConstants.DEFAULT_SECOND_COLUMN_TITLE,
    @ColumnInfo(name = DbConstants.THIRD_COLUMN_TITLE)
    var topic: String = DbConstants.DEFAULT_THIRD_COLUMN_TITLE,
    @ColumnInfo(name = DbConstants.FOURTH_COLUMN_TITLE)
    var explanation: String = DbConstants.DEFAULT_FOURTH_COLUMN_TITLE
)