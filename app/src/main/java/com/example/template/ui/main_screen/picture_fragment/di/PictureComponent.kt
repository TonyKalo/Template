package com.example.template.ui.main_screen.picture_fragment.di

import com.example.template.ui.main_screen.picture_fragment.PictureFragment
import dagger.Subcomponent

@Subcomponent(modules = [PictureModule::class, PictureBindModule::class])
interface PictureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PictureComponent
    }

    fun inject(fragment: PictureFragment)
}
