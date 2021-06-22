package site.budanitskaya.chemistryquiz.fine.models

import site.budanitskaya.chemistryquiz.fine.enums.CellState

data class Square(val id: Int,
                  val value: String,
                  val number: String,
                  var state: CellState
)
