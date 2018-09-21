package com.example.dan.footballaplication2.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dan.footballaplication2.NetworkLayer.RClient.retrofit
import com.example.dan.footballaplication2.NetworkLayer.ServiceInterface
import com.example.dan.footballaplication2.View.AnkoUI.MainActivityAnkoUI
import com.squareup.picasso.Picasso

import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
    private lateinit var mainUI : MainActivityAnkoUI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = MainActivityAnkoUI()
        mainUI.setContentView(this)



        val service: ServiceInterface = retrofit.create(
                ServiceInterface::class.java)

      /*  service.getUser("wildanka")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {user ->
                            getData(user)
                        },{error ->
                            Log.e("Error",error.message)
                        }
                )*/



    }

    /*private fun getData(user: Github?) {

        mainUI.tvUsername.text = user?.name
        mainUI.tvCompany.text = user?.company
        Picasso.get().load(user?.avatarUrl).into(mainUI.ivGithub)
//        username.text =
//        company.text = user?.company
    }*/

}
