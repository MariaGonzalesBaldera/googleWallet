package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ActivityPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal);
        Toast.makeText(this@ActivityPrincipal, "Principal", Toast.LENGTH_LONG).show()
    }
}