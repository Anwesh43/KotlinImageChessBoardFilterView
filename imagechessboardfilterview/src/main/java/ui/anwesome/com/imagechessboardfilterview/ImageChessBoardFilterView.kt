package ui.anwesome.com.imagechessboardfilterview

/**
 * Created by anweshmishra on 29/01/18.
 */
import android.app.Activity
import android.graphics.*
import android.view.*
import android.content.*
import java.util.concurrent.ConcurrentLinkedQueue

class ImageChessBoardFilterView(ctx:Context,var bitmap:Bitmap):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val renderer = Renderer(this)
    var onChessBoardFilterListener:OnChessBoardFilterListener?=null
    override fun onDraw(canvas:Canvas) {
        renderer.render(canvas,paint)
    }
    fun addOnChessBoardListener(onOpenListener:()->Unit,onCloseListener:()->Unit) {
        onChessBoardFilterListener = OnChessBoardFilterListener(onOpenListener,onCloseListener)
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                renderer.handleTap()
            }
        }
        return true
    }
    data class ChessBoardSquare(var i:Int) {
        fun draw(canvas:Canvas,paint:Paint,w:Float,scale:Float) {
            var color = Color.parseColor("#AAFFFFFF")
            if((i%8+i/8)%2 == 0) {
                color = Color.parseColor("#AA000000")
            }
            paint.color = color
            val size = w/8
            val x = (i%8)*size
            val y = ((i/8))*size
            canvas.save()
            val updating_size = (size/2)*scale
            canvas.translate(x,y)
            canvas.drawRect(RectF(size/2-updating_size,size/2-updating_size,size/2+updating_size,size/2+updating_size),paint)
            canvas.restore()
        }
    }
    data class ChessBoardSquareContainer(var bitmap:Bitmap,var w:Float) {
        val squares:ConcurrentLinkedQueue<ChessBoardSquare> = ConcurrentLinkedQueue()
        val state = ChessBoardSquareState()
        var pieArc = PieArc(w/2,w+w/4,w/12)
        init {
            for(i in 0..63) {
                squares.add(ChessBoardSquare(i))
            }
            bitmap = Bitmap.createScaledBitmap(bitmap,w.toInt(),w.toInt(),true)
        }
        fun draw(canvas:Canvas,paint:Paint) {
            paint.color = Color.BLACK
            canvas.drawBitmap(bitmap,0f,0f,paint)
            squares.forEach {
                it.draw(canvas,paint,w,state.scale)
            }
            pieArc.draw(canvas,paint,state.scale)
        }
        fun update(stopcb:(Float)->Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating(startcb)
        }
    }
    data class PieArc(var x:Float,var y:Float,var r:Float) {
        fun draw(canvas:Canvas,paint:Paint,scale:Float) {
            paint.color = Color.parseColor("#4527A0")
            canvas.drawArc(RectF(x-r,y-r,x+r,y+r),0f,360f*scale,true,paint)
        }
    }
    data class ChessBoardSquareState(var scale:Float = 0f,var dir:Float = 0f,var prevScale:Float = 0f) {
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
    data class Animator(var view:View,var animated:Boolean = false) {
        fun animate(updatecb:()->Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
    data class Renderer(var view:ImageChessBoardFilterView,var time:Int = 0) {
        val animator = Animator(view)
        var container:ChessBoardSquareContainer ?= null
        fun render(canvas:Canvas,paint:Paint) {
            if(time == 0) {
                val w= canvas.width.toFloat()
                container = ChessBoardSquareContainer(view.bitmap,w)
            }
            canvas.drawColor(Color.parseColor("#212121"))
            container?.draw(canvas,paint)
            time++
            animator.animate {
                container?.update { it ->
                    animator.stop()
                    when(it) {
                        0f -> view.onChessBoardFilterListener?.onCloseListener?.invoke()
                        1f -> view.onChessBoardFilterListener?.onOpenListener?.invoke()
                    }
                }
            }
        }
        fun handleTap() {
            container?.startUpdating {
                animator.start()
            }
        }
    }
    data class OnChessBoardFilterListener(var onOpenListener:()->Unit,var onCloseListener:()->Unit)
    companion object {
        fun create(activity:Activity,bitmap:Bitmap):ImageChessBoardFilterView {
            val view = ImageChessBoardFilterView(activity,bitmap)
            activity.setContentView(view)
            return view
        }
    }
}