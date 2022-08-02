package com.examples.imageloaderlibrary.logger;

/**
 * Created by Mariusz Gołębiowski
 */
class Verbose extends LogBase {
    private static final int VERBOSE = android.util.Log.VERBOSE;

    protected Verbose() {
        super(VERBOSE);
    }
}
