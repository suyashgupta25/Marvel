package com.marvel

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.marvel.BuildConfig.IMAGE_DISK_CACHE_NAME
import com.marvel.BuildConfig.IMAGE_DISK_CACHE_PERCENT
import com.marvel.BuildConfig.IMAGE_MEMEORY_CACHE_PERCENT
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun newImageLoader() =
        ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(IMAGE_MEMEORY_CACHE_PERCENT)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve(IMAGE_DISK_CACHE_NAME))
                    .maxSizePercent(IMAGE_DISK_CACHE_PERCENT)
                    .build()
            }
            .build()
}