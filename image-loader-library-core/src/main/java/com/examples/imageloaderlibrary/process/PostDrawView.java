package com.examples.imageloaderlibrary.process;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mariusz
 */
public class PostDrawView implements BitmapTransformation {
    private final List<View> viewList = new ArrayList<>();

    public PostDrawView() {
    }

    public PostDrawView(View... views) {
        setViewList(views);
    }

    @Override
    public Bitmap transform(Bitmap toTransform) {
        Bitmap bitmap = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(toTransform, 0, 0, null);
        for (View view : viewList) {
            if (view.getVisibility() == View.VISIBLE) {
                float scaleX = toTransform.getWidth() / (float) view.getWidth();
                float scaleY = toTransform.getHeight() / (float) view.getHeight();
                canvas.saveLayerAlpha(null, (int) (clamp(view.getAlpha(), 0, 1) * 255), Canvas.ALL_SAVE_FLAG);
                canvas.scale(scaleX, scaleY);
                view.draw(canvas);
                canvas.restore();
            }
        }
        return bitmap;
    }

    public void setViewList(View... views) {
        viewList.clear();
        for (View view : views)
            viewList.add(view);
    }

    float clamp(float value, float min, float max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}
