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
        val references = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
        binding.textView5kmRecord.text = references.getString("5km record", null)
        binding.textView5kmDate.text = references.getString("5km date", null)
        binding.textView10kmRecord.text = references.getString("10km record", null)
        binding.textView10kmDate.text = references.getString("10km date", null)
        binding.textViewHalfmarathonRecord.text = references.getString("Half Marathon record", null)
        binding.textViewHalfmarathonDate.text = references.getString("Half Marathon date", null)
        binding.textViewMarathonRecord.text = references.getString("Marathon record", null)
        binding.textViewMarathonDate.text = references.getString("Marathon date", null)
    }

    private fun setupOnClickListeners() {
        binding.container5km.setOnClickListener { setupClickRunningRecord("5km") }
        binding.container10km.setOnClickListener { setupClickRunningRecord("10km") }
        binding.containerHalfmarathon.setOnClickListener { setupClickRunningRecord("Half Marathon") }
        binding.containerMarathon.setOnClickListener { setupClickRunningRecord("Marathon") }
    }


    private fun setupClickRunningRecord(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("screendata", EditRecordActivity.ScreenData("Time", distance, "running"))
        startActivity(intent)
    }


}