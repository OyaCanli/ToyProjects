package com.example.bondgadgets.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bondgadgets.Gadget
import com.example.bondgadgets.GadgetQRCode
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(){

    private val gadgets = LinkedList<Gadget>()

    private var gadgetsLiveData = MutableLiveData<List<Gadget>>()

    fun getAllGadgets() : LiveData<List<Gadget>> = gadgetsLiveData

    fun addGadget(gadget : Gadget){
        //Set an id and date to the Gadget
        gadget.dateCreated = Date()
        gadget.id = if(gadgets.isEmpty()) 0 else gadgets.first.id.plus(1)
        //Add to the top of the list
        gadgets.addFirst(gadget)
        //Update livedate
        gadgetsLiveData.value = gadgets
    }

    fun getGadgetQRCodeById(gadgetId : Int) : GadgetQRCode? = findGadgetById(gadgetId)

    fun getGadgetNfcById(gadgetId : Int) : GadgetQRCode? = findGadgetById(gadgetId)

    private fun <T : Gadget> findGadgetById(gadgetId : Int) : T? {
        return gadgets.find { gadget ->
            gadget.id == gadgetId
        } as? T?
    }
}