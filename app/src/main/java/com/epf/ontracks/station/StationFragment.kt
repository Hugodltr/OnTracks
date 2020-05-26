package com.epf.ontracks.station

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        viewModel = ViewModelProviders.of(this).get(StationViewModel::class.java)

        binding.stationViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
