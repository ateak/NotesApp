package ru.study.notesapp.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.study.notesapp.di.viewModelModule
import ru.study.notesapp.di.dataModule
import ru.study.notesapp.di.domainModule

/**
 * Активируем Koin
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(dataModule, viewModelModule, domainModule))
        }
    }
}
