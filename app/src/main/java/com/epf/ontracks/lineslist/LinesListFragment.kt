package com.epf.ontracks.lineslist

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.epf.ontracks.MainActivity
import com.epf.ontracks.R
import com.epf.ontracks.databinding.LinesListFragmentBinding
import java.util.*


class LinesListFragment : Fragment() {

    private lateinit var binding : LinesListFragmentBinding
    private lateinit var viewModel: LinesListViewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.lines_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterLines(newText.toLowerCase(Locale.ROOT))
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.filterLines(query.toLowerCase(Locale.ROOT))
                searchView.clearFocus()
                return false
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Lignes"

        binding = DataBindingUtil.inflate(inflater, R.layout.lines_list_fragment,container,false)
        viewModel = ViewModelProvider(this).get(LinesListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.linesListViewModel = viewModel

        val adapter = LineAdapter(LineListener { line ->
            viewModel.navigateToStations(true, line, viewModel.showLines.value!!)
        })

        binding.linesList.adapter = adapter

        viewModel.lines.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToStations.observe(viewLifecycleOwner, Observer { navigating ->
            if(navigating && viewModel.line.value != null && viewModel.lineType.value != null) {
                this.findNavController().navigate(LinesListFragmentDirections.actionLinesListFragmentToStationsListFragment(viewModel.line.value!!, viewModel.lineType.value!!))
                viewModel.navigateToStations(false, null, null)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onStop() {
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
        super.onStop()
    }
}
