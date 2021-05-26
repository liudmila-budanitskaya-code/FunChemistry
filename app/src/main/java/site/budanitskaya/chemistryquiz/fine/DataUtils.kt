package site.budanitskaya.chemistryquiz.fine

import site.budanitskaya.chemistryquiz.fine.database.Question
import kotlin.math.exp


fun generateQuizItems(): MutableList<QuizItem> {
    return mutableListOf(
        QuizItem(
            text = R.string.q1_text.toString(),
            answers = listOf("HF", "HCl", "HI", "HBr"),
            topic = "Acids and bases",
            explanation = "HF is a really weak acid while the others are strong"
        ),
        QuizItem(
            text = "Which of these indicators is useless foe detecting acids?",
            answers = listOf("Cresolphthalein", "Congo red", "Methyl yellow", "Bromocresol green"),
            topic = "Acids and bases",
            explanation = "All phthaleins in aqueous solutions are insensible towards acids, so the correct answer is cresolphthalein"
        ),
        QuizItem(
            text = resourceWrapper(R.array.biochemistry_q1)[0],
            answers = resourceWrapper(R.array.biochemistry_q1)[1].split(", "),
            topic = resourceWrapper(R.array.biochemistry_q1)[2],
            explanation = resourceWrapper(R.array.biochemistry_q1)[3]
        ),
        QuizItem(
            text = "Which is the key enzyme in the biosynthesis of deoxyribonucletides?",
            answers = listOf("Ribonucleotide reductase", "deoxyribonuclease", "Endonuclease", "Exonuclease"),
            topic = "Biochemistry",
            explanation = "Ribonucleotide reductase of course. This enzyme catalyses the conversion of diphosphate ribonucleotides into diphosphate deoxyribonucleotides."
        ),
        QuizItem(
            text = "Which of these element pairs are not similar in their chemical and physical properties?",
            answers = listOf("Sr and Ba", "Al and Be", "B and Si", "Li and Mg"),
            topic = "Periodic table",
            explanation = "Of these element pairs, only Sr and Ba do not possess diagonal likelikhood. This is the fact that along the main diagonal of the periodic table, two nearest elements are similar in their properties."
        ),
        QuizItem(
            text = "Which of these compounds i an aromatic compound?",
            answers = listOf("All of these", "Toluene", "Phenolphthalein", "Benzene"),
            topic = "Organic chemistry",
            explanation = "Benzene is the simplest aromatic compound, toluene contains one additional methyl group while phenolphthalein, as the name says, contains phenolic structure in itself "
        ),
        QuizItem(
            text = "Which of the following compounds do not contain molecular bonds?",
            answers = listOf("NaCl", "Organic salts", "All inorganic salts", "All of these"),
            topic = "Chemical bonds",
            explanation = "Inorganic salts contain complex acid residues more often than not which is even more common for organic salts. NaCl, a simplest inorganic salt, however, does not contain molecular bonds. So the answers is..."
        ),
        QuizItem(
            text = "Which element provides a model for Schrödinger equation?",
            answers = listOf("H", "B", "Si", "None of these"),
            topic = "Atomic structure",
            explanation = "Atoms other than that of hydrogen can not be described using Schrödinger equation due to fact that hydrogen atom is the simplest one that's why it is a suitable model to study such complex things as atoms."
        ),
        QuizItem(
            text = "Oxygen does not oxidize everything upon normal conditions. What is a probable explanation for this fact?",
            answers = listOf("The reason is the very slow kinetics for this process", "On the contrary, oxygen is oxidised itself", "Oxygen has no enough potential to do this", "None of these"),
            topic = "Redox reactions",
            explanation = "Oxygen is potent oxidant and, according to thermodynamic predictions, it must oxidize a lot of things while it does not upon normal conditions. Kinetics is the reason."
        ),
        QuizItem(
            text = "Which of these characteristics explains the potential ability of compounds to react with each other?",
            answers = listOf("Chemical affinity", "Gibbs free energy", "Entropy", "Heat"),
            topic = "Energy in chemical reactions",
            explanation = "Chemical affinity is the real measure of whether chemical substances have the potential to react with each other. However, none of the experimental techniques can measure it accurately."
        ),
        QuizItem(
            text = "Which of these compounds is a strong electrolyte?",
            answers = listOf("RbBr", "NH4Cl", "Ammonia", "HF"),
            topic = "Electrolytes",
            explanation = "A salt containing a cation of alkaline metal and a halogen anion would be the best match. And we have such answer options, this is RbBr. ."
        ),
        QuizItem(
            text = "Which of these substances has a 2D hexagonal crystalline structure?",
            answers = listOf("Graphen", "BN", "Diamond", "NaCl"),
            topic = "Crystals",
            explanation = "Graphen consists of graphite slices and thus it contains a 2D hexagonal crystalline structure."
        )
    )
}

fun resourceWrapper(res: Int): Array<String> = MainApplication.applicationContext().resources.getStringArray(res)
