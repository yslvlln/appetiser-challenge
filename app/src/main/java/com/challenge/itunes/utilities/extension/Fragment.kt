package com.challenge.itunes.utilities.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


fun Fragment.redirect(): NavController {
    requireActivity().hideKeyboard()
    return NavHostFragment.findNavController(this)
}

fun Fragment.hideToolbar() {
    (activity as AppCompatActivity?)!!.supportActionBar?.hide()
}

fun Fragment.showToolbar() {
    (activity as AppCompatActivity?)!!.supportActionBar?.show()
}