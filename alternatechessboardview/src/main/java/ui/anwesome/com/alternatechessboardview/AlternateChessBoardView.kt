package ui.anwesome.com.alternatechessboardview

/**
 * Created by anweshmishra on 30/01/18.
 */
import android.view.*
import android.content.*
import android.graphics.*
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
}