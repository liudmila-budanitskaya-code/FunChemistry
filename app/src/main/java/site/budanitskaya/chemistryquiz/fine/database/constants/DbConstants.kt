package site.budanitskaya.chemistryquiz.fine.database.constants

object DbConstants {
    const val DEFAULT_DELIMITER = ", "

    // Question database
    const val DATABASE_QUESTION_NAME = "question_table"
    const val FIRST_COLUMN_TITLE = "question_title"
    const val DEFAULT_FIRST_COLUMN_TITLE = "default_question_title"
    const val SECOND_COLUMN_TITLE = "answer_options"
    val DEFAULT_SECOND_COLUMN_TITLE = listOf("answer1", "answer2", "answer3", "answer4")
    const val THIRD_COLUMN_TITLE = "topic"
    const val DEFAULT_THIRD_COLUMN_TITLE = "default_topic"
    const val FOURTH_COLUMN_TITLE = "explanation"
    const val DEFAULT_FOURTH_COLUMN_TITLE = "default_explanation"

    // Reaction database
    const val DATABASE_REACTION_NAME = "reaction_table"
    const val REACTION_FIRST_COLUMN_TITLE = "reagents"
    val REACTION_DEFAULT_FIRST_COLUMN_TITLE = listOf("default_reagents")
    const val REACTION_SECOND_COLUMN_TITLE = "products"
    val REACTION_DEFAULT_SECOND_COLUMN_TITLE = listOf("default_products")

}