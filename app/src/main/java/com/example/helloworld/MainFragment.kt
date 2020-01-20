package com.example.helloworld

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import info.androidhive.fontawesome.FontDrawable
import info.androidhive.fontawesome.FontTextView

class MainFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)

        val fab = v.findViewById<FloatingActionButton>(R.id.fab)
        val drawable = FontDrawable(v.context, R.string.fa_paper_plane_solid, true, false)
        drawable.setTextColor(ContextCompat.getColor(v.context, android.R.color.white))
        fab.setImageDrawable(drawable)


        val fragmentTransaction = fragmentManager?.beginTransaction()

        fragmentTransaction?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)


        val animation = AnimationUtils.loadAnimation(v.context, R.anim.rotation)


        fab?.setOnClickListener {
            // it?.startAnimation(animation)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.container, SubFragment())
            fragmentTransaction?.commit()
        }

        val button = v.findViewById<Button>(R.id.button)
        button.setOnClickListener{
            // toastMake(v, "An error has occurred")
            toastMake(v, "登録に失敗しました", R.string.fa_times_circle)
        }
        return v
    }


    private fun toastMake(v: View, msg: String, icon: Int){
        val toast = Toast(context)

        val viewGroup = v.findViewById<ViewGroup>(R.id.relative_layout)

        val toastView = layoutInflater.inflate(R.layout.custom_toast, viewGroup)

        val messageView = toastView.findViewById<TextView>(R.id.message)
        messageView.text = msg

        val iconView = toastView.findViewById<FontTextView>(R.id.icon)
        iconView.text = resources.getString(icon)

        toast.view = toastView
        toast.duration = Toast.LENGTH_LONG
        toast.show()
    }


}