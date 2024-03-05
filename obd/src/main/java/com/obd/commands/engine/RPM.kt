package com.obd.commands.engine

import com.github.pires.obd.commands.engine.RPMCommand
import com.obd.commands.Command

class RPM: RPMCommand() {
    private var value: Int

    init {
        value = 0
    }

    @Override
    override fun performCalculations() {
        if (Command.isEightBit()) {
            value = (buffer[2] + buffer[3]) / 4
        } else {
            value = (buffer[2] * 256 + buffer[3]) / 4
        }
    }

    @Override
    override fun getFormattedResult(): String {
        return String.format("%d%s", value, resultUnit)
    }

    @Override
    override fun getCalculatedResult(): String {
        return value.toString()
    }

    @Override
    override fun getRPM(): Int {
        return value
    }
}