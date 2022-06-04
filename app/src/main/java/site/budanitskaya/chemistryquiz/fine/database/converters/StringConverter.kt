package site.budanitskaya.chemistryquiz.fine.database.converters

import androidx.room.TypeConverter
import site.budanitskaya.chemistryquiz.fine.database.constants.DbConstants.DEFAULT_DELIMITER


class StringConverter {
    @TypeConverter
    fun toOneString(answers: List<String>): String {
        return answers.joinToString(DEFAULT_DELIMITER)
    }

    @TypeConverter
    fun toOneList(data: String): List<String> {
        return data.split(DEFAULT_DELIMITER)
    }
}