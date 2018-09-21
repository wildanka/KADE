import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.dan.belajarkotlin.R
import org.jetbrains.anko.*

class ClubItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View  = with(ui){
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            imageView(R.drawable.img_barca){
                id = Ids.iv_club_logo
            }.lparams(width = dip(50), height = dip(50))
            textView {
                id = Ids.tv_club_name
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
                margin = dip(10)
            }
        }
    }

    private object Ids {
        val iv_club_logo = 1
        val tv_club_name = 2
    }
}