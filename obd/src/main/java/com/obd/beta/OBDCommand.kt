package com.obd.beta

import com.obd.commands.CommandListener
import java.io.InputStream
import java.io.OutputStream

abstract class OBDCommand<T> {
    protected var inputStream: InputStream? = null

    protected var outputStream: OutputStream? = null

    protected var listener: CommandListener? = null

    protected abstract fun getValue(): T

    protected abstract fun getUnit(): String?

    protected abstract fun getCommand(): ByteArray?

    open fun run(input: InputStream,
                 output: OutputStream,
                 commandListener: CommandListener) {
        inputStream = input
        outputStream = output
        listener = commandListener
    }
}