package com.obd.commands

import android.bluetooth.BluetoothSocket
import android.os.Handler
import android.os.Looper
import com.github.pires.obd.commands.ObdCommand
import com.github.pires.obd.commands.engine.OilTempCommand
import com.github.pires.obd.commands.temperature.AirIntakeTemperatureCommand
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureCommand
import com.github.pires.obd.commands.temperature.EngineCoolantTemperatureCommand
import com.obd.commands.engine.EngineCommand
import com.obd.commands.control.ControlCommand

class Command {
    companion object {
        const val AIR_INTAKE_TEMPERATURE = "AIR_INTAKE_TEMPERATURE"
        const val AMBIENT_AIR_TEMPERATURE = "AMBIENT_AIR_TEMPERATURE"
        const val ENGINE_COOLANT_TEMPERATURE = "ENGINE_COOLANT_TEMPERATURE"
        const val ENGINE_OIL_TEMPERATURE = "ENGINE_OIL_TEMPERATURE"

        const val ECU_8_BIT = 8
        const val ECU_16_BIT = 16

        var BLUETOOTH_SOCKET: BluetoothSocket? = null

        var ECU_BIT = ECU_8_BIT

        fun run(type: String, listener: CommandListener) {
            Thread {
                var obdCommand: ObdCommand? = null

                if (type == ControlCommand.CONTROL_DTC ||
                        type == ControlCommand.CONTROL_PENDING_TROUBLE_CODES ||
                        type == ControlCommand.CONTROL_VIN) {
                    obdCommand = ControlCommand().getCommand(type)
                } else if (type == EngineCommand.ENGINE_ABSOLUTE_LOAD ||
                        type == EngineCommand.ENGINE_MASS_AIR_FLOW ||
                        type == EngineCommand.ENGINE_RPM ||
                        type == EngineCommand.ENGINE_SPEED ||
                        type == EngineCommand.ENGINE_THROTTLE_POSITION) {
                    obdCommand = EngineCommand().getCommand(type)
                } else if (type == AIR_INTAKE_TEMPERATURE) {
                    obdCommand = AirIntakeTemperatureCommand()
                } else if (type == AMBIENT_AIR_TEMPERATURE) {
                    obdCommand = AmbientAirTemperatureCommand()
                } else if (type == ENGINE_COOLANT_TEMPERATURE) {
                    obdCommand = EngineCoolantTemperatureCommand()
                } else if (type == ENGINE_OIL_TEMPERATURE) {
                    obdCommand = OilTempCommand()
                }

                if (obdCommand != null) {
                    try {
                        obdCommand.run(
                                BLUETOOTH_SOCKET?.inputStream,
                                BLUETOOTH_SOCKET?.outputStream)

                        Handler(Looper.getMainLooper()).post {
                            listener.onSuccess(
                                    obdCommand.calculatedResult,
                                    obdCommand.resultUnit)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()

                        Handler(Looper.getMainLooper()).post {
                            listener.onFailed(
                                    e.message,
                                    obdCommand.resultUnit)
                        }
                    }
                } else {
                    Handler(Looper.getMainLooper()).post {
                        listener.onFailed(
                                "NULL",
                                "NULL")
                    }
                }
            }.start()
        }

        fun isEightBit(): Boolean {
            return ECU_BIT == 8
        }
    }
}
