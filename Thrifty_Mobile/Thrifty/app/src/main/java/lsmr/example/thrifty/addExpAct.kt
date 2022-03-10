package lsmr.example.thrifty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class addExpAct : AppCompatActivity() {
    var additem: String = ""
    var addexpense: String = ""
    lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exp)
        supportActionBar?.hide()
        saveBtn = findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {

            additem = findViewById<EditText>(R.id.itemName).text.toString()
            addexpense = findViewById<EditText>(R.id.itemExp).text.toString()
            val username: String = intent.getStringExtra("username").toString()
            val currdate: String = intent.getStringExtra("passcurrdate").toString()

            when {
                additem == "" -> {
                    Toast.makeText(this, "Username is empty!", Toast.LENGTH_SHORT).show()
                }
                addexpense == "" -> {
                    Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show()
                }
                //successful input
                else -> {
                    val db = FirebaseFirestore.getInstance()
                    val user: MutableMap<String, Any> = HashMap()

                    user["item"] = additem
                    user["price"] = addexpense

                    db.collection("user_goal").document(username).collection("date_goal")
                        .document("for_" + currdate).collection("expense_items").document()
                        .set(user)

                    db.collection("user_goal").document(username).collection("date_goal")
                        .document("for_" + currdate).collection("total_price").document("items_total")
//                        .set(z)
                        .get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val document = task.result
                                if (document.exists()) {
//                                    var count =
//                                        document.getLong("count")
//                                    val count1 = count!! +1
//
//                                    user["item"] = additem
//                                    user["price"] = addexpense
//                                    user["item_id"] = "item-"+ count1
//
//                                    db.collection("user_goal").document(username).collection("date_goal")
//                                        .document("for_" + currdate).collection("expense_items").document("item-"+ count1)
//                                        .set(user)

                                    db.collection("user_goal").document(username)
                                        .collection("date_goal").document("for_" + currdate)
                                        .collection("total_price").document("items_total")
                                        .update("total", FieldValue.increment(addexpense.toLong()))
                                }
                                else{
                                    val x: MutableMap<String, Any> = HashMap()

                                    x["item"] = addexpense.toLong()
                                    db.collection("user_goal").document(username)
                                        .collection("date_goal").document("for_" + currdate)
                                        .collection("total_price").document("items_total")
                                        .set(x)

                                }
                                }
                                }
//                                else{
//                                    user["item"] = additem
//                                    user["price"] = addexpense
//                                    user["item_id"] = "item-1"
//
//                                    db.collection("user_goal").document(username).collection("date_goal")
//                                        .document("for_" + currdate).collection("expense_items").document("item-1")
//                                        .set(user)
//
//                                    val x: MutableMap<String, Any> = HashMap()
//                                    x["count"] = 1
//
//                                    db.collection("user_goal").document(username)
//                                        .collection("date_goal").document("for_" + currdate)
//                                        .collection("item_count").document("item_count")
//                                        .set(x)

//                               }
//                            }
//                        }

//                    db.collection("user_goal").document(username).collection("date_goal")
//                        .document("for_" + currdate).collection("expense_items").document()
//                        .set(user)
//
//                    val rootRef = FirebaseFirestore.getInstance()
//                    val z: MutableMap<String, Any> = HashMap()
//                    z ["item_nums"] = FieldValue.increment(1)
//
//                    rootRef.collection("user_goal").document(username).collection("date_goal")
//                        .document("for_" + currdate).collection("expense_items").document("items_count")
////                        .set(z)
//                        .get()
//                        .addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                val document = task.result
//                                if (document.exists()) {
//                                    val fetchusername =
//                                        document.getString("username")
//                                    db.collection("user_goal").document(username)
//                                        .collection("date_goal").document("for_" + currdate)
//                                        .collection("expense_items").document("item_count")
//                                        .update("item_count", FieldValue.increment(1))
//                                }
//                            }
//                        }




                    Toast.makeText(
                        this,
                        "Item Added!",
                        Toast.LENGTH_SHORT
                    ).show()
//                    val dashboard = Intent(this, dashboardAct::class.java)
//                    startActivity(dashboard)
                }
            }

        }


    }

    fun goBack(view: View) {
        val dashboard = Intent(this, dashboardAct::class.java)
        startActivity(dashboard)
//        finish()
    }
}