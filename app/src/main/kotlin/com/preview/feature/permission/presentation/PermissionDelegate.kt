package com.preview.feature.permission.presentation

import android.Manifest.permission.CAMERA
import android.app.AlertDialog
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.fragment.app.Fragment
import com.preview.R
import com.preview.base.extensions.hasPermission
import timber.log.Timber

interface PermissionDelegate {
    fun Fragment.registerPermissionDelegate()
    fun Fragment.checkPermission(permission: String, callback: (isGranted: Boolean) -> Unit)
}

class PermissionDelegateImpl : PermissionDelegate {

    private lateinit var requestSinglePermissionLauncher: ActivityResultLauncher<String>
    private lateinit var callback: (isGranted: Boolean) -> Unit

    private val singlePermissionCallback = ActivityResultCallback<Boolean> { isGranted ->
        if (isGranted) {
            Timber.d("Permission is granted by user")
        } else {
            Timber.d("Permission is NOT granted by user")
        }
        callback(isGranted)
    }

    override fun Fragment.registerPermissionDelegate() {
        requestSinglePermissionLauncher = registerForActivityResult(
            RequestPermission(),
            singlePermissionCallback
        )
    }

    override fun Fragment.checkPermission(permission: String, callback: (isGranted: Boolean) -> Unit) {
        this@PermissionDelegateImpl.callback = callback
        Timber.d("checking permission = $permission")
        when {
            permission.hasPermission(requireContext()) -> {
                Timber.d("$permission Granted")
                callback(true)
            }
            shouldShowRequestPermissionRationale(permission) -> {
                Timber.d("show permission rationale dialog")
                showRationale(permission)
            }
            else -> {
                Timber.d("requesting permission")
                requestSinglePermissionLauncher.launch(permission)
            }
        }
    }

    private fun Fragment.showRationale(permission: String) {
        when (permission) {
            CAMERA -> AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.permission_dialog_camera_permission_rationale))
                .setPositiveButton(android.R.string.ok, null)
                .setOnDismissListener {
                    requestSinglePermissionLauncher.launch(permission)
                }
                .create()
                .show()
            else -> requestSinglePermissionLauncher.launch(permission)
        }
    }
}
