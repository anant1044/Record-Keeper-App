package com.anantjava.recordkeeper.cycling

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anantjava.recordkeeper.databinding.CyclingFragmentBinding

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

        binding.containerLongestride.setOnClickListener { setupClicklistener("Longest Ride") }
        binding.containerBiggestclimb.setOnClickListener { setupClicklistener("Biggest Climb") }
        binding.containerBestavgspd.setOnClickListener { setupClicklistener("Best Average Speed") }

    }

    private fun setupClicklistener(distance: String)
    {
        val intent = Intent(context, CyclingRecordActivity::class.java)
        intent.putExtra("Distance", distance )
        startActivity(intent)




    }


}