package com.example.ex34;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class RabbitView extends View {//自定义兔子控件
    public float bitmapX;
    public float bitmapY;

    public RabbitView(Context context) {
        super(context);
        bitmapX=290;
        bitmapY=130;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.rabbit);
        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);
//        if (bitmap.isRecycled()) {
//            bitmap.recycle();在Android 3.0之后，图片数据放在了Bitmap对象的一个成员变量 mBuffer[] 中。因此可以不调用recycle()
//        }
    }
}
