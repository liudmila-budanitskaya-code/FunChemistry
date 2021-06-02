package site.budanitskaya.chemistryquiz.fine.crossword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RawRes
import org.akop.ararat.core.Crossword
import org.akop.ararat.core.buildCrossword
import org.akop.ararat.io.PuzFormatter
import org.akop.ararat.view.CrosswordView
import site.budanitskaya.chemistryquiz.fine.R

class CrosswordActivity : AppCompatActivity(), CrosswordView.OnLongPressListener, CrosswordView.OnStateChangeListener, CrosswordView.OnSelectionChangeListener {

    private var crosswordView: CrosswordView? = null
    private var hint: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_crossword)

        hint = findViewById(R.id.hint)

        val crossword = readPuzzle(R.raw.puzzle)

        title = getString(R.string.title_by_author,
            crossword.title, crossword.author)

        crosswordView!!.let { cv ->
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
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_restart -> {
                crosswordView!!.reset()
                return true
            }
            R.id.menu_solve_cell -> {
                crosswordView!!.solveChar(crosswordView!!.selectedWord!!,
                    crosswordView!!.selectedCell)
                return true
            }
            R.id.menu_solve_word -> {
                crosswordView!!.solveWord(crosswordView!!.selectedWord!!)
                return true
            }
            R.id.menu_solve_puzzle -> {
                crosswordView!!.solveCrossword()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCellLongPressed(view: CrosswordView,
                                   word: Crossword.Word, cell: Int) {
        Toast.makeText(this, "Show popup menu for " + word.hint!!,
            Toast.LENGTH_SHORT).show()
    }

    override fun onCrosswordChanged(view: CrosswordView) {}

    override fun onCrosswordSolved(view: CrosswordView) {
        Toast.makeText(this, R.string.youve_solved_the_puzzle,
            Toast.LENGTH_SHORT).show()
    }

    override fun onCrosswordUnsolved(view: CrosswordView) {
        Toast.makeText(this, R.string.youve_solved_the_puzzle,
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
}