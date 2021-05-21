package site.budanitskaya.chemistryquiz.fine

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when (parent.getChildLayoutPosition(view) % 3) {
            1 -> outRect.left = space
            2 -> outRect.left = space/20
        }
    }
}