package com.anantjava.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anantjava.recordkeeper.databinding.RunningFragmentBinding

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
        binding.container5km.setOnClickListener{setupClickRunningRecord("5km")}
        binding.container10km.setOnClickListener{setupClickRunningRecord("10km") }
        binding.containerHalfmarathon.setOnClickListener{setupClickRunningRecord("Half Marathon") }
        binding.containerMarathon.setOnClickListener { setupClickRunningRecord("Marathon") }
    }


    private fun setupClickRunningRecord(distance: String) {
        val intent = Intent(context, RunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }


}