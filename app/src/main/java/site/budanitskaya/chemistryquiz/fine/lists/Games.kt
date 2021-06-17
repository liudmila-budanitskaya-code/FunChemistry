package site.budanitskaya.chemistryquiz.fine.lists

import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.models.Game

val games: MutableList<Game> = mutableListOf(
    Game(
        id = 1,
        name = "Chemical chips",
        drawable = R.drawable.chips
    ),
    Game(
        id = 2,
        name = "Chemical crossword",
        drawable = R.drawable.crossword
    )
)