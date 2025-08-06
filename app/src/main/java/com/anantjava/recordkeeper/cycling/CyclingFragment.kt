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
import com.anantjava.recordkeeper.editrecord.INTENT_SCREEN_DATA

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

    }

    override fun onResume() {
        super.onResume()
        displayrecords()
    }

    private fun displayrecords() {
        val references = requireContext().getSharedPreferences(CYCLING_FILENAME, Context.MODE_PRIVATE)

        binding.textViewLongrideRecord.text = references.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textviewLongrideDate.text = references.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        binding.textViewBiggestclimbRecord.text = references.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textviewBiggestclimbDate.text = references.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        binding.textViewBstAvgSpeedRecord.text =
            references.getString("Best Average Speed ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textviewBestavgspeedDate.text =
            references.getString("Best Average Speed ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)

    }

    private fun setupOnClickListeners() {
        binding.containerLongestride.setOnClickListener {
            setupClicklistener(
                "Longest Ride",
                "Distance"
            )
        }
        binding.containerBiggestclimb.setOnClickListener {
            setupClicklistener(
                "Biggest Climb",
                "Height"
            )
        }
        binding.containerBestavgspd.setOnClickListener {
            setupClicklistener(
                "Best Average Speed",
                "Average Speed"
            )
        }
    }

    private fun setupClicklistener(distance: String, hint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_SCREEN_DATA, EditRecordActivity.ScreenData(hint, distance, CYCLING_FILENAME))
        startActivity(intent)


    }

    companion object{

        const val CYCLING_FILENAME = "cycling"
    }

}