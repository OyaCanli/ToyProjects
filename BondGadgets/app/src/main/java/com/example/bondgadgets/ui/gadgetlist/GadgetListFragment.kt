package com.example.bondgadgets.ui.gadgetlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bondgadgets.Gadget
import com.example.bondgadgets.R
import com.example.bondgadgets.databinding.FragmentGadgetListBinding
import com.example.bondgadgets.ui.GadgetListState
import com.example.bondgadgets.ui.GadgetListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GadgetListFragment : Fragment(R.layout.fragment_gadget_list),
    GadgetListAdapter.GadgetClickListener {

    private val binding by viewBinding(FragmentGadgetListBinding::bind)

    private val viewModel : GadgetListViewModel by activityViewModels()

    private lateinit var adapter : GadgetListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GadgetListAdapter(this)

        binding.gadgetlist.adapter = adapter

        viewModel.getViewState().observe(viewLifecycleOwner, {
            updateUI(it)
        })

        binding.floatingActionButton.setOnClickListener {
            navigateToQRCodeScan()
        }
    }

    private fun navigateToQRCodeScan() {
        val action = GadgetListFragmentDirections.actionGadgetListFragmentToQRCodeScanFragment()
         findNavController().navigate(action)
    }

    private fun updateUI(state: GadgetListState) {
        if(state.hasGadgetsChanged){
            adapter.submitList(state.gadgets)
        }
    }

    override fun onGadgetClicked(currentGadget: Gadget) {
        TODO("Not yet implemented")
    }
}