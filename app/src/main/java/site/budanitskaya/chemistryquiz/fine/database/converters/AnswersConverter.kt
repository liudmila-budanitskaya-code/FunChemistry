package site.budanitskaya.chemistryquiz.fine.database.converters

import androidx.room.TypeConverter


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