package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.text.Html
import android.text.SpannableString
import android.text.style.RelativeSizeSpan

class StringFormatter {

    companion object{
        fun formatFormula(str: String): SpannableString{
            val spannableString = SpannableString(Html.fromHtml(str))



            return spannableString
        }
    }
}

/* spannableString.setSpan(RelativeSizeSpan(0.6f), 2, 3, 0) // set size

spannableString.setSpan(RelativeSizeSpan(0.6f), 8, 11, 0) // set size

spannableString.setSpan(RelativeSizeSpan(0.6f), 17, 18, 0) // set size

spannableString.setSpan(RelativeSizeSpan(0.6f), 20, 21, 0) // set size*/