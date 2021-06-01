package com.example.bondgadgets.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.bondgadgets.Gadget
import com.example.bondgadgets.Repository

class GadgetListViewModel @ViewModelInject constructor(
    private val repository : Repository) : ViewModel() {

    private val viewState = MediatorLiveData<GadgetListState>()
    fun getViewState() : LiveData<GadgetListState> = viewState

    init {
        viewState.value = GadgetListState(false, emptyList())

        viewState.addSource(repository.getAllGadgets()){ gadgets ->
            val oldState = viewState.value
            viewState.value = oldState?.copy(
                hasGadgetsChanged = true,
                gadgets = gadgets
            )
        }
    }

    fun addGadget(gadget : Gadget){
        repository.addGadget(gadget)
    }


}

data class GadgetListState(
    val hasGadgetsChanged : Boolean,
    val gadgets : List<Gadget>
)