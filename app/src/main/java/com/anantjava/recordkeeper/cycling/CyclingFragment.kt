package com.anantjava.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anantjava.recordkeeper.databinding.CyclingFragmentBinding
import com.anantjava.recordkeeper.editrecord.EditRecordActivity

class CyclingFragment : Fragment() {

    private lateinit var binding: CyclingFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CyclingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnClickListeners()
        displayrecords()

    }

    private fun displayrecords() {
        val references = requireContext().getSharedPreferences("records", Context.MODE_PRIVATE)

        binding.textViewLongrideRecord.text = references.getString("Longest Ride record", null)
        binding.textviewLongrideDate.text = references.getString("Longest Ride date", null)
        binding.textViewBiggestclimbRecord.text = references.getString("Biggest Climb record", null)
        binding.textviewBiggestclimbDate.text = references.getString("Biggest Climb date", null)
        binding.textViewBstAvgSpeedRecord.text = references.getString("Best Average Speed record", null)
        binding.textviewBestavgspeedDate.text = references.getString("Best Average Speed date", null)

    }

    private fun setupOnClickListeners() {
        binding.containerLongestride.setOnClickListener { setupClicklistener("Longest Ride", "Distance") }
        binding.containerBiggestclimb.setOnClickListener { setupClicklistener("Biggest Climb", "Height") }
        binding.containerBestavgspd.setOnClickListener { setupClicklistener("Best Average Speed", "Average Speed") }
    }

    private fun setupClicklistener(distance: String, hint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("screendata", EditRecordActivity.ScreenData(hint, distance, "records"))
        startActivity(intent)


    }


}