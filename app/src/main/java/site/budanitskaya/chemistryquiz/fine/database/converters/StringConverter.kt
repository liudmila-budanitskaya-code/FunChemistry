package site.budanitskaya.chemistryquiz.fine.database.converters

import androidx.room.TypeConverter


class StringConverter {
    @TypeConverter
    fun toOneString(answers: List<String>): String {
        return answers.joinToString(", ")
    }

    @TypeConverter
    fun toOneList(data: String): List<String> {
        return data.split(", ")
    }
}