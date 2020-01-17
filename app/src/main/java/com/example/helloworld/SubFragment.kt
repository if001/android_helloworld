package com.example.helloworld

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_main.*

class SubFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_sub, container, false)
        val fab2 = v.findViewById<FloatingActionButton>(R.id.fab2)

        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)

        fab2?.setOnClickListener {
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.container, MainFragment())
            fragmentTransaction?.commit()
        }

        val animation = AnimationUtils.loadAnimation(v.context, R.anim.zoom_in)
        fab2?.startAnimation(animation)

        return v
    }


}