package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.text.SpannableString

data class SpannableReaction(
    val reagents: List<SpannableString>,
    val products: List<SpannableString>
)