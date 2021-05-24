package site.budanitskaya.chemistryquiz.fine.database

import androidx.room.*
import java.util.stream.Collectors


@Entity(tableName = "question_table")
@TypeConverters(AnswersConverter::class)
data class Question(
    @PrimaryKey(autoGenerate = true)
    var questionId: Long = 0L,
    @ColumnInfo(name = "question_title")
    var questionTitle: String = "default_question_title",
    @ColumnInfo(name = "answer_options")
    var answers: List<String> = listOf("answer1", "answer2", "answer3", "answer4")
)

class AnswersConverter {
    @TypeConverter
    fun fromAnswers(answers: List<String>): String {
        return answers.joinToString(", ")
    }

    @TypeConverter
    fun toAnswers(data: String): List<String> {
        return data.split(", ")
    }
}