package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

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

@BindingAdapter("chip_animate")
fun setAnimation(chip: Chip, rawString: String) {
    chip.animate().rotation(360F).duration = 2700
}

@BindingAdapter("chip_visible")
fun setVisibility(chip: Chip, bool: Boolean) {
    chip.visibility = View.VISIBLE
}



