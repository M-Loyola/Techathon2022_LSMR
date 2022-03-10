package lsmr.example.thrifty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //delay timer to go to login page (HomeLogin)
        Handler().postDelayed({
            startActivity(
                Intent(
                    this@MainActivity,
                    loginAct::class.java
                )
            )
            finish()
        }, 2000)

    }
}