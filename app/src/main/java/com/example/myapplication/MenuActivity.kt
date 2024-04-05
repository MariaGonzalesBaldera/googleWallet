package com.example.myapplication

import android.content.Intent

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuActivity : AppCompatActivity() {
    private lateinit var etPassword : EditText
    private lateinit var etUsername : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnLoginGoogle : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        btnLogin = findViewById<Button>(R.id.btnLogin);
        btnLoginGoogle = findViewById<Button>(R.id.btnLoginGoogle);
        btnLogin.setOnClickListener {
            getAccessToken();
        }
        btnLoginGoogle.setOnClickListener {
            getGoogle();
        }
    }
    fun getAccessToken(){
        val service = RetrofitClientInstance
            .getRetrofitInstance().create(GetDataService::class.java);
        val password = etPassword.getText().toString();
        val username = etUsername.getText().toString();
        val call:Call<AccessToken> = service.getAccessToken(
            "Login",
            "password",
            "shkv23GdOwephMFORhxwqONehvT63tqt",
            "openid",
            username,
            password);
        call.enqueue(object : Callback<AccessToken>{
            override fun onResponse(call: Call<AccessToken>,
                                    response: Response<AccessToken>){
                if(response.isSuccessful){
                    val intent = Intent(this@MenuActivity, CheckoutActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MenuActivity, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<AccessToken>, t:Throwable){
                Toast.makeText(this@MenuActivity, "Error: $t", Toast.LENGTH_LONG).show();
            }
        });
    }
    fun getGoogle(){
        Toast.makeText(this@MenuActivity, "mensaje de google ", Toast.LENGTH_LONG).show()

    }
}