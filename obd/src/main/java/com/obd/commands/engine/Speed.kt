package com.obd.commands.engine

import com.github.pires.obd.commands.SpeedCommand
import com.obd.commands.Command

class Speed: SpeedCommand() {
    private var value: Int

    init {
        value = 0
    }

    @Override
    override fun performCalculations() {
        if (Command.isEightBit()) {
            value = buffer[2] shl 8 or buffer[3]
        } else {
            value = buffer[2]
        }
    }

    @Override
    override fun getMetricSpeed(): Int {
        return value
    }

    @Override
    override fun getImperialUnit(): Float {
        return value * 0.621371192f
    }
}