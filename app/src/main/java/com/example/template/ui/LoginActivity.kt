package com.example.template.ui


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.template.R
import com.example.template.data.AppDataManager
import com.example.template.data.DataManager
import com.example.template.data.db.AppDatabase
import com.example.template.di.qualifiers.AppContext
import com.example.template.di.module.ActivityModule
import com.example.template.TemplateApp
import com.example.template.data.db.model.Users
import com.example.template.di.components.ActivityComponent
import com.example.template.di.components.DaggerActivityComponent
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginNavigator {



    @Inject
    lateinit var dataManager:DataManager

    lateinit var activityComponent:ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.template.R.layout.activity_main)


        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as TemplateApp).getAppComponent())
            .build()
        activityComponent.inject(this)

        dataManager.getDb().getUsersDao().insertUser(Users(0,"Marko","Martic"))

        dataManager.getDb().getUsersDao().getAllUsers().forEach {

            Log.d("TAG",it.name+it.id)
        }



    }
}
