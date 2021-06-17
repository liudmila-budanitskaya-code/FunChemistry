package site.budanitskaya.chemistryquiz.fine.domain

data class Reaction(
    val reagents: List<String>,
    val answers: List<String>,
    val correctProducts: List<String>
)