package site.budanitskaya.chemistryquiz.fine.database.entities

import androidx.room.*
import site.budanitskaya.chemistryquiz.fine.database.converters.StringConverter

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

