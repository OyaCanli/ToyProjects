package com.example.advancedgraphicsandviewscodelab

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        dialView.selectionCount = when (item.itemId) {
            R.id.action_level_3 -> 3
            R.id.action_level_4 -> 4
            R.id.action_level_5 -> 5
            R.id.action_level_6 -> 6
            else -> throw IllegalArgumentException("No such id or you forgot to update your onOptionsItemSelected method")
        }
        return super.onOptionsItemSelected(item)
    }
}