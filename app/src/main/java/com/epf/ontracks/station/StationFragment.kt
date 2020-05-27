package com.epf.ontracks.station

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.R
import com.epf.ontracks.databinding.StationFragmentBinding

class StationFragment : Fragment() {

    private lateinit var binding: StationFragmentBinding
    private lateinit var viewModel: StationViewModel
    private lateinit var viewModelFactory: StationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.station_fragment,container,false)

        val args = StationFragmentArgs.fromBundle(requireArguments())
        viewModelFactory = StationViewModelFactory(args.code, args.station, args.type)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StationViewModel::class.java)

        binding.lifecycleOwner = this
        binding.stationViewModel = viewModel

        val adapter = ScheduleAdapter()
        binding.schedulesList.adapter = adapter

        viewModel.schedules.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
