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
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.BuildConfig
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Plant
import id.belitong.bigs.core.utils.createTempFile
import id.belitong.bigs.core.utils.rotateBitmap
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.core.utils.showToast
import id.belitong.bigs.core.utils.uriToFile
import id.belitong.bigs.databinding.DialogDataFoundBinding
import id.belitong.bigs.databinding.DialogDataNotFoundBinding
import id.belitong.bigs.databinding.FragmentAddBinding
import id.belitong.bigs.ui.main.MainViewModel
import java.io.File

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>() {

    private var getFile: File? = null
    private var currentPhotoPath = ""

    private var scannerState = false

    private val mainViewModel: MainViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddBinding = FragmentAddBinding.inflate(inflater, container, false)

    override fun initData() {
        initPermission()
    }

    private fun initPermission() {
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // PERMISSION GRANTED
            allPermissionsGranted()
        } else {
            // PERMISSION NOT GRANTED
            getString(R.string.not_given_access).showToast(requireContext())
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun initView() {}

    override fun initAction() {
        with(binding) {
            this?.btnCamera?.setOnClickListener { cameraHandler() }
            this?.btnGallery?.setOnClickListener { galleryHandler() }
            this?.btnScan?.setOnClickListener { scanHandler() }
        }
    }

    private fun cameraHandler() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            it.resolveActivity(requireActivity().packageManager)
        }

        createTempFile(requireContext()).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID,
                it
            )
            currentPhotoPath = it.absolutePath

            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }

        scannerState = true
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
        intent.type = getString(R.string.image_format)

        val chooser = Intent.createChooser(intent, getString(R.string.pilih_gambar))
        launcherIntentGallery.launch(chooser)

        scannerState = false
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

    private fun scanHandler() {
        if (getFile != null) {
            binding?.apply {
                mainViewModel.plant.observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> showLoading(true)

                        is Resource.Success -> {
                            showLoading(false)
                            detectionHandler(it.data)
                        }

                        is Resource.Error -> {
                            showLoading(false)
                            it.message.showSnackbar(requireView(), navView)
                        }

                        else -> {}
                    }
                }
            }
        } else {
            getString(R.string.no_image_selected).showSnackbar(requireView(), navView)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                scanPlant.visibility = ViewGroup.VISIBLE
                btnCamera.isEnabled = false
                btnGallery.isEnabled = false
                btnScan.isEnabled = false
            } else {
                scanPlant.visibility = ViewGroup.GONE
                btnCamera.isEnabled = true
                btnGallery.isEnabled = true
                btnScan.isEnabled = true
            }
        }
    }

    private fun detectionHandler(plant: Plant) {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialog).create()
        val dialogSuccessBinding = DialogDataFoundBinding.inflate(layoutInflater)
        val dialogFailedBinding = DialogDataNotFoundBinding.inflate(layoutInflater)

        dialogSuccessBinding.apply {
            tvName.text = plant.name
            tvLatinName.text = plant.latin
            btnDetails.setOnClickListener {
                getString(R.string.on_click_handler).showSnackbar(requireView(), navView)
            }
        }

        dialogFailedBinding.apply {
            btnAdd.setOnClickListener {
                getString(R.string.on_click_handler).showSnackbar(requireView(), navView)
            }

            btnClose.setOnClickListener {
                builder.dismiss()
            }
        }

        if (scannerState) builder.setView(dialogSuccessBinding.root)
        else builder.setView(dialogFailedBinding.root)

        builder.show()
    }

    override fun initObservers() {

    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}