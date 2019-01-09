package com.canli.oya.aboutme.collectinfo


import android.Manifest
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.databinding.ImageLayoutBinding
import com.canli.oya.aboutme.showinfo.ShowProfileActivity
import com.canli.oya.aboutme.utils.createImageFile
import com.canli.oya.aboutme.utils.deleteImageFile
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import timber.log.Timber
import java.io.File
import java.io.IOException

/**
 * The fragment for getting user profile photo
 *
 */

const val REQUEST_IMAGE_CAPTURE = 1
const val REQUEST_STORAGE_PERMISSION = 2
const val PICK_IMAGE_REQUEST = 3
const val FILE_PROVIDER_AUTHORITY = "com.canli.oya.aboutme.fileprovider"
const val KEY_USER_INFO_LIST: String = "userInfoList"

class UploadImageFragment : Fragment(), BlockingStep {

    private lateinit var binding: ImageLayoutBinding
    private lateinit var mViewModel: CollectInfoViewModel
    private var mImageUri: String? = null
    private lateinit var mTempPhotoPath: String
    private var pickImageDialog: AlertDialog? = null
    private var mUsersChoice: Int = 0
    private val mDialogClickListener =
        DialogInterface.OnClickListener { _, item -> mUsersChoice = item }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_image, container, false)

        binding.pickImage.setOnClickListener {
            openImageDialog()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(requireActivity()).get(CollectInfoViewModel::class.java)
        binding.imageUrl = mViewModel.userInfo[mViewModel.imageUrl]

    }

    fun saveAndShowAll() {
        saveImageUrl(mImageUri)

        val showAll = Intent(requireActivity(), ShowProfileActivity::class.java)
        showAll.putExtra(KEY_USER_INFO_LIST, mViewModel.userInfo)
        startActivity(showAll)
    }

    /////////////// OBLIGATORY CALLBACK OF STEPPER LAYOUT

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
        callback?.goToPrevStep()
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
        Timber.d("OnCompleteClicked called")
        saveAndShowAll()
        callback?.complete()
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
        Timber.d("OnNextClicked called")
        saveAndShowAll()
    }

    override fun onSelected() {
        //Nothing for the moment
    }

    override fun verifyStep(): VerificationError? {
        //TODO
        return null
    }

    override fun onError(error: VerificationError) {
        Timber.e(error.errorMessage)
    }

    /////// IMAGE PICKING - EITHER BY CAMERA OR FROM GALLERY //////////////////

    private fun openImageDialog() {
        val dialogOptions = activity!!.resources.getStringArray(R.array.dialog_options)
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle(R.string.add_image_from)
        builder.setSingleChoiceItems(dialogOptions, -1, mDialogClickListener)
        builder.setPositiveButton(R.string.ok) { _, _ ->
            when (mUsersChoice) {
                0 -> {
                    if (ContextCompat.checkSelfPermission(
                            activity!!,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // If you do not have permission, request it
                        this@UploadImageFragment.requestPermissions(
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_STORAGE_PERMISSION
                        )
                    } else {
                        // Launch the camera if the permission exists
                        openCamera()
                    }
                }
                1 -> openGallery()
            }
        }

        builder.setNegativeButton(R.string.cancel) { _, _ -> }
        pickImageDialog = builder.create()
        pickImageDialog?.show()
    }

    private fun openGallery() {
        val intent = Intent()
        // Show only images, no videos or anything else
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity!!.packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile(context!!)
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {

                // Get the path of the temporary file
                mTempPhotoPath = photoFile.absolutePath

                val imageUri = FileProvider.getUriForFile(
                    activity!!,
                    FILE_PROVIDER_AUTHORITY,
                    photoFile
                )
                mImageUri = imageUri.toString()
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("onActivityResult is called")
        pickImageDialog?.dismiss()
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                binding.imageUrl = mImageUri
                saveImageUrl(mImageUri)
            } else {
                deleteImageFile(activity!!, mTempPhotoPath)
            }
        } else if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                val imageUri = data?.data
                mImageUri = imageUri?.toString()
                binding.imageUrl = mImageUri
                saveImageUrl(mImageUri)
            }
        }
    }

    private fun saveImageUrl(imageUri: String?) {
        mViewModel.userInfo[mViewModel.imageUrl] = imageUri
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Timber.d("result of the permission received")
        // Called when you request permission to read and write to external storage
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // If you get permission, launch the camera
                    openCamera()
                } else {
                    // If you do not get permission, show a Toast
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
