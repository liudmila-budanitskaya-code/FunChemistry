package site.budanitskaya.chemistryquiz.fine

import site.budanitskaya.chemistryquiz.fine.database.Question


fun generateQuizItems(): MutableList<QuizItem> {
    return mutableListOf(
        QuizItem(
            text = "What is Android Jetpack?",
            answers = listOf("All of these", "Tools", "Documentation", "Libraries"),
            topic = "Acids and bases"
        ),
        QuizItem(
            text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot"),
            topic = "Biochemistry"
        ),
        QuizItem(
            text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout"),
            topic = "Periodic table"
        ),
        QuizItem(
            text = "What do you use to push structured data into a layout?",
            answers = listOf("Data binding", "Data pushing", "Set text", "An OnClick method"),
            topic = "Organic chemistry"
        ),
        QuizItem(
            text = "What method do you use to inflate layouts in fragments?",
            answers = listOf(
                "onCreateView()",
                "onActivityCreated()",
                "onCreateLayout()",
                "onInflateLayout()"
            ),
            topic = "Atomic structure"
        ),
        QuizItem(
            text = "What's the build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle"),
            topic = "Chemical bonds"
        ),
        QuizItem(
            text = "Which class do you use to create a vector drawable?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            ),
            topic = "Energy in chemical reactions"
        ),
        QuizItem(
            text = "Which one of these is an Android navigation component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher"),
            topic = "Redox reactions"
        ),
        QuizItem(
            text = "Which XML element lets you register an activity with the launcher activity?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher"),
            topic = "Electrolytes"
        ),
        QuizItem(
            text = "What do you use to mark a layout for data binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"),
            topic = "Structure"
        )
    )
}
