package com.marvel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marvel.navigation.NavGuide
import com.marvel.navigation.route.CharactersHomeRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navGuide: NavGuide

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navGuide.navigateTo(CharactersHomeRoute(R.id.fragmentContainer))
        }
    }
}