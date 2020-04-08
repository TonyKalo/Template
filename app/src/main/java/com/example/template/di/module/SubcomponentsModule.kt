package com.example.template.di.module

import com.example.template.ui.main_screen.main_activity.MainActivity
import com.example.template.ui.main_screen.permission_fragment.PermissionFragment
import com.example.template.ui.main_screen.permission_fragment.di.PermissionComponent
import com.example.template.ui.main_screen.picture_fragment.PictureFragment
import com.example.template.ui.main_screen.picture_fragment.detail_fragment.DetailFragment
import com.example.template.ui.main_screen.picture_fragment.detail_fragment.di.DetailComponent
import com.example.template.ui.main_screen.picture_fragment.di.PictureComponent
import com.example.template.ui.registration_login.login.LoginFragment
import com.example.template.ui.registration_login.login.di.LoginComponent
import com.example.template.ui.registration_login.registration_activity.di.RegistrationActivityComponent
import com.example.template.ui.registration_login.registration_activity.RegistrationActivity
import com.example.template.ui.main_screen.main_activity.di.MainActivityComponent
import com.example.template.ui.splash.SplashFragment
import com.example.template.ui.splash.di.SplashComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        RegistrationActivityComponent::class,
        MainActivityComponent::class,
        SplashComponent::class,
        LoginComponent::class,
        PictureComponent::class,
        PermissionComponent::class,
        DetailComponent::class
    ]
)
abstract class SubcomponentsModule {

    @Binds
    @IntoMap
    @ClassKey(RegistrationActivity::class)
    abstract fun bindRegistrationActivityInjectorFactory(factory: RegistrationActivityComponent.Factory?): AndroidInjector.Factory<*>?

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(factory: MainActivityComponent.Factory?): AndroidInjector.Factory<*>?


    @Binds
    @IntoMap
    @ClassKey(SplashFragment::class)
    abstract fun bindSplashInjectorFactory(factory: SplashComponent.Factory?): AndroidInjector.Factory<*>?

    @Binds
    @IntoMap
    @ClassKey(LoginFragment::class)
    abstract fun bindLoginInjectorFactory(factory: LoginComponent.Factory?): AndroidInjector.Factory<*>?

    @Binds
    @IntoMap
    @ClassKey(PermissionFragment::class)
    abstract fun bindPermissionInjectorFactory(factory: PermissionComponent.Factory?): AndroidInjector.Factory<*>?

    @Binds
    @IntoMap
    @ClassKey(PictureFragment::class)
    abstract fun bindPictureInjectorFactory(factory: PictureComponent.Factory?): AndroidInjector.Factory<*>?

    @Binds
    @IntoMap
    @ClassKey(DetailFragment::class)
    abstract fun bindDetailInjectorFactory(factory: DetailComponent.Factory?): AndroidInjector.Factory<*>?

}
