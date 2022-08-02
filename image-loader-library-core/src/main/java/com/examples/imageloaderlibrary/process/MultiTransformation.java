package com.examples.imageloaderlibrary.process;

import android.graphics.Bitmap;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Mariusz
 */
public class MultiTransformation implements BitmapTransformation {
    private Collection<? extends BitmapTransformation> transformations;

    public MultiTransformation(BitmapTransformation... transformations) {
        if (transformations.length < 1) {
            throw new IllegalArgumentException(
                    "MultiTransformation must contain at least one Transformation");
        }
        this.transformations = Arrays.asList(transformations);
    }

    public MultiTransformation(Collection<? extends BitmapTransformation> transformationList) {
        if (transformationList.isEmpty()) {
            throw new IllegalArgumentException(
                    "MultiTransformation must contain at least one Transformation");
        }
        this.transformations = transformationList;
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        for (BitmapTransformation transformation : transformations)
            toTransform = transformation.transform(toTransform);
        return toTransform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiTransformation that = (MultiTransformation) o;

        return transformations != null ? transformations.equals(that.transformations) : that.transformations == null;
    }

    @Override
    public int hashCode() {
        return transformations != null ? transformations.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MultiTransformation{" +
                "transformations=" + transformations +
                '}';
    }
}
