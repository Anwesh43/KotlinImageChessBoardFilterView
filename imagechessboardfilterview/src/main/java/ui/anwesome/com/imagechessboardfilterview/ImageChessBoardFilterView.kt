package ui.anwesome.com.imagechessboardfilterview

/**
 * Created by anweshmishra on 29/01/18.
 */
import android.graphics.*
import android.view.*
import android.content.*
import java.util.concurrent.ConcurrentLinkedQueue

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
    data class ChessBoardSquareContainer(var bitmap:Bitmap,var w:Float) {
        val squares:ConcurrentLinkedQueue<ChessBoardSquare> = ConcurrentLinkedQueue()
        init {
            for(i in 0..63) {
                squares.add(ChessBoardSquare(i))
            }
            bitmap = Bitmap.createScaledBitmap(bitmap,w.toInt(),w.toInt())
        }
        fun draw(canvas:Canvas,paint:Paint) {
            paint.color = Color.BLACK
            canvas.drawBitmap(bitmap,0f,0f,paint)
            squares.forEach {
                it.draw(canvas,paint)
            }
        }
        fun update(stopcb:(Float)->Unit) {

        }
        fun startUpdating(startcb:()->Unit) {

        }
    }
}