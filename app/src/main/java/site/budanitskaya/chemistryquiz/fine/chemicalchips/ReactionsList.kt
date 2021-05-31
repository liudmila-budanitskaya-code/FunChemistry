package site.budanitskaya.chemistryquiz.fine.chemicalchips

import site.budanitskaya.chemistryquiz.fine.domain.Reaction

fun generateReactionsList(): MutableList<Reaction> {
    return mutableListOf(
        Reaction(
            listOf("Zn", "CuSO<sub size = 2>4</sub>"),
            listOf(
                "Cu",
                "ZnSO<sub size = 2>4</sub>",
                "CuZnO<sub size = 2>2</sub>",
                "O<sub size = 2>2</sub>",
                "CuSO<sub size = 2>3</sub>"
            )
        ),
        Reaction(
            listOf("Ba(OH)<sub>2</sub>", "H<sub>2</sub>SO<sub size = 2>4</sub>"),
            listOf(
                "BaSO<sub>4</sub>",
                "H<sub>2</sub>O",
                "BaS",
                "BaSO<sub>3</sub>",
                "BaSO<sub>2</sub>"
            )
        ),

        Reaction(
            listOf("KCl", "AgNO>3</sub>"),
            listOf(
                "AgCl",
                "KNO>3</sub>",
                "AgClO>3</sub>",
                "KNO>2</sub>",
                "KClO<sub>3</sub>"
            )
        ),
        Reaction(
            listOf("H<sub>2</sub>O", "CO<sub>2</sub>"),
            listOf(
                "O<sub>2</sub>",
                "C<sub>6</sub>H<sub>12</sub>O<sub>6</sub>",
                "C<sub>6</sub>H<sub>5</sub>OH",
                "HCOOH",
                "CH<sub>3</sub>COOH"
            )
        ),
        Reaction(
            listOf("CHCl<sub>3</sub>", "Cl<sub>2</sub>"),
            listOf(
                "HCl",
                "CCl<sub>4</sub>",
                "CCl<sub>2</sub>",
                "C<sub>2</sub>Cl<sub>4</sub>",
                "C<sub>6</sub>Cl<sub>6</sub>"
            )
        ),
        Reaction(
            listOf("CH<sub>3</sub>CONH<sub>2</sub>", "NaClO"),
            listOf(
                "CH<sub>3</sub>NH<sub>2</sub>",
                "CO<sub>2</sub>",
                "CH<sub>3</sub>OH",
                "CH<sub>3</sub>Cl",
                "N<sub>2</sub>H<sub>4</sub>"
            )
        ),
        Reaction(
            listOf("CH<sub>3</sub>CN", "LiAlH<sub>4</sub>"),
            listOf(
                "CH<sub>3</sub>NH<sub>2</sub>",
                "Li[Al(OH)<sub>4</sub>]",
                "CH<sub>3</sub>OH",
                "CH<sub>3</sub>COOLi",
                "C<sub>2</sub>H<sub>6</sub>"
            )
        ),
        Reaction(
            listOf("CH<sub>3</sub>COCl", "NaN<sub>3</sub>"),
            listOf(
                "CH<sub>3</sub>NCO",
                "N<sub>2</sub>",
                "CH<sub>3</sub>CNO",
                "CH<sub>3</sub>COONa",
                "C<sub>2</sub>H<sub>6</sub>"
            )
        ),
        Reaction(
            listOf("C<sub>6</sub>H<sub>6</sub>", "N<sub>2</sub>O<sub>4</sub>"),
            listOf(
                "CH<sub>6</sub>H<sub>5</sub>NO<sub>2</sub>",
                "H<sub>2</sub>0",
                "C<sub>6</sub>H<sub>4</sub>N<sub>2</sub>O<sub>4</sub>",
                "C<sub>6</sub>H<sub>3</sub>N<sub>3</sub>O<sub>6</sub>",
                "C<sub>6</sub>H<sub>2</sub>N<sub>4</sub>O<sub>8</sub>"
            )
        )

    )
}