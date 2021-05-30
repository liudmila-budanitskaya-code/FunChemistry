package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import site.budanitskaya.chemistryquiz.fine.R

class GamesListAdapter(
    private val topics: List<String>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val childRecyclerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return GamesViewHolder(childRecyclerView, topics, onItemClick)
    }

    override fun getItemCount(): Int = topics.size

    class GamesViewHolder(
        view: View,
        private val topics: List<String>,
        private val onItemClick: (Int) -> Unit

    ) : RecyclerView.ViewHolder(view) {

        private var root: View = view.rootView
        private val itemButton: CircleImageView = root.findViewById(R.id.game_item)

        fun bind(position: Int) {

            itemButton.setOnClickListener {
                onItemClick(position)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GamesViewHolder) {
            holder.bind(position)
        }
    }
}