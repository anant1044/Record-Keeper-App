package com.anantjava.recordkeeper.editrecord

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anantjava.recordkeeper.R
import com.anantjava.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

const val INTENT_SCREEN_DATA = "screendata"

class EditRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRecordBinding
    private val references: SharedPreferences by lazy {
        getSharedPreferences(
            screenData.sharedpreference,
            MODE_PRIVATE
        )
    }

    private val screenData: ScreenData by lazy {
        intent.getSerializableExtra(INTENT_SCREEN_DATA) as ScreenData
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUi()
        displayRecords()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUi() {
        title = "${screenData.record} Record"
        binding.textInputLayoutRecord.hint = screenData.recordHint
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

        binding.editTextRecord.setText(
            references.getString(
                "${screenData.record} $SHARED_PREFERENCES_RECORD_KEY",
                null
            )
        )
        binding.editTextDate.setText(
            references.getString(
                "${screenData.record} $SHARED_PREFERENCES_DATE_KEY",
                null
            )
        )
    }

    private fun saveRecords() {


        references.edit {
            putString(
                "${screenData.record} $SHARED_PREFERENCES_RECORD_KEY",
                binding.editTextRecord.text.toString()
            )
            putString(
                "${screenData.record} $SHARED_PREFERENCES_DATE_KEY",
                binding.editTextDate.text.toString()
            )
        }
    }

    private fun clearRecords() {
        references.edit {
            remove("${screenData.record} $SHARED_PREFERENCES_RECORD_KEY")
            remove("${screenData.record} $SHARED_PREFERENCES_DATE_KEY")
        }
    }

    data class ScreenData(
        val recordHint: String,
        val record: String,
        val sharedpreference: String
    ) : Serializable


    companion object {

        const val SHARED_PREFERENCES_RECORD_KEY = "record"
        const val SHARED_PREFERENCES_DATE_KEY = "date"
    }

}