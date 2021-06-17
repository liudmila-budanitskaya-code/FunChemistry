package site.budanitskaya.chemistryquiz.fine.utils

import android.content.Context
import android.graphics.Color
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.models.toQuestion
import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import java.util.*

fun generateQuizItems(): MutableList<QuizItem> {
    return mutableListOf(
        QuizItem(
            text = "Which of these acids is a weak acid: HF, HCl, HI, or HBr?",
            answers = listOf("HF", "HCl", "HI", "HBr"),
            topic = "Acids and bases",
            explanation = "HF is a really weak acid while the others are strong"
        ),
        QuizItem(
            text = resourceWrapper(R.array.acid_base_q1)[0],
            answers = resourceWrapper(R.array.acid_base_q1)[1].split(", "),
            topic = resourceWrapper(R.array.acid_base_q1)[2],
            explanation = resourceWrapper(R.array.acid_base_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.acid_base_q2)[0],
            answers = resourceWrapper(R.array.acid_base_q2)[1].split(", "),
            topic = resourceWrapper(R.array.acid_base_q2)[2],
            explanation = resourceWrapper(R.array.acid_base_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.acid_base_q3)[0],
            answers = resourceWrapper(R.array.acid_base_q3)[1].split(", "),
            topic = resourceWrapper(R.array.acid_base_q3)[2],
            explanation = resourceWrapper(R.array.acid_base_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.acid_base_q4)[0],
            answers = resourceWrapper(R.array.acid_base_q4)[1].split(", "),
            topic = resourceWrapper(R.array.acid_base_q4)[2],
            explanation = resourceWrapper(R.array.acid_base_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.acid_base_q5)[0],
            answers = resourceWrapper(R.array.acid_base_q5)[1].split(", "),
            topic = resourceWrapper(R.array.acid_base_q5)[2],
            explanation = resourceWrapper(R.array.acid_base_q5)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.bio_q1)[0],
            answers = resourceWrapper(R.array.bio_q1)[1].split(", "),
            topic = resourceWrapper(R.array.bio_q1)[2],
            explanation = resourceWrapper(R.array.bio_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.bio_q2)[0],
            answers = resourceWrapper(R.array.bio_q2)[1].split(", "),
            topic = resourceWrapper(R.array.bio_q2)[2],
            explanation = resourceWrapper(R.array.bio_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.bio_q3)[0],
            answers = resourceWrapper(R.array.bio_q3)[1].split(", "),
            topic = resourceWrapper(R.array.bio_q3)[2],
            explanation = resourceWrapper(R.array.bio_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.bio_q4)[0],
            answers = resourceWrapper(R.array.bio_q4)[1].split(", "),
            topic = resourceWrapper(R.array.bio_q4)[2],
            explanation = resourceWrapper(R.array.bio_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.bio_q5)[0],
            answers = resourceWrapper(R.array.bio_q5)[1].split(", "),
            topic = resourceWrapper(R.array.bio_q5)[2],
            explanation = resourceWrapper(R.array.bio_q5)[3]
        ),
        QuizItem(
            text = "Which is the key enzyme in the biosynthesis of deoxyribonucletides?",
            answers = listOf(
                "Ribonucleotide reductase",
                "deoxyribonuclease",
                "Endonuclease",
                "Exonuclease"
            ),
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
            text = resourceWrapper(R.array.pt_q1)[0],
            answers = resourceWrapper(R.array.pt_q1)[1].split(", "),
            topic = resourceWrapper(R.array.pt_q1)[2],
            explanation = resourceWrapper(R.array.pt_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.pt_q2)[0],
            answers = resourceWrapper(R.array.pt_q2)[1].split(", "),
            topic = resourceWrapper(R.array.pt_q2)[2],
            explanation = resourceWrapper(R.array.pt_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.pt_q3)[0],
            answers = resourceWrapper(R.array.pt_q3)[1].split(", "),
            topic = resourceWrapper(R.array.pt_q3)[2],
            explanation = resourceWrapper(R.array.pt_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.pt_q4)[0],
            answers = resourceWrapper(R.array.pt_q4)[1].split(", "),
            topic = resourceWrapper(R.array.pt_q4)[2],
            explanation = resourceWrapper(R.array.pt_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.pt_q5)[0],
            answers = resourceWrapper(R.array.pt_q5)[1].split(", "),
            topic = resourceWrapper(R.array.pt_q5)[2],
            explanation = resourceWrapper(R.array.pt_q5)[3]
        ),
        QuizItem(
            text = "Which of these compounds i an aromatic compound?",
            answers = listOf("All of these", "Toluene", "Phenolphthalein", "Benzene"),
            topic = "Organic chemistry",
            explanation = "Benzene is the simplest aromatic compound, toluene contains one additional methyl group while phenolphthalein, as the name says, contains phenolic structure in itself "
        ),
        QuizItem(
            text = resourceWrapper(R.array.och_q1)[0],
            answers = resourceWrapper(R.array.och_q1)[1].split(", "),
            topic = resourceWrapper(R.array.och_q1)[2],
            explanation = resourceWrapper(R.array.och_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.och_q2)[0],
            answers = resourceWrapper(R.array.och_q2)[1].split(", "),
            topic = resourceWrapper(R.array.och_q2)[2],
            explanation = resourceWrapper(R.array.och_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.och_q3)[0],
            answers = resourceWrapper(R.array.och_q3)[1].split(", "),
            topic = resourceWrapper(R.array.och_q3)[2],
            explanation = resourceWrapper(R.array.och_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.och_q4)[0],
            answers = resourceWrapper(R.array.och_q4)[1].split(", "),
            topic = resourceWrapper(R.array.och_q4)[2],
            explanation = resourceWrapper(R.array.och_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.och_q5)[0],
            answers = resourceWrapper(R.array.och_q5)[1].split(", "),
            topic = resourceWrapper(R.array.och_q5)[2],
            explanation = resourceWrapper(R.array.och_q5)[3]
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
            answers = listOf(
                "The reason is the very slow kinetics for this process",
                "On the contrary, oxygen is oxidised itself",
                "Oxygen has no enough potential to do this",
                "None of these"
            ),
            topic = "Redox reactions",
            explanation = "Oxygen is potent oxidant and, according to thermodynamic predictions, it must oxidize a lot of things while it does not upon normal conditions. Kinetics is the reason."
        ),

        QuizItem(
            text = resourceWrapper(R.array.redox_q1)[0],
            answers = resourceWrapper(R.array.redox_q1)[1].split(", "),
            topic = resourceWrapper(R.array.redox_q1)[2],
            explanation = resourceWrapper(R.array.redox_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.redox_q2)[0],
            answers = resourceWrapper(R.array.redox_q2)[1].split(", "),
            topic = resourceWrapper(R.array.redox_q2)[2],
            explanation = resourceWrapper(R.array.redox_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.redox_q3)[0],
            answers = resourceWrapper(R.array.redox_q3)[1].split(", "),
            topic = resourceWrapper(R.array.redox_q3)[2],
            explanation = resourceWrapper(R.array.redox_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.redox_q4)[0],
            answers = resourceWrapper(R.array.redox_q4)[1].split(", "),
            topic = resourceWrapper(R.array.redox_q4)[2],
            explanation = resourceWrapper(R.array.redox_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.redox_q5)[0],
            answers = resourceWrapper(R.array.redox_q5)[1].split(", "),
            topic = resourceWrapper(R.array.redox_q5)[2],
            explanation = resourceWrapper(R.array.redox_q5)[3]
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
        ),
        QuizItem(
            text = resourceWrapper(R.array.cb_q1)[0],
            answers = resourceWrapper(R.array.cb_q1)[1].split(", "),
            topic = resourceWrapper(R.array.cb_q1)[2],
            explanation = resourceWrapper(R.array.cb_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.cb_q2)[0],
            answers = resourceWrapper(R.array.cb_q2)[1].split(", "),
            topic = resourceWrapper(R.array.cb_q2)[2],
            explanation = resourceWrapper(R.array.cb_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.cb_q3)[0],
            answers = resourceWrapper(R.array.cb_q3)[1].split(", "),
            topic = resourceWrapper(R.array.cb_q3)[2],
            explanation = resourceWrapper(R.array.cb_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.cb_q4)[0],
            answers = resourceWrapper(R.array.cb_q4)[1].split(", "),
            topic = resourceWrapper(R.array.cb_q4)[2],
            explanation = resourceWrapper(R.array.cb_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.cb_q5)[0],
            answers = resourceWrapper(R.array.cb_q5)[1].split(", "),
            topic = resourceWrapper(R.array.cb_q5)[2],
            explanation = resourceWrapper(R.array.cb_q5)[3]
        ),
        QuizItem(
            text = "What is the effect of a reaction if the energy involved in breaking bonds is less than the energy involved in forming bonds?",
            answers = listOf(
                "There is no enough information to answer the question correctly",
                "The reaction doesn't change the temperature of the surroundings",
                "The reaction makes the surroundings warmer",
                "The reaction makes the surroundings colder"
            ),
            topic = "Energy in chemical reactions",
            explanation = "The energy changes in chemical reactions are not fully described by the temperature changes. There is also entropy that contributes to the overall energy."
        ),

        QuizItem(
            text = "Which of these characteristics explains the potential ability of compounds to react with each other?",
            answers = listOf("Chemical affinity", "Gibbs free energy", "Entropy", "Heat"),
            topic = "Energy in chemical reactions",
            explanation = "Chemical affinity is the real measure of whether chemical substances have the potential to react with each other. However, none of the experimental techniques can measure it accurately."
        ),

        QuizItem(
            text = "What is the energy change for the reaction H–H + F–F → 2H–F?\n" +
                    "    Bond energies: H–H = 436 kJ, F–F = 158 kJ, H–F = 565 kJ",
            answers = listOf("–536 kJ", "536 kJ", "236 kJ", "-236 kJ"),
            topic = "Energy in chemical reactions",
            explanation = "Energy in = 436 + 158 and energy out = 2 × 565, so change is 594 – 1130 = –536 kJ."
        ),
        QuizItem(
            text = "Which of the following processes is endothermic?",
            answers = listOf(
                "Bonds being broken in the reactants",
                "Bonds being made in the products",
                "Any combustion reaction",
                "Any hydrolysis reaction"
            ),
            topic = "Energy in chemical reactions",
            explanation = "Breaking bonds is an endothermic process. Combustion reactions are always exothermic."
        ),
        QuizItem(
            text = "What is the definition of activation energy?",
            answers = listOf(
                "The energy needed to make a reaction starts",
                "The energy needed to break all the bonds in the reactants",
                "The energy produced as the reaction takes place",
                "The energy consumed the reaction takes place"
            ),
            topic = "Energy in chemical reactions",
            explanation = "Activation energy is the minimum energy needed to start a reaction."
        ),
        QuizItem(
            text = resourceWrapper(R.array.crystals_q1)[0],
            answers = resourceWrapper(R.array.crystals_q1)[1].split(", "),
            topic = resourceWrapper(R.array.crystals_q1)[2],
            explanation = resourceWrapper(R.array.crystals_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.crystals_q2)[0],
            answers = resourceWrapper(R.array.crystals_q2)[1].split(", "),
            topic = resourceWrapper(R.array.crystals_q2)[2],
            explanation = resourceWrapper(R.array.crystals_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.crystals_q3)[0],
            answers = resourceWrapper(R.array.crystals_q3)[1].split(", "),
            topic = resourceWrapper(R.array.crystals_q3)[2],
            explanation = resourceWrapper(R.array.crystals_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.crystals_q4)[0],
            answers = resourceWrapper(R.array.crystals_q4)[1].split(", "),
            topic = resourceWrapper(R.array.crystals_q4)[2],
            explanation = resourceWrapper(R.array.crystals_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.crystals_q5)[0],
            answers = resourceWrapper(R.array.crystals_q5)[1].split(", "),
            topic = resourceWrapper(R.array.crystals_q5)[2],
            explanation = resourceWrapper(R.array.crystals_q5)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.as_q1)[0],
            answers = resourceWrapper(R.array.as_q1)[1].split(", "),
            topic = resourceWrapper(R.array.as_q1)[2],
            explanation = resourceWrapper(R.array.as_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.as_q2)[0],
            answers = resourceWrapper(R.array.as_q2)[1].split(", "),
            topic = resourceWrapper(R.array.as_q2)[2],
            explanation = resourceWrapper(R.array.as_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.as_q3)[0],
            answers = resourceWrapper(R.array.as_q3)[1].split(", "),
            topic = resourceWrapper(R.array.as_q3)[2],
            explanation = resourceWrapper(R.array.as_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.as_q4)[0],
            answers = resourceWrapper(R.array.as_q4)[1].split(", "),
            topic = resourceWrapper(R.array.as_q4)[2],
            explanation = resourceWrapper(R.array.as_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.as_q5)[0],
            answers = resourceWrapper(R.array.as_q5)[1].split(", "),
            topic = resourceWrapper(R.array.as_q5)[2],
            explanation = resourceWrapper(R.array.as_q5)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.electrolytes_q1)[0],
            answers = resourceWrapper(R.array.electrolytes_q1)[1].split(", "),
            topic = resourceWrapper(R.array.electrolytes_q1)[2],
            explanation = resourceWrapper(R.array.electrolytes_q1)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.electrolytes_q2)[0],
            answers = resourceWrapper(R.array.electrolytes_q2)[1].split(", "),
            topic = resourceWrapper(R.array.electrolytes_q2)[2],
            explanation = resourceWrapper(R.array.electrolytes_q2)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.electrolytes_q3)[0],
            answers = resourceWrapper(R.array.electrolytes_q3)[1].split(", "),
            topic = resourceWrapper(R.array.electrolytes_q3)[2],
            explanation = resourceWrapper(R.array.electrolytes_q3)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.electrolytes_q4)[0],
            answers = resourceWrapper(R.array.electrolytes_q4)[1].split(", "),
            topic = resourceWrapper(R.array.electrolytes_q4)[2],
            explanation = resourceWrapper(R.array.electrolytes_q4)[3]
        ),
        QuizItem(
            text = resourceWrapper(R.array.electrolytes_q5)[0],
            answers = resourceWrapper(R.array.electrolytes_q5)[1].split(", "),
            topic = resourceWrapper(R.array.electrolytes_q5)[2],
            explanation = resourceWrapper(R.array.electrolytes_q5)[3]
        )
    )
}

fun resourceWrapper(res: Int): Array<String> =
    MainApplication.applicationContext().resources.getStringArray(res)

fun generateRandomColor(): Int {
    val rnd = Random()
    return Color.argb(120, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}

fun mapFromSpannableListToList(quizItems: List<QuizItem>): List<Question> {
    val questions = mutableListOf<Question>()
    quizItems.forEach {
        it.toQuestion()
        questions.add(it.toQuestion())
    }
    return questions
}

fun List<String>.toSpannableList(): List<String>{
    val list: MutableList<String> = mutableListOf()
    for (i in this){
        list.add(StringFormatter.formatFormula(i).toString())
    }
    return list
}

fun colorWrapper(res: Int, context: Context): Int = context.resources.getColor(res)
