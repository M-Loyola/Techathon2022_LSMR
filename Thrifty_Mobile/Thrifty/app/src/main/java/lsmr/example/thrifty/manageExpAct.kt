package lsmr.example.thrifty

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import java.time.LocalDate

class manageExpAct : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemsArrayList: ArrayList<itemData>
    private lateinit var myAdapter: ItemListAdapter
    private lateinit var db: FirebaseFirestore
    var total: String = "0"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_exp)
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.expRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        itemsArrayList = arrayListOf()

        myAdapter = ItemListAdapter(this, itemsArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListener()

        var currdate = LocalDate.now()
        var x = currdate.month
        var y = currdate.dayOfMonth
//        val new = x.toString() + " " + y.toString()

//        Log.d(TAG, new)
        val username: String = intent.getStringExtra("username").toString()
        var totalWeeklyExpET = findViewById<EditText>(R.id.totalWeeklyExp)
        var monthly = totalWeeklyExpET
        when {
            x.toString()
                .contains("JANUARY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "JANUARY 7",
                monthly

            )
            x.toString()
                .contains("JANUARY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getData(
                username,
                "JANUARY 14", monthly
            )
            x.toString().contains(
                "JANUARY",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getData(username, "JANUARY 21", monthly)
            x.toString().contains(
                "JANUARY",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> getData(username, "JANUARY 30", monthly)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getData(username, "FEBRUARY 7", monthly)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getData(username, "FEBRUARY 14", monthly)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getData(username, "FEBRUARY 21", monthly)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 28) -> getData(username, "FEBRUARY 28", monthly)
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "MARCH 7", monthly
            )
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() > 7 && y <= 14) ->
                getData(
                    username,
                    "MARCH 14", monthly
                )
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getData(
                username,
                "MARCH 21", monthly
            )
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> getData(
                username,
                "MARCH 31", monthly
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "APRIL 7", monthly
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getData(
                username,
                "APRIL 14", monthly
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() > 21 && y <= 21) -> getData(
                username,
                "APRIL 21", monthly
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() > 21 && y <= 30) -> getData(
                username,
                "APRIL 30", monthly
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "MAY 07", monthly
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getData(
                username,
                "MAY 14", monthly
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getData(
                username,
                "MAY 21", monthly
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> getData(
                username,
                "MAY 31", monthly
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "JUNE 7", monthly
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getData(
                username,
                "JUNE 14", monthly
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getData(
                username,
                "JUNE 21", monthly
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 21 && y <= 30) -> getData(
                username,
                "JUNE 30", monthly
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "JULY 7", monthly
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getData(
                username,
                "JULY 14", monthly
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getData(
                username,
                "JULY 21", monthly
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> getData(
                username,
                "JULY 31", monthly
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "AUGUST 7", monthly
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getData(
                username,
                "AUGUST 14", monthly
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getData(
                username,
                "AUGUST 21", monthly
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> getData(
                username,
                "AUGUST 31",
                monthly
            )
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getData(username, "SEPTEMBER 7", monthly)
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getData(username, "SEPTEMBER 14", monthly)
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getData(username, "SEPTEMBER 21", monthly)
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> getData(username, "SEPTEMBER 30", monthly)
            x.toString()
                .contains("OCTOBER", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getData(
                username,
                "OCTOBER 7",
                monthly
            )
            x.toString()
                .contains("OCTOBER", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getData(
                username,
                "OCTOBER 14",
                monthly
            )
            x.toString().contains(
                "OCTOBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getData(username, "OCTOBER 21", monthly)
            x.toString().contains(
                "OCTOBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 31) -> getData(username, "OCTOBER 31", monthly)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getData(username, "NOVEMBER 7", monthly)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getData(username, "NOVEMBER 14", monthly)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getData(username, "NOVEMBER 21", monthly)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> getData(username, "NOVEMBER 30", monthly)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getData(username, "DECEMBER 7", monthly)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getData(username, "DECEMBER 14", monthly)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getData(username, "DECEMBER 21", monthly)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 31) -> getData(username, "DECEMBER 31", monthly)

        }

    }
    fun getData(username: String, z: String, monthly: EditText){
 //       val monthly_goal = monthly as TextView
        val db = FirebaseFirestore.getInstance()
        db.collection("user_goal")
            .document(username)
            .collection("date_goal")
//            .whereEqualTo("goal_date", z)
            .document("for_"+z)
            .collection("total_price")
            .document("items_total")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        var totalP = document.getLong("total")
                        val text = monthly as TextView
                        text.text = totalP.toString()

                    }
                }
            }
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
//
//                    val text = findViewById<EditText>(R.id.setWeeklyGoal) as TextView
//                    text.text = "${document.data.getValue("weeklyGoal")}"
//                    monthly_goal.text = "${document.data.getValue("monthlyGoal")}"
//
////                    dataPass(z)
//                }
//            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun EventChangeListener() {
        //  var queueList = arrayListOf("")
        val username: String = intent.getStringExtra("username").toString()
        var currdate = LocalDate.now()
        var x = currdate.month
        var y = currdate.dayOfMonth
        when {
            x.toString()
                .contains(
                    "JANUARY",
                    ignoreCase = true
                ) && (y.toInt() >= 1 && y <= 7) -> getItems("JANUARY 7", username)
            x.toString()
                .contains(
                    "JANUARY",
                    ignoreCase = true
                ) && (y.toInt() > 7 && y <= 14) -> getItems("JANUARY 14", username)
            x.toString().contains(
                "JANUARY",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getItems("JANUARY 21", username)
            x.toString().contains(
                "JANUARY",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> getItems("JANUARY 30", username)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getItems("FEBRUARY 7", username)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getItems("FEBRUARY 14", username)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getItems("FEBRUARY 21", username)
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 28) -> getItems("FEBRUARY 28", username)
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getItems(
                "MARCH 7", username
            )
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() > 7 && y <= 14) ->
                getItems(
                    "MARCH 14", username
                )
            x.toString()
                .contains(
                    "MARCH",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> getItems(
                "MARCH 21", username
            )
            x.toString()
                .contains(
                    "MARCH",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 31) -> getItems(
                "MARCH 31", username
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getItems(
                "APRIL 7", username
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getItems(
                "APRIL 14", username
            )
            x.toString()
                .contains(
                    "APRIL",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 21) -> getItems(
                "APRIL 21", username
            )
            x.toString()
                .contains(
                    "APRIL",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 30) -> getItems(

                "APRIL 30", username
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getItems(
                "MAY 07", username
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getItems(
                "MAY 14", username
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getItems(
                "MAY 21", username
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> getItems(
                "MAY 31", username
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getItems(
                "JUNE 7", username
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getItems(
                "JUNE 14", username
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getItems(
                "JUNE 21", username
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 21 && y <= 30) -> getItems(
                "JUNE 30", username
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> getItems(
                "JULY 7", username
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> getItems(
                "JULY 14", username
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> getItems(
                "JULY 21", username
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> getItems(
                "JULY 31", username
            )
            x.toString()
                .contains(
                    "AUGUST",
                    ignoreCase = true
                ) && (y.toInt() >= 1 && y <= 7) -> getItems(
                "AUGUST 7", username
            )
            x.toString()
                .contains(
                    "AUGUST",
                    ignoreCase = true
                ) && (y.toInt() > 7 && y <= 14) -> getItems(
                "AUGUST 14", username
            )
            x.toString()
                .contains(
                    "AUGUST",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> getItems(
                "AUGUST 21", username
            )
            x.toString()
                .contains(
                    "AUGUST",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 31) -> getItems(
                "AUGUST 31", username
            )
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getItems("SEPTEMBER 7", username)
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getItems("SEPTEMBER 14", username)
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getItems("SEPTEMBER 21", username)
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> getItems("SEPTEMBER 30", username)
            x.toString()
                .contains(
                    "OCTOBER",
                    ignoreCase = true
                ) && (y.toInt() >= 1 && y <= 7) -> getItems(
                username,
                "OCTOBER 7"
            )
            x.toString()
                .contains(
                    "OCTOBER",
                    ignoreCase = true
                ) && (y.toInt() > 7 && y <= 14) -> getItems(
                "OCTOBER 14", username
            )
            x.toString().contains(
                "OCTOBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getItems("OCTOBER 21", username)
            x.toString().contains(
                "OCTOBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 31) -> getItems("OCTOBER 31", username)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getItems("NOVEMBER 7", username)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getItems("NOVEMBER 14", username)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getItems("NOVEMBER 21", username)
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> getItems("NOVEMBER 30", username)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> getItems("DECEMBER 7", username)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> getItems("DECEMBER 14", username)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> getItems("DECEMBER 21", username)
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 31) -> getItems("DECEMBER 31", username)

        }


    }

    fun getItems(z: String, username: String) {
        db = FirebaseFirestore.getInstance()

        //toastMaker(username)
        db.collection("user_goal")
            .document(username)
            .collection("date_goal")
            .document("for_"+z)
            .collection("expense_items")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            itemsArrayList.add(
                                dc.document.toObject(
                                    itemData::class.java
                                )
                            )
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }
            })
    }
    fun goBack(view: View) {
        val dashboard = Intent(this, dashboardAct::class.java)
        startActivity(dashboard)
//        finish()
    }
}