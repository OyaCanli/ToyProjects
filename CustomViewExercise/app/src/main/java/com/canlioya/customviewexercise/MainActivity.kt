package com.canlioya.customviewexercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.canlioya.customviewexercise.bookshelflayout.BookShelfActivity
import com.canlioya.customviewexercise.carousellayoutmanager.CustomLayoutManagerActivity
import com.canlioya.customviewexercise.diagonallayout.DiagonalLayoutActivity
import com.canlioya.customviewexercise.donut.FancyViewsActivity
import com.canlioya.customviewexercise.dynamicpropertychange.DynamicPropertyChangeActivity
import com.canlioya.customviewexercise.linededittext.NotePadActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diagonal_layout_btn.setOnClickListener { launchDiagonalLayoutActivity() }
        dynamic_property_change_btn.setOnClickListener { launchDynamicPropertyChangeExercise() }
        bookshelf_layout_btn.setOnClickListener { launchBookshelfLayoutActivity() }
        lined_notepad_btn.setOnClickListener { launchLinedNotePadActivity() }
        donut_btn.setOnClickListener { launchDonutView() }
        carousel_layout_manager.setOnClickListener { launchCustomLayoutManagerActivity()}
    }

    private fun launchCustomLayoutManagerActivity() {
        val intent = Intent(this, CustomLayoutManagerActivity::class.java)
        startActivity(intent)
    }

    private fun launchDonutView() {
        val intent = Intent(this, FancyViewsActivity::class.java)
        startActivity(intent)
    }

    private fun launchLinedNotePadActivity() {
        val intent = Intent(this, NotePadActivity::class.java)
        startActivity(intent)
    }

    private fun launchBookshelfLayoutActivity() {
        val intent = Intent(this, BookShelfActivity::class.java)
        startActivity(intent)
    }

    private fun launchDynamicPropertyChangeExercise() {
        val intent = Intent(this, DynamicPropertyChangeActivity::class.java)
        startActivity(intent)
    }

    private fun launchDiagonalLayoutActivity() {
        val intent = Intent(this, DiagonalLayoutActivity::class.java)
        startActivity(intent)
    }

}
