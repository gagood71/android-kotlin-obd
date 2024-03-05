package com.obd.beta

import android.os.Handler
import android.os.Looper
import com.obd.commands.CommandListener
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class RPMCommand : OBDCommand<Int?>() {
    @Override
    override fun getValue(): Int {
        try {
            outputStream?.write(getCommand())
        } catch (e: IOException) {
            e.printStackTrace()

            Handler(Looper.getMainLooper()).post { listener?.onFailed(e.message, getUnit()) }
        }

        val response = ByteArray(4)

        try {
            if (inputStream?.read(response) != response.size) {
                return -1
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return if (response[0].toInt() != 0x41) {
            -1
        } else response[2] + response[3]
    }

    @Override
    override fun getUnit(): String {
        return "RPM"
    }

    @Override
    override fun getCommand(): ByteArray {
        return byteArrayOf(0x01, 0x0D)
    }

    @Override
    override fun run(input: InputStream,
                     output: OutputStream,
                     commandListener: CommandListener) {
        super.run(input, output, commandListener)

        Handler(Looper.getMainLooper()).post { listener?.onSuccess(getValue().toString(), getUnit()) }
    }
}
