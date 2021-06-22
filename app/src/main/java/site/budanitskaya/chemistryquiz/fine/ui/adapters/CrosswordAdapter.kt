package site.budanitskaya.chemistryquiz.fine.ui.adapters

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.chemistryquiz.fine.databinding.CrosswordItemBinding
import site.budanitskaya.chemistryquiz.fine.models.Square
import site.budanitskaya.chemistryquiz.fine.ui.activities.MainActivity
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.MainViewModel
import kotlin.math.sqrt

class CrosswordAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Square, CrosswordAdapter.CrosswordDataViewHolder>(Diffcallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrosswordDataViewHolder {

        return CrosswordDataViewHolder(
            CrosswordItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), viewModel
        )
    }

    override fun onBindViewHolder(holder: CrosswordDataViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data!!)
    }

    class CrosswordDataViewHolder(
        private var binding:
        CrosswordItemBinding,
        private val viewModel: MainViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {

        val squareSize =
            calculateSquareSize()

        fun bind(
            data: Square
        ) {

            binding.data = data
            binding.holder = this
            binding.executePendingBindings()
            setupCrosswordCell()
        }

        fun calculateSquareSize(): Int {
            val displaymetrics = DisplayMetrics()
            (binding.root.context as MainActivity).windowManager.defaultDisplay.getMetrics(
                displaymetrics
            )
            return displaymetrics.widthPixels / (1 + sqrt(viewModel.cellList.value?.size?.toDouble()!!).toInt())
        }

        fun getValue(): String {
            return viewModel.cellList.value?.get(adapterPosition)!!.value
        }

        private fun setupCrosswordCell() {

            // adapting cells to the screen size

            val params1: ConstraintLayout.LayoutParams =
                ConstraintLayout.LayoutParams(squareSize, squareSize)
            binding.root.layoutParams = params1

            binding.etTxt.addTextChangedListener(
                CrosswordCellWatcher(
                    binding.etTxt,
                    viewModel.cellList.value?.get(adapterPosition)!!
                )
            )
        }
    }
}

object Diffcallback : DiffUtil.ItemCallback<Square>() {
    override fun areItemsTheSame(oldItem: Square, newItem: Square): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Square, newItem: Square): Boolean {
        return oldItem.state == newItem.state
    }
}