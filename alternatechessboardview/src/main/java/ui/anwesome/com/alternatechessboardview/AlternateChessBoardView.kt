package ui.anwesome.com.alternatechessboardview

/**
 * Created by anweshmishra on 30/01/18.
 */
import android.view.*
import android.content.*
import android.graphics.*
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
}