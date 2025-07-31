package com.anantjava.recordkeeper.running

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anantjava.recordkeeper.R
import com.anantjava.recordkeeper.databinding.ActivityRunningRecordBinding

class RunningRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRunningRecordBinding
    private val references: SharedPreferences by lazy {
        getSharedPreferences(
            "records",
            MODE_PRIVATE
        )
    }
    private val distance: String? by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUi()
        displayRecords()

    }

    private fun setupUi() {
        title = "$distance Record"
        binding.buttonSaveRecords.setOnClickListener {
            saveRecords()
            finish()
        }

        binding.buttonClearRecords.setOnClickListener {
            clearRecords()
            finish()
        }
    }

    private fun displayRecords() {

        binding.editTextRecord.setText(references.getString("$distance record", null))
        binding.editTextDate.setText(references.getString("$distance date", null))
    }

    private fun saveRecords() {


        references.edit {
            putString("$distance record", binding.editTextRecord.text.toString())
            putString("$distance date", binding.editTextDate.text.toString())
        }
    }

    private fun clearRecords() {
        references.edit {
            remove("$distance record")
            remove("$distance date")
        }
    }

}