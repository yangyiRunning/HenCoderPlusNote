package com.yy.hencoderplushomework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.yy.hencoderplushomework.util.BitmapUtil;
import com.yy.hencoderplushomework.util.DensityUtil;

/**
 * 圆形头像
 *
 * @author yangyi 2018年11月07日23:53:11
 */
public class AvatarView extends View {

    private static final int WIDTH = (int) DensityUtil.dp2px(300);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private RectF rectF = new RectF();
    private PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private static final float BORDER_WIDTH = DensityUtil.dp2px(10);

    public AvatarView(Context context) {
        super(context);
    }

    {
        bitmap = BitmapUtil.getAvatar(getContext(), WIDTH);
        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF.set(getWidth() / 2 - WIDTH / 2,
                getHeight() / 2 - WIDTH / 2,
                getWidth() / 2 + WIDTH / 2,
                getHeight() / 2 + WIDTH / 2);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, WIDTH / 2 + BORDER_WIDTH, paint);
        //离屏缓冲
        int saveCount = canvas.saveLayer(rectF, paint);
        canvas.drawOval(rectF, paint);
        //SRC_IN方式融合
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bitmap,
                getWidth() / 2 - bitmap.getWidth() / 2,
                getHeight() / 2 - bitmap.getHeight() / 2,
                paint);
        //回收离屏缓冲和融合方式
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }
}
