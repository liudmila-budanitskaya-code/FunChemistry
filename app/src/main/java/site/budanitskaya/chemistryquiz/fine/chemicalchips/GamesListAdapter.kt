package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import site.budanitskaya.chemistryquiz.fine.R

class GamesListAdapter(
    private val games: List<Game>,
    private val onItemClick: (Game) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val childRecyclerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return GamesViewHolder(childRecyclerView, games, onItemClick)
    }

    override fun getItemCount(): Int = games.size

    class GamesViewHolder(
        view: View,
        private val games: List<Game>,
        private val onItemClick: (Game) -> Unit

    ) : RecyclerView.ViewHolder(view) {

        private var root: View = view.rootView
        private val itemButton: CircleImageView = root.findViewById(R.id.game_item)
        private val textView = root.findViewById<TextView>(R.id.game_name)

        fun bind(position: Int) {

            itemButton.setOnClickListener {
                onItemClick(games[position])
            }

            itemButton.setImageResource(games[position].drawable)
            textView.text = games[position].name


        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GamesViewHolder) {
            holder.bind(position)
        }
    }
}