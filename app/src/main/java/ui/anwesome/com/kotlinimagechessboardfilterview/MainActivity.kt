package ui.anwesome.com.kotlinimagechessboardfilterview

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ui.anwesome.com.alternatechessboardview.AlternateChessBoardView
import ui.anwesome.com.imagechessboardfilterview.ImageChessBoardFilterView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val view = ImageChessBoardFilterView.create(this,BitmapFactory.decodeResource(resources,R.drawable.nature_more))
//        view.addOnChessBoardListener({
//           Toast.makeText(this,"chess board square expanded",Toast.LENGTH_SHORT).show()
//        },{
//            Toast.makeText(this,"chess board square collapsed",Toast.LENGTH_SHORT).show()
//        })
        val view = AlternateChessBoardView.create(this,BitmapFactory.decodeResource(resources,R.drawable.nature_more))

    }
}
