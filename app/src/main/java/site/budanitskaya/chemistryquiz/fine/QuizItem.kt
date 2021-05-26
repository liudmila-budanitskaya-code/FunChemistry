package site.budanitskaya.chemistryquiz.fine

data class QuizItem(
    val text: String,
    val answers: List<String>,
    val topic: String
)