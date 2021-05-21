package site.budanitskaya.chemistryquiz.fine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class QuizListAdapter(
    private val figures: List<Int>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val childRecyclerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.circle_item, parent, false)

        return ButtonsViewHolder(childRecyclerView, figures, onItemClick)
    }

    override fun getItemCount(): Int = figures.size

    class ButtonsViewHolder(
        view: View,
        private val figures: List<Int>,
        private val onItemClick: (Int) -> Unit

    ) : RecyclerView.ViewHolder(view) {

        private val root: View = view.rootView
        private val itemButton: CircleImageView = root.findViewById(R.id.chemistry)

        fun bind(position: Int) {

            itemButton.setOnClickListener {
                onItemClick(figures[position])
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ButtonsViewHolder) {
            holder.bind(position)
        }
    }
}