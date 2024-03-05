package com.obd.commands.control

import com.github.pires.obd.commands.ObdCommand
import com.github.pires.obd.commands.control.DtcNumberCommand
import com.github.pires.obd.commands.control.PendingTroubleCodesCommand
import com.github.pires.obd.commands.control.VinCommand

class ControlCommand {
    companion object {
        const val CONTROL_DTC = "CONTROL_DTC"
        const val CONTROL_PENDING_TROUBLE_CODES = "CONTROL_PENDING_TROUBLE_CODES"
        const val CONTROL_VIN = "CONTROL_VIN"
    }

    fun getCommand(type: String?): ObdCommand? {
        var obdCommand: ObdCommand? = null

        when (type) {
            CONTROL_DTC -> obdCommand = DtcNumberCommand()
            CONTROL_PENDING_TROUBLE_CODES -> obdCommand = PendingTroubleCodesCommand()
            CONTROL_VIN -> obdCommand = VinCommand()
        }

        return obdCommand
    }
}