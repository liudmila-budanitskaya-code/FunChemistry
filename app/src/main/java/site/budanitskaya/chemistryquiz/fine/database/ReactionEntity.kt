package site.budanitskaya.chemistryquiz.fine.chemicalchips

import androidx.room.*

@Entity(tableName = "reaction_table")
@TypeConverters(StringConverter::class)
data class ReactionEntity(
    @PrimaryKey(autoGenerate = true)
    var reactionId: Long = 0L,
    @ColumnInfo(name = "reagents")
    var reagents: List<String> = listOf("default_reagents"),
    @ColumnInfo(name = "products")
    var products: List<String> = listOf("default_products")
)
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

