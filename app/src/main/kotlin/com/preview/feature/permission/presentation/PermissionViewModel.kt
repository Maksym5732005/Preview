package com.preview.feature.permission.presentation

import android.Manifest
import androidx.lifecycle.MutableLiveData
import com.preview.base.extensions.delegate
import com.preview.base.extensions.mapDistinct
import com.preview.base.presentation.BaseViewModel
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

private val permissions = listOf(Manifest.permission.CAMERA)

class PermissionViewModel @Inject constructor() : BaseViewModel() {

    private val requiredPermissions: Queue<String> = LinkedList(permissions)
    private val viewState = MutableLiveData(initialState())
    private var state by viewState.delegate()

    val areFeaturesBlocked = viewState.mapDistinct { it.areFeaturesBlocked }

    init {
        checkPermissions()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        requiredPermissions.remove(permission)
        checkPermissions()
        state = state.copy(areFeaturesBlocked = !isGranted)
    }

    private fun checkPermissions() {
        val permission = requiredPermissions.peek() ?: return
        event.value = PermissionEvent(permission)
    }

    private fun initialState() = PermissionUiState()
}