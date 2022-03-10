package lsmr.example.thrifty

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class registerAct : AppCompatActivity() {

    //buttons
    lateinit var backBtn : ImageView
    lateinit var registerBtn: Button

    //edit texts
    var usernameReg: String = ""
    var emailReg: String = ""
    var passwordReg: String = ""
    var confpassReg: String = ""

    //firebase authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        //firebase authentication
        auth = FirebaseAuth.getInstance()

        //buttons
        backBtn = findViewById<ImageView>(R.id.arrBack_btn1)
        registerBtn = findViewById<Button>(R.id.registerBtn)

        //registration
        registerBtn.setOnClickListener {

            //edit texts
            usernameReg = findViewById<EditText>(R.id.usernameRegET).text.toString()
            emailReg = findViewById<EditText>(R.id.emailRegET).text.toString()
            passwordReg = findViewById<EditText>(R.id.passwordRegET).text.toString()
            confpassReg = findViewById<EditText>(R.id.c_passwordRegET).text.toString()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //check if input is an email


            when {
                usernameReg == "" -> {
                    Toast.makeText(
                        this,
                        "Username is empty",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                emailReg == "" -> {
                    Toast.makeText(
                        this,
                        "Email Address is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                passwordReg.length < 8 -> {
                    Toast.makeText(
                        this,
                        "Passwords should be more than 7 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                passwordReg == "" -> {
                    Toast.makeText(
                        this,
                        "Password is empty",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                passwordReg != confpassReg -> {
                    Toast.makeText(
                        this,
                        "Passwords don't match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
//                            if all text fields pass
                else -> {
//                            put here code for registration
                    if (emailReg.matches(emailPattern.toRegex())) {
                        val rootRef = FirebaseFirestore.getInstance()
                        rootRef.collection("users")
                            .document(usernameReg)
                            .get()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val document = task.result
                                    if (document.exists()) {
                                        val usernameExists = document.getString("username")
                                        if (usernameReg == usernameExists) {
                                            Toast.makeText(
                                                this,
                                                "Username is already taken, please choose another one.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        auth.createUserWithEmailAndPassword(emailReg, passwordReg)
                                            .addOnCompleteListener(this) { task ->
                                                if (task.isSuccessful) {
                                                    //insertion in firestore
                                                    val user = auth.currentUser

                                                    user!!.sendEmailVerification()
                                                        .addOnCompleteListener { task ->
                                                            if (task.isSuccessful) {
                                                                Log.d(
                                                                    TAG,
                                                                    "Email verification sent."
                                                                )
                                                            }
                                                        }
                                                    registerFireStore(
                                                        usernameReg,
                                                        emailReg
                                                    )
                                                    Toast.makeText(
                                                        this,
                                                        "Registration complete!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    finish()
                                                } else { //else if task is not successful in registration
                                                    Toast.makeText(
                                                        this,
                                                        "An error occured, please check your internet connection and try again",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                    else {
                        Toast.makeText(
                            this,
                            "Please enter a valid email address",
                            Toast.LENGTH_SHORT
                            )
                            .show()
                        }
                    }
                }
            }

        //back btn
        backBtn.setOnClickListener {
            finish()
        }

    }

    //data insertion
    fun registerFireStore(usernameReg: String, emailReg: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        db.collection("users")
            .document("user_count")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        val fetchnum = document.getLong("count")
                        user["username"] = usernameReg
                        user["email"] = emailReg
                        user["user_id"] = "user-"+fetchnum
                        user["dateCreated"] = FieldValue.serverTimestamp()

                        db.collection("users").document(usernameReg).set(user)
                        db.collection("users").document("user_count").update("count", FieldValue.increment(1))
                    }
                }
            }
        }
    }