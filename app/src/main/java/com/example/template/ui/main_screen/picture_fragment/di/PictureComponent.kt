package com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di

import com.example.template.ui.main_screen.picture_fragment.PictureFragment
import com.example.template.ui.registration_login.login.LoginFragment
import dagger.Subcomponent

@Subcomponent(modules = [PictureModule::class, PictureBindModule::class])
interface PictureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PictureComponent
    }

    fun inject(fragment: PictureFragment)
}
