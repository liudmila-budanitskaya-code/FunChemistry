package site.budanitskaya.chemistryquiz.fine.topics

import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.domain.Topic

val topics: MutableList<Topic> = mutableListOf(
    Topic(
        id = 1,
        name = "Acids and bases",
        drawable = R.drawable.acids_and_bases
    ),
    Topic(
        id = 2,
        name = "Biochemistry",
        drawable = R.drawable.biochemistry
    ),

    Topic(
        id = 3,
        name = "Periodic table",
        drawable = R.drawable.periodic_table
    ),
    Topic(
        id = 4,
        name = "Organic chemistry",
        drawable = R.drawable.organic_chemistry
    ),
    Topic(
        id= 5,
        name = "Atomic structure",
        drawable = R.drawable.atomic_structure
    ),
    Topic(
        id = 6,
        name = "Chemical bonds",
        drawable = R.drawable.chemical_bonds
    ),

    Topic(
        id = 7,
        name = "Energy in chemical reactions",
        drawable = R.drawable.energy
    ),
    Topic(
        id = 8,
        name = "Redox reactions",
        drawable = R.drawable.redox
    ),
    Topic(
        id = 9,
        name = "Electrolytes",
        drawable = R.drawable.electrolytes
    ),
    Topic(
        id = 10,
        name = "Crystals",
        drawable = R.drawable.crystals
    )
)