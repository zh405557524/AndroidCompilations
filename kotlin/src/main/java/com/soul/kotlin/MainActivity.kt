package com.soul.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.soul.lib.test.TestActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_kotlinBasics).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_kotlinAdvance).setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_kotlinBasics -> {
                startTextFragment("com.soul.kotlin.basics")
            }
            R.id.tv_kotlinAdvance -> {
                startTextFragment("com.soul.kotlin.advance")
            }
        }
    }

    fun startTextFragment(packageName: String) {
        val intent = Intent(this@MainActivity, TestActivity::class.java)
        intent.putExtra("pageName", packageName)
        startActivity(intent)
    }

}
