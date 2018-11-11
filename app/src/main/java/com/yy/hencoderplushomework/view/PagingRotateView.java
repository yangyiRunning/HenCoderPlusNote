package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.yy.hencoderplushomework.util.BitmapUtil;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 翻页+波纹
 *
 * @author yangyi 2018年11月08日22:30:12
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PagingRotateView extends View {
    private static int WIDTH = (int) DensityUtil.dp2px(200);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private Camera camera = new Camera();

    public PagingRotateView(Context context) {
        super(context);
    }

    {
        bitmap = BitmapUtil.getAvatar(getContext(), WIDTH);
        camera.rotateX(45);
        camera.setLocation(0, 0, -18 * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 核心奥义：
     * 1. 先移动，再变换；
     * <p>
     * 2. 变换中，旋转完之后再裁切，裁切完了再旋转回来
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(-10);
        canvas.clipRect(-bitmap.getWidth(),
                -bitmap.getHeight(),
                bitmap.getWidth(),
                0);
        canvas.rotate(10);
        canvas.translate(-getWidth() / 2, -getHeight() / 2);
        canvas.drawBitmap(bitmap,
                getWidth() / 2 - bitmap.getWidth() / 2,
                getHeight() / 2 - bitmap.getHeight() / 2,
                paint);
        canvas.restore();

        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(-10);
        canvas.clipRect(-bitmap.getWidth(), 0, bitmap.getWidth(), bitmap.getHeight());
        camera.applyToCanvas(canvas);
        canvas.rotate(10);
        canvas.translate(-getWidth() / 2, -getHeight() / 2);
        canvas.drawBitmap(bitmap,
                getWidth() / 2 - bitmap.getWidth() / 2,
                getHeight() / 2 - bitmap.getHeight() / 2,
                paint);
        canvas.restore();
    }
}
