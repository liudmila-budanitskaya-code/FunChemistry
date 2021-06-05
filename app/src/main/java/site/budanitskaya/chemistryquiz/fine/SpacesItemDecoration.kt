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
            0 -> {
                outRect.left = space
                outRect.right = space
            }
        }
    }
}

/*

override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    var position = parent.getChildViewHolder(view)?.adapterPosition
    if (position == RecyclerView.NO_POSITION) {
        val oldPosition = parent.getChildViewHolder(view)?.oldPosition
        if (oldPosition == RecyclerView.NO_POSITION) return
        position = oldPosition
    }

    when (parent.getChildViewHolder(view)?.itemViewType?.rem(3)) {
        //do your outRect here
        1 -> outRect.left = space
        2 -> outRect.left = space/20
    }
}*/
