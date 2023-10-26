package com.changhong.beatsyncx.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.changhong.beatsyncx.R

class PrivacyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }

    }
}