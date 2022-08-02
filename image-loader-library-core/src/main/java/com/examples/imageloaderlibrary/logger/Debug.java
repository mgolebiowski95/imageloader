package com.examples.imageloaderlibrary.logger;

/**
 * Created by Mariusz Gołębiowski
 */
class Debug extends LogBase {
    private static final int DEBUG = android.util.Log.DEBUG;

    protected Debug() {
        super(DEBUG);
    }
}
