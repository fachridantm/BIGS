package id.belitong.bigs.ui.add

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.R
import id.belitong.bigs.core.utils.createTempFile
import id.belitong.bigs.core.utils.rotateBitmap
import id.belitong.bigs.core.utils.showMessage
import id.belitong.bigs.core.utils.uriToFile
import id.belitong.bigs.databinding.FragmentAddBinding
import java.io.File

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>() {

    private var getFile: File? = null
    private lateinit var currentPhotoPath: String

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddBinding = FragmentAddBinding.inflate(inflater, container, false)

    override fun initData() {
        initPermission()
    }

    private fun initPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // PERMISSION GRANTED
            allPermissionsGranted()
        } else {
            // PERMISSION NOT GRANTED
            getString(R.string.not_given_access).showMessage(requireContext())
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun initView() {

    }

    override fun initAction() {
        with(binding) {
            this?.btnCamera?.setOnClickListener { cameraHandler() }
            this?.btnGallery?.setOnClickListener { galleryHandler() }
        }
    }

    private fun cameraHandler() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            it.resolveActivity(requireActivity().packageManager)
        }

        createTempFile(requireContext()).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                "id.belitong.bigs",
                it
            )
            currentPhotoPath = it.absolutePath

            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile

            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                true
            )

            binding?.ivPlant?.setImageBitmap(result)
        }
    }

    private fun galleryHandler() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"

        val chooser = Intent.createChooser(intent, "Pilih gambar")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireContext())

            getFile = myFile
            binding?.ivPlant?.setImageURI(selectedImg)
        }
    }

    override fun initObservers() {

    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}