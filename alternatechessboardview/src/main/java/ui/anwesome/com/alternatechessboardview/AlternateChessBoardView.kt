package ui.anwesome.com.alternatechessboardview

/**
 * Created by anweshmishra on 30/01/18.
 */
import android.view.*
import android.content.*
import android.graphics.*
import java.util.concurrent.ConcurrentLinkedQueue

val bw_colors:Array<String> = arrayOf("000000","ffffff")
class AlternateChessBoardView(ctx:Context,var bitmap:Bitmap):View(ctx) {
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class AlternateChessSquare(var i:Int) {
        fun draw(canvas:Canvas,paint:Paint,w:Float,scale:Float) {
            val x = (i%8)*w
            val updated_scale = scale+(1-2*scale)*(i%2)
            val y = (i/8)*w
            paint.color = Color.parseColor("#AA${bw_colors[(i%2+(i/8)%2)]}")
            canvas.save()
            canvas.translate(x+w/2,y+w/2)
            canvas.scale(updated_scale,updated_scale)
            canvas.drawRect(RectF(-w/2,-w/2,w/2,w/2),paint)
            canvas.restore()
        }
    }
    data class AlternateChessSquareImage(var bitmap:Bitmap,var w:Float) {
        val squares:ConcurrentLinkedQueue<AlternateChessSquare> = ConcurrentLinkedQueue()
        init {
            bitmap = Bitmap.createScaledBitmap(bitmap,w.toInt(),w.toInt(),true)
            for(i in 0..63) {
                squares.add(AlternateChessSquare(i))
            }
        }
        fun draw(canvas:Canvas,paint:Paint) {
            paint.color = Color.BLACK
            canvas.drawBitmap(bitmap,0f,0f,paint)
            squares.forEach {
                it.draw(canvas,paint,w/8,1f)
            }
        }
        fun update(stopcb:(Float)->Unit) {

        }
        fun startUpdating(startcb:()->Unit) {

        }
    }
    data class AlternateChessSquareState(var scale:Float = 0f,var dir:Float = 0f,var prevScale:Float = 0f) {
        fun update(stopcb:(Float)->Unit) {
            scale += 0.1f*dir
            if(Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                stopcb(scale)
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if(dir == 0f) {
                dir = 1-2*scale
                startcb()
            }
        }
    }
}