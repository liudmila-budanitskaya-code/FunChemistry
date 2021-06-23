package site.budanitskaya.chemistryquiz.fine.ui.bindingadapters

import android.text.Html
import android.text.InputFilter
import android.text.SpannableString
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.models.Square
import site.budanitskaya.chemistryquiz.fine.utils.paintString
import kotlin.math.sqrt

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

@BindingAdapter("cell_style")
fun setView(etTxt: EditText, squareTxt: String){
    with(etTxt){
        if (squareTxt == "") {
            background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.not_crossword_square,
                null
            )
            filters = arrayOf(
                InputFilter { src, start, end, dst, dstart, dend ->
                    if (src.isEmpty()) dst.subSequence(
                        dstart,
                        dend
                    ) else ""
                }
            )
            isCursorVisible = false
            showSoftInputOnFocus = false
        } else {
            background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.crossword_square,
                null
            )
        }
    }
}

@BindingAdapter("app:spannable_color")
fun display(textView: TextView, string: String){
    val text = paintString(string)
    textView.setText(text, TextView.BufferType.SPANNABLE)
}

@BindingAdapter("setup_crossword")
fun setup(recyclerView: RecyclerView, squareList: LiveData<List<Square>>){
    recyclerView.layoutManager =
        GridLayoutManager(recyclerView.context, sqrt(squareList.value?.size?.toDouble()!!).toInt())
    recyclerView.setHasFixedSize(true)
}

@BindingAdapter("adapter")
fun setupAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>){
    recyclerView.adapter = adapter
}
