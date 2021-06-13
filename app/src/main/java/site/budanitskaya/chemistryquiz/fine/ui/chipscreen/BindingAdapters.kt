package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import android.text.Html
import android.text.SpannableString
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("spannable_text")
fun setSpannableText(txtView: TextView, rawString: String) {
    txtView.setText(
        SpannableString(Html.fromHtml(rawString)),
        TextView.BufferType.SPANNABLE
    )
}


@BindingAdapter("spannable_chip_text")
fun setSpannableChipText(chip: Chip, rawString: String) {
    chip.setText(
        SpannableString(Html.fromHtml(rawString)),
        TextView.BufferType.SPANNABLE
    )
}



