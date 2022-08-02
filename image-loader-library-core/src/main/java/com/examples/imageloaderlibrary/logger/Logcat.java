package com.examples.imageloaderlibrary.logger;

import java.lang.*;

/**
 * Created by Mariusz Gołębiowski
 */
public class Logcat {
    public static final LogBase VERBOSE = new Verbose();
    public static final LogBase DEBUG = new Debug();
    public static final LogBase INFO = new Info();
    public static final LogBase WARN = new Warn();
    public static final LogBase ERROR = new Error();

    public static void setDefaultTag(String defaultTag) {
        VERBOSE.setDefaultTag(defaultTag);
        DEBUG.setDefaultTag(defaultTag);
        INFO.setDefaultTag(defaultTag);
        WARN.setDefaultTag(defaultTag);
        ERROR.setDefaultTag(defaultTag);
    }
}
