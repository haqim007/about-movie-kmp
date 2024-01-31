package dev.haqim.aboutmovie.util

import dev.haqim.aboutmovie.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin { 
        modules(getSharedModules())
    }
}