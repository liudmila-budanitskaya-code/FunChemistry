package site.budanitskaya.chemistryquiz.fine.crossword

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.databinding.DataBindingUtil
import org.akop.ararat.core.Crossword
import org.akop.ararat.core.buildCrossword
import org.akop.ararat.io.PuzFormatter
import org.akop.ararat.view.CrosswordView
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentChemChipsQuestionBinding
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentCrosswordBinding

class CrosswordFragment : Fragment(), CrosswordView.OnLongPressListener, CrosswordView.OnStateChangeListener, CrosswordView.OnSelectionChangeListener {

    private var crosswordView: CrosswordView? = null
    private var hint: TextView? = null

    private lateinit var binding: FragmentCrosswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_crossword, container, false
        )
        val crossword = readPuzzle(R.raw.puzzle)
        hint = binding.hint
        crosswordView = binding.crossword
        crosswordView!!.let { cv ->
            cv.crossword = readPuzzle(R.raw.puzzle)
            cv.crossword = crossword
            cv.onLongPressListener = this
            cv.onStateChangeListener = this
            cv.onSelectionChangeListener = this
            cv.inputValidator = { ch -> !ch.first().isISOControl() }
            cv.undoMode = CrosswordView.UNDO_NONE
            cv.markerDisplayMode = CrosswordView.MARKER_CHEAT

            onSelectionChanged(cv, cv.selectedWord, cv.selectedCell)
        }

        onSelectionChanged(crosswordView!!, crosswordView!!.selectedWord, crosswordView!!.selectedCell)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.guessCrosswordIcon.setOnClickListener{
            guessCrossword()
        }

        binding.guessLetterIcon.setOnClickListener{
            guessLetter()
        }

        binding.guessWordIcon.setOnClickListener{
            guessWord()
        }

        binding.restartIcon.setOnClickListener{
            restartCrossword()
        }
    }

    private fun restartCrossword() {
        crosswordView!!.reset()
    }

    private fun guessLetter() {
        crosswordView!!.solveChar(
            crosswordView!!.selectedWord!!,
            crosswordView!!.selectedCell
        )
    }

    private fun guessWord() {
        crosswordView!!.solveWord(crosswordView!!.selectedWord!!)
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable("state", crosswordView!!.state)
    }

    override fun onCellLongPressed(view: CrosswordView,
                                   word: Crossword.Word, cell: Int) {
        Toast.makeText(requireContext(), "Show popup menu for " + word.hint!!,
            Toast.LENGTH_SHORT).show()
    }

    override fun onCrosswordChanged(view: CrosswordView) {}

    override fun onCrosswordSolved(view: CrosswordView) {
        Toast.makeText(requireContext(), R.string.youve_solved_the_puzzle,
            Toast.LENGTH_SHORT).show()
    }

    override fun onCrosswordUnsolved(view: CrosswordView) {
        Toast.makeText(requireContext(), R.string.youve_solved_the_puzzle,
            Toast.LENGTH_SHORT).show()
    }

    private fun readPuzzle(@RawRes resourceId: Int): Crossword =
        resources.openRawResource(resourceId).use { s ->
            buildCrossword { PuzFormatter().read(this, s) }
        }

    override fun onSelectionChanged(view: CrosswordView,
                                    word: Crossword.Word?, position: Int) {
        hint!!.text = when (word?.direction) {
            Crossword.Word.DIR_ACROSS -> getString(R.string.across, word.number, word.hint)
            Crossword.Word.DIR_DOWN -> getString(R.string.down, word.number, word.hint)
            else -> ""
        }
    }

    fun guessCrossword(){
        crosswordView!!.solveCrossword()
    }
}