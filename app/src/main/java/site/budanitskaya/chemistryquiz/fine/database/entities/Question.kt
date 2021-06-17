package site.budanitskaya.chemistryquiz.fine.database.entities

import androidx.room.*
import site.budanitskaya.chemistryquiz.fine.database.converters.AnswersConverter


@Entity(tableName = "question_table")
@TypeConverters(AnswersConverter::class)
data class Question(
    @PrimaryKey(autoGenerate = true)
    var questionId: Long = 0L,
    @ColumnInfo(name = "question_title")
    var questionTitle: String = "default_question_title",
    @ColumnInfo(name = "answer_options")
    var answers: List<String> = listOf("answer1", "answer2", "answer3", "answer4"),
    @ColumnInfo(name = "topic")
    var topic: String = "default_topic",
    @ColumnInfo(name = "explanation")
    var explanation: String = "default_explanation"
)