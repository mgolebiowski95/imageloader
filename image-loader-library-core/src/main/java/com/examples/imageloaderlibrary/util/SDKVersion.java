package com.examples.imageloaderlibrary.util;

import android.os.Build;
import androidx.annotation.IntRange;

/**
 * Created by Mariusz
 */
public class SDKVersion {
    public static boolean greaterOrEqualsThan(@IntRange(from = 1, to = 25) int level) {
        return Build.VERSION.SDK_INT >= level;
    }

    public static boolean lesserOrEqualsThan(@IntRange(from = 1, to = 25) int level) {
        return Build.VERSION.SDK_INT <= level;
    }

    public static boolean equals(@IntRange(from = 1, to = 25) int level) {
        return Build.VERSION.SDK_INT == level;
    }
}
