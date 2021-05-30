package site.budanitskaya.chemistryquiz.fine.chemicalchips

import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.domain.Topic

val games: MutableList<Game> = mutableListOf(
    Game(
        id = 1,
        name = "Chemical chips",
        drawable = R.drawable.chips
    ),
    Game(
        id = 2,
        name = "Wise Koa",
        drawable = R.drawable.snake
    ),

    Game(
        id = 3,
        name = "Guess the scientist",
        drawable = R.drawable.scientist
    )
)