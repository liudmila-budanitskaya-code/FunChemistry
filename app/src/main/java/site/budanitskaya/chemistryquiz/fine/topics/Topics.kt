package site.budanitskaya.chemistryquiz.fine.topics

import site.budanitskaya.chemistryquiz.fine.R

data class Topic(
    val name: String,
    val drawable: Int
)

val topics: MutableList<Topic> = mutableListOf(
    Topic(
        name = "Acids and bases",
        drawable = R.drawable.acids_and_bases
    ),
    Topic(
        name = "Biochemistry",
        drawable = R.drawable.biochemistry
    ),

    Topic(
        name = "Periodic table",
        drawable = R.drawable.periodic_table
    ),
    Topic(
        name = "Organic chemistry",
        drawable = R.drawable.organic_chemistry
    ),
    Topic(
        name = "Atomic structure",
        drawable = R.drawable.atomic_structure
    ),
    Topic(
        name = "Chemical bonds",
        drawable = R.drawable.chemical_bonds
    ),

    Topic(
        name = "Energy in chemical reactions",
        drawable = R.drawable.energy
    ),
    Topic(
        name = "Redox reactions",
        drawable = R.drawable.redox
    ),
    Topic(
        name = "Electrolytes",
        drawable = R.drawable.electrolytes
    ),
    Topic(
        name = "Structure",
        drawable = R.drawable.structure
    )
    /*
    Topic(
        name = "Acids and bases",
        drawable = R.drawable.acids_and_bases
    ),
    Topic(
        name = "Acids and bases",
        drawable = R.drawable.acids_and_bases
    ),
    Topic(
        name = "Acids and bases",
        drawable = R.drawable.acids_and_bases
    ),
    Topic(
        name = "Acids and bases",
        drawable = R.drawable.acids_and_bases
    )*/
)