package site.budanitskaya.chemistryquiz.fine.utils

import android.text.Html
import android.text.SpannableString

class StringFormatter {
    companion object{
        fun formatFormula(str: String): SpannableString {
            return SpannableString(Html.fromHtml(str))
        }
    }
}

