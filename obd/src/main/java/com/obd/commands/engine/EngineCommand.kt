package com.obd.commands.engine

import com.github.pires.obd.commands.ObdCommand

class EngineCommand {
    companion object {
        const val ENGINE_ABSOLUTE_LOAD = "ENGINE_ABSOLUTE_LOAD"
        const val ENGINE_MASS_AIR_FLOW = "ENGINE_MASS_AIR_FLOW"
        const val ENGINE_RPM = "ENGINE_RPM"
        const val ENGINE_SPEED = "ENGINE_SPEED"
        const val ENGINE_THROTTLE_POSITION = "ENGINE_THROTTLE_POSITION"
    }

    fun getCommand(type: String?): ObdCommand? {
        var obdCommand: ObdCommand? = null

        when (type) {
            ENGINE_ABSOLUTE_LOAD -> obdCommand = AbsoluteLoad()
            ENGINE_MASS_AIR_FLOW -> obdCommand = MassAirFlow()
            ENGINE_RPM -> obdCommand = RPM()
            ENGINE_SPEED -> obdCommand = Speed()
            ENGINE_THROTTLE_POSITION -> obdCommand = ThrottlePosition()
        }

        return obdCommand
    }
}