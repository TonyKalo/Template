package com.example.template.ui.base.model

import com.example.template.ui.base.callbacks.PermissionCallback

data class PermissionModel (var permissions: Array<String>,
                            var handleDialog: Boolean,
                            var permissionCallback: PermissionCallback) {
}