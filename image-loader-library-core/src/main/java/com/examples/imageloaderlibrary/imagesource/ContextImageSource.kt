package com.examples.imageloaderlibrary.imagesource

import android.content.Context
import java.io.InputStream

class ContextImageSource(
    private val context: Context,
    private val name: String
) : ImageSource {

    override fun getStream(): InputStream {
        return context.openFileInput(name)
    }

    override fun toString(): String {
        return "ContextImageSource(name='$name')"
    }
}