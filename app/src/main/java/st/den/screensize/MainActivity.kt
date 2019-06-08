package st.den.screensize

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_name.text = "${getDpiCategory()}"
        tv_width_value.text = ": ${getScreenWidth()} px"
        tv_height_value.text = ": ${getScreenHeight()} px"
        tv_density_value.text = ": ${getDensity()}"
        tv_dpi.text = ": ${getDpi()} dpi"


    }


}
