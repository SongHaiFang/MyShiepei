package demo.song.com.myshiepei;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * data:2017/9/25 0025.
 * Created by ：  song on
 */

public class YuanJiao extends ImageView {
    private int BorderRadius = 39;
    //画笔
    private Paint paint;

    //3*3的矩阵,主要用于缩小放大
    private Matrix matrix;
    //渲染图像,使用图像为绘制图形着色
    private BitmapShader bitmapShader;

    public YuanJiao(Context context) {
        this(context,null);
    }
    public YuanJiao(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public YuanJiao(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        matrix = new Matrix();
        paint = new Paint();
//抗锯齿
//        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setColor(Color.GREEN);
        paint.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null){
            return;
        }
        Bitmap bitmap = drawableToBitmap(getDrawable());
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (!(bitmap.getWidth()==getWidth()&&bitmap.getHeight()==getHeight())){
            //
            scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(),
                             getHeight() * 1.0f / bitmap.getHeight());
        }
        matrix.setScale(scale,scale);

        bitmapShader.setLocalMatrix(matrix);

        paint.setShader(bitmapShader);
        canvas.drawRoundRect(new RectF(7,5,getWidth(),getHeight()),
                BorderRadius,BorderRadius,paint);
    }

    private Bitmap drawableToBitmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }

        //
        int width = drawable.getIntrinsicWidth() <= 0 ? getWidth() : drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight() <= 0 ? getWidth() :drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        drawable.setBounds(20,20,width,height);
        drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.DST_OUT);
        drawable.draw(canvas);

        return bitmap;
    }
}
