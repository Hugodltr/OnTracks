package com.epf.ontracks.overview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.epf.ontracks.R
import com.epf.ontracks.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    private lateinit var binding : OverviewFragmentBinding

    companion object {
        fun newInstance() = OverviewFragment()
    }

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.overview_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        binding.overviewViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
