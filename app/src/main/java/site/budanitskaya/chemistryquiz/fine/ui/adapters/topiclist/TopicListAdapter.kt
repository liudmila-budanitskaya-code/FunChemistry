package site.budanitskaya.chemistryquiz.fine.ui.adapters.topiclist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.models.Topic

class TopicListAdapter(
    private val topics: List<Topic>,
    private val onItemClick: (Topic) -> Unit,
    private val numOpenLevels: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val childRecyclerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.circle_item, parent, false)

        return ButtonsViewHolder(childRecyclerView, topics, onItemClick, numOpenLevels)
    }

    override fun getItemCount(): Int = topics.size

    class ButtonsViewHolder(
        view: View,
        private val topics: List<Topic>,
        private val onItemClick: (Topic) -> Unit,
        private val numOpenLevels: Int

    ) : RecyclerView.ViewHolder(view) {

        private var root: View = view.rootView
        private val itemButton: ShapeableImageView = root.findViewById(R.id.game_item)

        fun bind(position: Int) {
/*            if (position == numOpenLevels + 1) {
                itemButton.alpha = 0.2F
            }*/
            if(!topics[position].isOpen){
                itemButton.alpha = 0.2F
            }

            itemButton.setOnClickListener {
                onItemClick(topics[position])
            }
            root.findViewById<TextView>(R.id.topic_name).text = topics[position].name
            root.findViewById<ShapeableImageView>(R.id.game_item)
                .setImageResource(topics[position].drawable)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ButtonsViewHolder) {
            holder.bind(position)
        }
    }

}
