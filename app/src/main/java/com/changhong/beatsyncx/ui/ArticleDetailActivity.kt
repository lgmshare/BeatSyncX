package com.changhong.beatsyncx.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.changhong.beatsyncx.R

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }

        val index = intent.getIntExtra("index", 1)
        when (index) {
            1 -> {
                findViewById<TextView>(R.id.article_title).setText(R.string.article_title_1)
                findViewById<TextView>(R.id.article_content).setText(R.string.article_content_1)
            }

            2 -> {
                findViewById<TextView>(R.id.article_title).setText(R.string.article_title_2)
                findViewById<TextView>(R.id.article_content).setText(R.string.article_content_2)
            }

            3 -> {
                findViewById<TextView>(R.id.article_title).setText(R.string.article_title_3)
                findViewById<TextView>(R.id.article_content).setText(R.string.article_content_3)
            }

            4 -> {
                findViewById<TextView>(R.id.article_title).setText(R.string.article_title_4)
                findViewById<TextView>(R.id.article_content).setText(R.string.article_content_4)
            }
        }
    }
}