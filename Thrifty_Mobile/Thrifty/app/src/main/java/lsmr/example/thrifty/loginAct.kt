package lsmr.example.thrifty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class loginAct : AppCompatActivity() {

    //buttons
    lateinit var loginBtn : Button
    lateinit var goto_registerBtn : TextView
    lateinit var forgotPassBtn : TextView

    //user input : edit texts to String
    var username :String = ""
    var password :String = ""

    //edit to blank if wrong
    lateinit var usernameET: EditText
    lateinit var passwordET: EditText

    //firebase authentication
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        //firebase auth
        auth= FirebaseAuth.getInstance()

        //buttons
        loginBtn = findViewById<Button>(R.id.loginBtn)
        goto_registerBtn = findViewById<TextView>(R.id.goto_registerBtn)
        forgotPassBtn = findViewById<TextView>(R.id.forgotPassBtn)

        //login button activity
        loginBtn.setOnClickListener{

            username = findViewById<EditText>(R.id.usernameET).text.toString()
            password = findViewById<EditText>(R.id.passwordET).text.toString()

            when {
                username == "" -> {
                    Toast.makeText(this, "Username is empty!",Toast.LENGTH_SHORT).show()
                }
                password == ""-> {
                    Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show()
                }
                //when login is successful
                else -> {
                  //  checkemailIsVerified()


                    val rootRef = FirebaseFirestore.getInstance()
                    rootRef.collection("users").document(username)
                        .get()
                        .addOnCompleteListener{ task ->
                            if (task.isSuccessful){
                                val document = task.result
                                if (document.exists()){
                                    val email = document.getString("email").toString()
                                    auth.signInWithEmailAndPassword(email, password)
                                        .addOnSuccessListener {
                                            val user = FirebaseAuth.getInstance().currentUser

                                            if (user!!.isEmailVerified) {
                                                val fetchusername = document.getString("username")
                                                if (fetchusername == username) {
                                                    Toast.makeText(
                                                        this,
                                                        "Logging in",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    val intentDashboard = Intent (this, dashboardAct::class.java)
                                                    intentDashboard.putExtra(
                                                        "username",
                                                        username
                                                    )
                                                    startActivity(intentDashboard)
                                                    finish()
                                                }
                                                // user is verified, so you can finish this activity or send user to activity which you want.
                                               // finish()
                                                //  Toast.makeText(this@LoginActivity, "Successfully logged in", Toast.LENGTH_SHORT).show()
                                            } else {
                                                // email is not verified, so just prompt the message to the user and restart this activity.
                                                // NOTE: don't forget to log out the user.
                                                Toast.makeText(
                                                    this,
                                                    "Email is not yet verified, check your email then verify your account.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                FirebaseAuth.getInstance().signOut()

                                                //restart this activity
                                            }

                                        }
                                        .addOnFailureListener {
//                                            usernameET.setText("") //clear edit text when wrong input
//                                            passwordET.setText("") //clear edit text when wrong input
                                            Toast.makeText(
                                                this,
                                                "Invalid username or password",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                else{
//                                usernameET.setText(
//                                    ""
//                                ) //clear edit text when wrong input
//                                passwordET.setText(
//                                    ""
//                                ) //clear edit text when wrong input
                                    Toast.makeText(
                                        this,
                                        "No such user exists, if you are new, please create an account.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                }

                            }
                        }
                    }
                }



        //register button activity
        goto_registerBtn.setOnClickListener {
            val gotoReg = Intent(this, registerAct::class.java)
            startActivity(gotoReg)
        }

        //forgot password activity
        forgotPassBtn.setOnClickListener {
            Toast.makeText(this, "Oh no you forgot your password", Toast.LENGTH_SHORT).show()
        }

    }
//    fun checkemailIsVerified(){
//
//    }
}