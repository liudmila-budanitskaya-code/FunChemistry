package site.budanitskaya.chemistryquiz.fine.ui.bindingadapters

import android.text.Html
import android.text.SpannableString
import android.view.View
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


@BindingAdapter("chip_visible")
fun setVisibility(chip: Chip, bool: Boolean) {
    chip.visibility = View.VISIBLE
}


