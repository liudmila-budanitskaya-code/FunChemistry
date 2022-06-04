package site.budanitskaya.chemistryquiz.fine.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.chemistryquiz.fine.R

class ExplanationsAdapter(private val explanations: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val childRecyclerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.explanation_item, parent, false)

        return ExplanationViewHolder(childRecyclerView, explanations)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ExplanationViewHolder){
            holder.bind(position)
        }
    }

    override fun getItemCount() = explanations.size

    class ExplanationViewHolder(
        view: View,
        private val explanations: List<String>

        ) : RecyclerView.ViewHolder(view) {

        private var root: View = view.rootView

        fun bind(position: Int) {
            val explanation: String = explanations[position]
            root.findViewById<TextView>(R.id.explanation_text).text = "${position + 1}. $explanation"
        }
    }
}