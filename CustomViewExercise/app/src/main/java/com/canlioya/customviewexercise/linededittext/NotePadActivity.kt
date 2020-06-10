package com.canlioya.customviewexercise.linededittext

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.canlioya.customviewexercise.R
import kotlinx.android.synthetic.main.activity_note_pad.*

class NotePadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_pad)

        linedEditText.requestFocus()
        linedEditText.setOnClickListener {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } //TODO : check out why this is not automatically working
    }
}
