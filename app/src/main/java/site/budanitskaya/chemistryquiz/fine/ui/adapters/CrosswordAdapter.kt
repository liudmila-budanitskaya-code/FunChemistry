package site.budanitskaya.chemistryquiz.fine.ui.adapters

import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.constants.LOG_TAG
import site.budanitskaya.chemistryquiz.fine.databinding.CrosswordItemBinding
import site.budanitskaya.chemistryquiz.fine.enums.CellState
import site.budanitskaya.chemistryquiz.fine.models.Square
import site.budanitskaya.chemistryquiz.fine.ui.activities.MainActivity
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.MainViewModel
import kotlin.math.sqrt

class CrosswordAdapter(val viewModel: MainViewModel) :
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
        val viewModel: MainViewModel
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
            setupCrosswordCell(data)
        }

        fun calculateSquareSize(): Int {
            val displaymetrics = DisplayMetrics()
            (binding.root.context as MainActivity).windowManager.defaultDisplay.getMetrics(
                displaymetrics
            )
            return displaymetrics.widthPixels / (1 + sqrt(viewModel.cellList.value?.size?.toDouble()!!).toInt())
        }

        fun getValue(data: Square): String {
            return data!!.value
        }

        private fun setupCrosswordCell(square: Square) {
            Log.d(LOG_TAG, "areContentsTheSame: I am here")

            // adapting cells to the screen size

            val params1: ConstraintLayout.LayoutParams =
                ConstraintLayout.LayoutParams(squareSize, squareSize)
            binding.root.layoutParams = params1
            binding.etTxt.addTextChangedListener(CrosswordCellWatcher(square, viewModel))
        }
    }
}

object Diffcallback : DiffUtil.ItemCallback<Square>() {
    override fun areItemsTheSame(oldItem: Square, newItem: Square): Boolean {
        Log.d(LOG_TAG, "areContentsTheSame: hihi")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Square, newItem: Square): Boolean {
        Log.d(LOG_TAG, "areContentsTheSame: hihi")
        return oldItem.state == newItem.state
    }
}

/*

when(binding.etTxt.text.toString()){
    "" -> {
        Log.d(LOG_TAG, "me: I am here")
        square.state = CellState.EMPTY
        viewModel.updateList(square)
    }
    square.value -> {
        square.state = CellState.CORRECT
        viewModel.updateList(square)
    }
    else -> {
        square.state = CellState.INCORRECT
        viewModel.updateList(square)
    }
}

when (square.state) {
    CellState.CORRECT -> {
        binding.etTxt.background = ResourcesCompat.getDrawable(
            binding.etTxt.resources,
            R.color.green,
            null
        )
    }
    CellState.INCORRECT -> {
        binding.etTxt.background = ResourcesCompat.getDrawable(
            binding.etTxt.resources,
            R.color.pink,
            null
        )
    }
}*/
