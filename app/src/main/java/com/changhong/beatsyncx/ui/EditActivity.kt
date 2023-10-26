package com.changhong.beatsyncx.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.changhong.beatsyncx.R
import com.changhong.beatsyncx.databases.AppDatabase
import com.changhong.beatsyncx.databinding.ActivityEditBinding
import com.changhong.beatsyncx.models.Heartbeat
import com.changhong.beatsyncx.utils.Utils
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    private lateinit var heartbeat: Heartbeat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val lastData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("heartbeat", Heartbeat::class.java)
        } else {
            intent.getParcelableExtra("heartbeat")
        }
        if (lastData != null) {
            heartbeat = lastData
            binding.toolbarTitle.text = "Amend Record"
            binding.btnDelete.isVisible = true
            binding.btnDelete.setOnClickListener {
                lifecycleScope.launch {
                    AppDatabase.getInstance().dataDao().delete(heartbeat)
                    finish()
                }
            }
        } else {
            heartbeat = Heartbeat()
            heartbeat.max = 0
            heartbeat.min = 0
            heartbeat.ave = 0
            heartbeat.datetime = System.currentTimeMillis()
            binding.btnDelete.isVisible = false
        }

        if (heartbeat.max > 0) {
            binding.etMax.setText(heartbeat.max.toString())
            binding.etMin.setText(heartbeat.min.toString())
            binding.etAve.setText(heartbeat.ave.toString())
        }

        binding.tvDatetime.text = "Datetime: ${Utils.formatDatetime(heartbeat.datetime)}"

        binding.etMax.addTextChangedListener {
            val max = binding.etMax.text.toString()
            val maxValue = try {
                max.toInt()
            } catch (e: Exception) {
                0
            }
            heartbeat.max = maxValue
            countLevel()
        }

        binding.etMin.addTextChangedListener {
            val min = binding.etMin.text.toString()
            val minValue = try {
                min.toInt()
            } catch (e: Exception) {
                0
            }
            heartbeat.min = minValue
            countLevel()
        }

        binding.etAve.addTextChangedListener {
            val ave = binding.etAve.text.toString()
            val aveValue = try {
                ave.toInt()
            } catch (e: Exception) {
                0
            }
            heartbeat.ave = aveValue
            countLevel()
        }

        binding.btnSave.setOnClickListener {
            if (heartbeat.max == 0 || heartbeat.min == 0 || heartbeat.ave == 0) {
                Toast.makeText(this@EditActivity, "Please make sure that all heart rate data is recorded thoroughly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (heartbeat.max < heartbeat.ave || heartbeat.ave < heartbeat.min) {
                Toast.makeText(
                    this@EditActivity,
                    "During intense workouts, it's normal for the heart rate to exceed its typical daily baseline, but during sleep, it should drop below the standard daily levels",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                if (heartbeat.id > 0) {
                    AppDatabase.getInstance().dataDao().update(heartbeat)
                } else {
                    AppDatabase.getInstance().dataDao().insert(heartbeat)
                }
                Toast.makeText(this@EditActivity, "The record has been successfully added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        countLevel()
    }

    private fun countLevel() {
        if (heartbeat.max == 0 || heartbeat.min == 0 || heartbeat.ave == 0) {
            binding.stageLevel.setImageResource(R.mipmap.heartbeat_level_2)
            binding.stageTitle.setText(R.string.heartbeat_level_normal)
            binding.stageIntroduce.setText(R.string.heartbeat_level_2)
            return
        }

        when (heartbeat.level()) {
            0 -> {
                binding.stageLevel.setImageResource(R.mipmap.heartbeat_level_1)
                binding.stageTitle.setText(R.string.heartbeat_level_slow)
                binding.stageIntroduce.setText(R.string.heartbeat_level_1)
            }

            1 -> {
                binding.stageLevel.setImageResource(R.mipmap.heartbeat_level_2)
                binding.stageTitle.setText(R.string.heartbeat_level_normal)
                binding.stageIntroduce.setText(R.string.heartbeat_level_2)
            }

            2 -> {
                binding.stageLevel.setImageResource(R.mipmap.heartbeat_level_3)
                binding.stageTitle.setText(R.string.heartbeat_level_fast)
                binding.stageIntroduce.setText(R.string.heartbeat_level_3)
            }
        }
    }
}