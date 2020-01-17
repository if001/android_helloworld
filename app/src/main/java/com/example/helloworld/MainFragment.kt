package com.example.helloworld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)

        val fab = v.findViewById<FloatingActionButton>(R.id.fab)

        val fragmentTransaction = fragmentManager?.beginTransaction()

        fragmentTransaction?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)


        val animation = AnimationUtils.loadAnimation(v.context, R.anim.rotation)


        fab?.setOnClickListener {
            // it?.startAnimation(animation)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.container, SubFragment())
            fragmentTransaction?.commit()
        }

        return v
    }

}