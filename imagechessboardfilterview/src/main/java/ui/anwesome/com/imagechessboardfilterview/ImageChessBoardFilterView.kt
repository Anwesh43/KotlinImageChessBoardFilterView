package ui.anwesome.com.imagechessboardfilterview

/**
 * Created by anweshmishra on 29/01/18.
 */
import android.graphics.*
import android.view.*
import android.content.*
class ImageChessBoardFilterView(ctx:Context,var bitmap:Bitmap):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class ChessBoardSquare(var i:Int) {
        fun draw(canvas:Canvas,paint:Paint,w:Float,scale:Float) {
            var color = Color.parseColor("#99FFFFFF")
            if(i%2 == 0) {
                color = Color.parseColor("#99000000")
            }
            paint.color = color
            val size = w/8
            val x = (i%w)*size
            val y = (i/w.toInt())*size
            canvas.save()
            val updating_size = (size/2)*scale
            canvas.translate(x,y)
            canvas.drawRect(RectF(size/2-updating_size,size/2-updating_size,size/2+updating_size,size/2+updating_size),paint)
            canvas.restore()
        }
    }
}