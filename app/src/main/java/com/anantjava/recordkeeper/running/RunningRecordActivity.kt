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
    private lateinit var references: SharedPreferences
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
        references = getSharedPreferences("records", MODE_PRIVATE)


        title = "$distance Record"


        displayRecords()
        binding.buttonSaveRecords.setOnClickListener {
            saveRecords()
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

}