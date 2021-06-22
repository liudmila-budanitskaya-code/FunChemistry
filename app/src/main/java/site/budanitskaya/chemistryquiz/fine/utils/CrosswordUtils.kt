package site.budanitskaya.chemistryquiz.fine.utils

import site.budanitskaya.chemistryquiz.fine.enums.CellState
import site.budanitskaya.chemistryquiz.fine.models.Square

val mainCrossword =
    "             V    " +
            "        SILICIUM  " +
            "             T    " +
            " TARING      A  S " +
            "      L F CHEMICAL" +
            "    PROTON Y I  L " +
            " A    V R  O N  T " +
            " TRIPLE M  R      " +
            "GOGGLES U  O CYAN " +
            " M A X  L  X O    " +
            "   S POTASSIUM    " +
            "     EZ    D P    " +
            "  MICROSCOPE O B  " +
            "     IN      U E  " +
            "HYGROMETER   N A  " +
            "     E       D K  " +
            "   ION         E  " +
            "     THERMOMETER  "

val answersList =
    listOf(
        "The common name for substances essential for life",
        "The fourteenth element of the periodic table",
        "Distracting the weight of a container or wrapper that is deducted from the gross weight to obtain net weight",
        "You should put them on before working in a chemical laboratory",
        "The generic name of substances made of cation and anion, which a neither acid or base",
        "Pattern used to generify calculations and to make them easier",
        "Something that is characterized by chemistry by nature",
        "Substance having oxygen and hydrogen atoms in its structure",
        "Particle which is the main contributor to the position of an element in the periodic table",
        "The minimum quantity of matter that represents a chemical element",
        "The characteristic of a bond inside the molecule of gaseous nitrogen",
        "You should put them on before working in a chemical laboratory",
        "The name of copper(II) sulfate color",
        "Micro element taking part in neural conduction",
        "Magnifying equipment used to study the form of crystals",
        "A sort of glassware used almost in every chemical experiment",
        "A piece of equipment used to measure humidity",
        "Formed upon salt dissolution",
        "Equipment used to measure temperature"
    )
val answerNumberList = listOf(
    13,
    26,
    55,
    60,
    70,
    80,
    82,
    83,
    94,
    109,
    127,
    144,
    157,
    185,
    218,
    231,
    252,
    291,
    311
)

fun generateSquareListFromString(sourceString: String): List<Square> {
    val list = mutableListOf<Square>()
    val initialList = sourceString.toList()
    for (i in initialList.indices) {
        if (initialList[i] != ' ') {
            val value = initialList[i]
            if (answerNumberList.contains(i)) {
                val index = answerNumberList.indexOf(i)
                list.add(Square(i, value.toString(), (index + 1).toString(), CellState.EMPTY))
            } else {
                list.add(Square(i, value.toString(), "", CellState.EMPTY))
            }

        } else {
            list.add(Square(i, "", "", CellState.EMPTY))
        }
    }
    return list.toList()

}
