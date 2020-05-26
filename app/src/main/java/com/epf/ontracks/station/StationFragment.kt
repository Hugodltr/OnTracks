package com.epf.ontracks.station

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.R
import com.epf.ontracks.databinding.StationFragmentBinding

class StationFragment : Fragment() {

    private lateinit var binding: StationFragmentBinding

    companion object {
        fun newInstance() = StationFragment()
    }

    private lateinit var viewModel: StationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.station_fragment,container,false)
        viewModel = ViewModelProvider(this).get(StationViewModel::class.java)

        binding.lifecycleOwner = this

        binding.stationViewModel = viewModel

        return binding.root
    }
}
