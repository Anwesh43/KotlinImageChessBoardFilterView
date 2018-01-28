package ui.anwesome.com.imagechessboardfilterview

/**
 * Created by anweshmishra on 29/01/18.
 */
import android.graphics.*
import android.view.*
import android.content.*
class ImageChessBoardFilterView(ctx:Context):View(ctx) {
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
}