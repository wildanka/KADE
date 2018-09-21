import android.app.*
import android.view.*
import android.widget.*
import org.jetbrains.anko.*
import android.os.Bundle
import com.example.dan.belajarkotlin.R

class SomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)

        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = R.dimen.vertical_margin
        
            imageView(R.drawable.img_acm){
                id = Ids.iv_club_logo
            }.lparams(width = dip(50), height = dip(50)) {
                margin = dip(4)
            }
            textView {
                id = Ids.tv_club_name
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
            }
        }
    }

    private object Ids {
        val iv_club_logo = 1
        val tv_club_name = 2
    }
}
