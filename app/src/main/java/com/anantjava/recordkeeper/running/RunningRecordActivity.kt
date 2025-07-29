package com.anantjava.recordkeeper.running

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anantjava.recordkeeper.R
import com.anantjava.recordkeeper.databinding.ActivityRunningRecordBinding

class RunningRecordActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRunningRecordBinding

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

        val distance = intent.getStringExtra("Distance")
        title = "$distance Record"

        binding.buttonSaveRecords.setOnClickListener { saveRecords(distance) }
    }

    private fun saveRecords(distance: String?) {

        val references = getSharedPreferences("records", MODE_PRIVATE)

        references.edit {
            putString("$distance record", binding.editTextRecord.text.toString())
            putString("$distance date", binding.editTextDate.text.toString())
        }
    }

}