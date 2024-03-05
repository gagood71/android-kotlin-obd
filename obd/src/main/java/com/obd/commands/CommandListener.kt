package com.obd.commands

interface CommandListener {
    fun onSuccess(value: String?, unit: String?)

    fun onFailed(errorMessage: String?, unit: String?)
}