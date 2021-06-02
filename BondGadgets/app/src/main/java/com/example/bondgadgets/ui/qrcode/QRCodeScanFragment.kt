package com.example.bondgadgets.ui.qrcode

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.budiyev.android.codescanner.*
import com.example.bondgadgets.GadgetQRCode
import com.example.bondgadgets.R
import com.example.bondgadgets.common.shortToast
import com.example.bondgadgets.databinding.FragmentQrcodeScanBinding
import com.example.bondgadgets.ui.GadgetListViewModel
import timber.log.Timber


class QRCodeScanFragment : Fragment(R.layout.fragment_qrcode_scan) {

    private val binding by viewBinding(FragmentQrcodeScanBinding::bind)

    private val viewModel : GadgetListViewModel by activityViewModels()

    private lateinit var codeScanner: CodeScanner

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                startCamera()
            } else {
                context?.shortToast("Permission denied")
                findNavController().popBackStack()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission.launch(Manifest.permission.CAMERA)

        codeScanner = CodeScanner(requireContext(), binding.scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            Timber.d("Scan result: ${it.text}")
            activity?.runOnUiThread {
                viewModel.addGadget(GadgetQRCode(it.text))
                context?.shortToast("Scan result: ${it.text}")
                findNavController().popBackStack()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            Timber.e("Camera initialization error: ${it.message}")
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun startCamera(){
        codeScanner.startPreview()
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

}