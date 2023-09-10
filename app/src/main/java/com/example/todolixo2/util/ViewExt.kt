package com.example.todolixo2.util

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

inline fun SearchView.onQueryTextChanged(crossinline listenerLb: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listenerLb(newText.orEmpty())
            return true
        }

    })
}


fun logViewHierarchy(view: View, indent: String = "") {
    Log.d("ViewHierarchy", "$indent${view.javaClass.simpleName} - ID: ${view.id}")
    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            logViewHierarchy(view.getChildAt(i), "$indent--")
        }
    }
}