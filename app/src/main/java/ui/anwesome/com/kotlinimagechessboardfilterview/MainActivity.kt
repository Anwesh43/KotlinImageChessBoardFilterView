package ui.anwesome.com.kotlinimagechessboardfilterview

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.imagechessboardfilterview.ImageChessBoardFilterView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImageChessBoardFilterView.create(this,BitmapFactory.decodeResource(resources,R.drawable.nature_more))
    }
}
