package com.anantjava.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anantjava.recordkeeper.databinding.RunningFragmentBinding
import com.anantjava.recordkeeper.editrecord.EditRecordActivity
import com.anantjava.recordkeeper.editrecord.INTENT_SCREEN_DATA

class RunningFragment : Fragment() {

    private lateinit var binding: RunningFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RunningFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()


    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val references = requireContext().getSharedPreferences(RUNNING_FILENAME, Context.MODE_PRIVATE)
        binding.textView5kmRecord.text = references.getString("5km ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textView5kmDate.text = references.getString("5km ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        binding.textView10kmRecord.text = references.getString("10km ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textView10kmDate.text = references.getString("10km ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        binding.textViewHalfmarathonRecord.text = references.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textViewHalfmarathonDate.text = references.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        binding.textViewMarathonRecord.text = references.getString("Marathon ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textViewMarathonDate.text = references.getString("Marathon ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
    }

    private fun setupOnClickListeners() {
        binding.container5km.setOnClickListener { setupClickRunningRecord("5km") }
        binding.container10km.setOnClickListener { setupClickRunningRecord("10km") }
        binding.containerHalfmarathon.setOnClickListener { setupClickRunningRecord("Half Marathon") }
        binding.containerMarathon.setOnClickListener { setupClickRunningRecord("Marathon") }
    }


    private fun setupClickRunningRecord(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_SCREEN_DATA, EditRecordActivity.ScreenData("Time", distance, RUNNING_FILENAME))
        startActivity(intent)
    }


    companion object{

        const val RUNNING_FILENAME = "running"
    }

}