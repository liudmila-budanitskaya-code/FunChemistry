package site.budanitskaya.chemistryquiz.fine

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class IntermittentSpanLayoutManager (context: Context, span: Int): GridLayoutManager(context, span){

    override fun getSpanSizeLookup(): SpanSizeLookup {
        return object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {

                return when (position % 3) {
                    0 -> 0
                    1 -> 2
                    2 -> 1
                    else -> throw Exception()
                }
            }
        }
    }

    override fun setSpanSizeLookup(spanSizeLookup: SpanSizeLookup?) {
        super.setSpanSizeLookup(spanSizeLookup)
    }
}