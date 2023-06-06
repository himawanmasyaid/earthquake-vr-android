package com.siklusdev.gempavr.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

fun copyClipboard(context: Context, data: String) {

    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", data)
    clipboardManager.setPrimaryClip(clipData)

    Toast.makeText(context, "Data tersalin di clipboard", Toast.LENGTH_SHORT).show()

}
