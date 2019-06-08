package st.den.screensize

import android.graphics.Point
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.getScreenWidth() = getDisplayInformation().x
fun AppCompatActivity.getScreenHeight() = getDisplayInformation().y
fun AppCompatActivity.getDisplayInformation(): Point {
    val size = Point()
    defaultDisplay().let { it.getSize(size) }
    return size
}

fun AppCompatActivity.getDensity(): Float {
    val dm = DisplayMetrics()
    defaultDisplay().let { it.getMetrics(dm) }
    return dm.density
}

fun AppCompatActivity.getDpi(): Int {
    val dm = DisplayMetrics()
    defaultDisplay().let { it.getMetrics(dm) }
    Log.e("density", " density : ${dm.density}")
    Log.e("density", " densityDpi : ${dm.densityDpi}")
    Log.e("density", " heightPixels : ${dm.heightPixels}")
    Log.e("density", " widthPixels : ${dm.widthPixels}")
    Log.e("density", " xdpi : ${dm.xdpi}")
    Log.e("density", " ydpi : ${dm.ydpi}")
    Log.e("density", " scaledDensity : ${dm.scaledDensity}")

    return dm.densityDpi
}

fun AppCompatActivity.defaultDisplay(): Display = DisplayMetrics().let { windowManager.defaultDisplay }

fun AppCompatActivity.getDpiCategory(): String =
    when (getDpi()) {
        in 0..120 -> "LDPI"
        in 120..160 -> "MDPI"
        in 160..240 -> "HDPI"
        in 240..320 -> "XHDPI"
        in 320..480 -> "XXHDPI"
        in 480..640 -> "XXXHDPI"
        else -> "Not found category"
    }

fun AppCompatActivity.getSoftKeyboardHeight(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    var deviceHeight = 0
    if (resourceId > 0) {
        deviceHeight = resources.getDimensionPixelSize(resourceId)
    }
    return deviceHeight
}

fun AppCompatActivity.getRatio() : String {
    val dm = DisplayMetrics()
    var max = 0f
    var min = 0f
    var gcd = 0f
    var war = 0f
    var har = 0f

    defaultDisplay().let { it.getMetrics(dm) }

    var rwidth = dm.xdpi // 화면의 가로 해상도 구하기
    var rheight = dm.ydpi;      // 화면의 세로 해상도 구하기

    if (rwidth < rheight) {      // 화면의 가로, 세로 크기 비교
        max = rwidth;          // 큰쪽을 max로
        min = rheight;
    }
    else {
        max = rheight; min = rwidth;
    }

    while (max % min != 0f) {      // 나머지가 0이 될 때까지 temp = max % min;            // max/min의 나머지를 temp로
        max = min;           // min이 나눠지는 수로 min = temp;           // temp가 나누는 수로 바뀌어 다시 나머지 계산 }
        gcd = min;      // 최종적으로 나온 나누는 수가 바로 최대공약수
        war = rwidth / gcd;      // 화면의 가로/최대공약수 = 가로비율
        har = rheight / gcd;      // 화면의 세로/최대공약수 = 세로비율
    }
    return "$war : $har"
}