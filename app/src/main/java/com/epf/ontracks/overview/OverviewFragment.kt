package com.epf.ontracks.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.epf.ontracks.R
import com.epf.ontracks.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    private lateinit var binding : OverviewFragmentBinding
    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.overview_fragment,container,false)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.lifecycleOwner = this
        binding.overviewViewModel = viewModel

        viewModel.navigateToLines.observe(viewLifecycleOwner, Observer { navigating ->
            if(navigating) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToLinesListFragment())
                viewModel.navigateToLines(false)
            }
        })

        return binding.root
    }
}
