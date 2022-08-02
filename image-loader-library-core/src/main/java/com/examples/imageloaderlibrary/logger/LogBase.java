package com.examples.imageloaderlibrary.logger;

import java.util.Arrays;

/**
 * Created by Mariusz Gołębiowski
 */
public class LogBase {
    private final int logLevel;
    private String defaultTag;

    protected LogBase(int logLevel) {
        this.logLevel = logLevel;
        defaultTag = getClass().getSimpleName();
    }

    public void setDefaultTag(String defaultTag) {
        this.defaultTag = defaultTag;
    }

    public void log(Object object) {
        log(defaultTag, object);
    }

    public void log(Object[] objects) {
        log(defaultTag, objects);
    }

    public void log(Object object, Throwable tr) {
        log(defaultTag, object, tr);
    }

    public void log(Object[] objects, Throwable tr) {
        log(defaultTag, objects, tr);
    }

    public void log(Object simpleNameTag, Object object) {
        log(simpleNameTag.getClass(), object);
    }

    public void log(Object simpleNameTag, Object[] objects) {
        log(simpleNameTag.getClass(), objects);
    }

    public void log(Object simpleNameTag, Object object, Throwable tr) {
        log(simpleNameTag.getClass(), object, tr);
    }

    public void log(Object simpleNameTag, Object[] objects, Throwable tr) {
        log(simpleNameTag.getClass(), objects, tr);
    }

    public void log(Class simpleNameTag, Object object) {
        log(simpleNameTag.getSimpleName(), object);
    }

    public void log(Class simpleNameTag, Object[] objects) {
        log(simpleNameTag.getSimpleName(), objects);
    }

    public void log(Class simpleNameTag, Object object, Throwable tr) {
        log(simpleNameTag.getSimpleName(), object, tr);
    }

    public void log(Class simpleNameTag, Object[] objects, Throwable tr) {
        log(simpleNameTag.getSimpleName(), objects, tr);
    }

    public void log(String tag, Object object) {
        println(logLevel, tag, object);
    }

    public void log(String tag, Object[] objects) {
        println(logLevel, tag, objects);
    }

    public void log(String tag, Object object, Throwable tr) {
        println(logLevel, tag, object, tr);
    }

    public void log(String tag, Object[] objects, Throwable tr) {
        println(logLevel, tag, objects, tr);
    }

    private void println(int priority, String tag, Object object) {
        String tmpTag = tag == null ? "" : tag;
        String tmpMsg = String.valueOf(object);
        println(priority, tmpTag, tmpMsg);
    }

    private void println(int priority, String tag, Object[] objects) {
        String tmpTag = tag == null ? "" : tag;
        String tmpMsg = Arrays.toString(objects);
        println(priority, tmpTag, tmpMsg);
    }

    private void println(int priority, String tag, Object object, Throwable tr) {
        String tmpTag = tag == null ? "" : tag;
        String tmpMsg = String.valueOf(object);
        println(priority, tmpTag, tmpMsg, tr);
    }

    private void println(int priority, String tag, Object[] objects, Throwable tr) {
        String tmpTag = tag == null ? "" : tag;
        String tmpMsg = Arrays.toString(objects);
        println(priority, tmpTag, tmpMsg, tr);
    }

    private void println(int priority, String tmpTag, String tmpMsg, Throwable tr) {
        tmpMsg += "\n" + android.util.Log.getStackTraceString(tr);
        println(priority, tmpTag, tmpMsg);
    }

    private void println(int priority, String tmpTag, String tmpMsg) {
        android.util.Log.println(priority, tmpTag, tmpMsg);
    }
}
