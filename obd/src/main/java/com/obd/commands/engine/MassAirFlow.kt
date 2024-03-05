package com.obd.commands.engine

import com.github.pires.obd.commands.engine.MassAirFlowCommand
import com.obd.commands.Command

class MassAirFlow: MassAirFlowCommand() {
    private var value: Float

    init {
        value = -1.0f
    }

    @Override
    override fun performCalculations() {
        if (Command.isEightBit()) {
            value = (buffer[2] * 256 + buffer[3]) / 100.0f
        } else {
            value = (buffer[2] + buffer[3]) / 100.0f
        }
    }

    @Override
    override fun getFormattedResult(): String {
        return String.format("%.2f%s", value, resultUnit)
    }

    @Override
    override fun getCalculatedResult(): String {
        return value.toString()
    }

    @Override
    override fun getResultUnit(): String {
        return "g/s"
    }

    @Override
    override fun getMAF(): Double {
        return value.toDouble()
    }
}