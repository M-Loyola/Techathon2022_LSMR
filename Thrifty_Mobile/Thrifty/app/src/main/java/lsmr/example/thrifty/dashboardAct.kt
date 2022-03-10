package lsmr.example.thrifty

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.util.*


class dashboardAct : AppCompatActivity() {

    //buttons
    lateinit var logoutBtn: Button
    lateinit var addExpBtn: Button
    lateinit var mngExpBtn: Button
    lateinit var editBtn: ImageButton

    //Edit Strings
    var setWeeklyGoal: String = "0"
    lateinit var calMonthlyGoal: EditText


    //user
    lateinit var userWelcomeTV: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()

        userWelcomeTV = findViewById(R.id.userWelcomeTV)

        val username: String = intent.getStringExtra("username").toString()
        userWelcomeTV.text = "Welcome " + username + "!"
        //buttons
        logoutBtn = findViewById<Button>(R.id.logoutBtn)
        addExpBtn = findViewById<Button>(R.id.addExpBtn)
        mngExpBtn = findViewById<Button>(R.id.manageExpBtn)
        editBtn = findViewById<ImageButton>(R.id.editBtn)


        //logout button
        logoutBtn.setOnClickListener {
            val dialogLogout = AlertDialog.Builder(this)
                .setMessage("Are you sure you want to logout?")
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(this, "Cancel Logout", Toast.LENGTH_SHORT)
                }
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT)
                    val intent = Intent(this, loginAct::class.java)
                    startActivity(intent)
                    finish()
                }.create()
            dialogLogout.show()
        }


        //addExp
        addExpBtn.setOnClickListener {
            var currdate = LocalDate.now()
            var x = currdate.month
            var y = currdate.dayOfMonth
            when {
                x.toString()
                    .contains(
                        "JANUARY",
                        ignoreCase = true
                    ) && (y.toInt() >= 1 && y <= 7) -> passData("JANUARY 7",username)
                x.toString()
                    .contains(
                        "JANUARY",
                        ignoreCase = true
                    ) && (y.toInt() > 7 && y <= 14) -> passData("JANUARY 14",username)
                x.toString().contains(
                    "JANUARY",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> passData("JANUARY 21",username)
                x.toString().contains(
                    "JANUARY",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 30) -> passData("JANUARY 30",username)
                x.toString().contains(
                    "FEBRUARY",
                    ignoreCase = true
                ) && (y.toInt() >= 1 && y <= 7) -> passData("FEBRUARY 7",username)
                x.toString().contains(
                    "FEBRUARY",
                    ignoreCase = true
                ) && (y.toInt() > 7 && y <= 14) -> passData("FEBRUARY 14",username)
                x.toString().contains(
                    "FEBRUARY",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> passData("FEBRUARY 21",username)
                x.toString().contains(
                    "FEBRUARY",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 28) -> passData("FEBRUARY 28",username)
                x.toString()
                    .contains("MARCH", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> passData(
                    "MARCH 7",username
                )
                x.toString()
                    .contains("MARCH", ignoreCase = true) && (y.toInt() > 7 && y <= 14) ->
                    passData(
                        "MARCH 14",username
                    )
                x.toString()
                    .contains(
                        "MARCH",
                        ignoreCase = true
                    ) && (y.toInt() > 14 && y <= 21) -> passData(
                    "MARCH 21",username
                )
                x.toString()
                    .contains(
                        "MARCH",
                        ignoreCase = true
                    ) && (y.toInt() > 21 && y <= 31) -> passData(
                    "MARCH 31",username
                )
                x.toString()
                    .contains("APRIL", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> passData(
                    "APRIL 7",username
                )
                x.toString()
                    .contains("APRIL", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> passData(
                    "APRIL 14",username
                )
                x.toString()
                    .contains(
                        "APRIL",
                        ignoreCase = true
                    ) && (y.toInt() > 21 && y <= 21) -> passData(
                    "APRIL 21",username
                )
                x.toString()
                    .contains(
                        "APRIL",
                        ignoreCase = true
                    ) && (y.toInt() > 21 && y <= 30) -> passData(

                    "APRIL 30",username
                )
                x.toString()
                    .contains("MAY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> passData(
                    "MAY 07",username
                )
                x.toString()
                    .contains("MAY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> passData(
                    "MAY 14",username
                )
                x.toString()
                    .contains("MAY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> passData(
                    "MAY 21",username
                )
                x.toString()
                    .contains("MAY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> passData(
                    "MAY 31",username
                )
                x.toString()
                    .contains("JUNE", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> passData(
                    "JUNE 7",username
                )
                x.toString()
                    .contains("JUNE", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> passData(
                    "JUNE 14",username
                )
                x.toString()
                    .contains("JUNE", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> passData(
                    "JUNE 21",username
                )
                x.toString()
                    .contains("JUNE", ignoreCase = true) && (y.toInt() > 21 && y <= 30) -> passData(
                    "JUNE 30",username
                )
                x.toString()
                    .contains("JULY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> passData(
                    "JULY 7",username
                )
                x.toString()
                    .contains("JULY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> passData(
                    "JULY 14",username
                )
                x.toString()
                    .contains("JULY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> passData(
                    "JULY 21",username
                )
                x.toString()
                    .contains("JULY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> passData(
                    "JULY 31",username
                )
                x.toString()
                    .contains(
                        "AUGUST",
                        ignoreCase = true
                    ) && (y.toInt() >= 1 && y <= 7) -> passData(
                    "AUGUST 7",username
                )
                x.toString()
                    .contains(
                        "AUGUST",
                        ignoreCase = true
                    ) && (y.toInt() > 7 && y <= 14) -> passData(
                    "AUGUST 14",username
                )
                x.toString()
                    .contains(
                        "AUGUST",
                        ignoreCase = true
                    ) && (y.toInt() > 14 && y <= 21) -> passData(
                    "AUGUST 21",username
                )
                x.toString()
                    .contains(
                        "AUGUST",
                        ignoreCase = true
                    ) && (y.toInt() > 21 && y <= 31) -> passData(
                    "AUGUST 31",username
                )
                x.toString().contains(
                    "SEPTEMBER",
                    ignoreCase = true
                ) && (y.toInt() >= 1 && y <= 7) -> passData("SEPTEMBER 7",username)
                x.toString().contains(
                    "SEPTEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 7 && y <= 14) -> passData("SEPTEMBER 14",username)
                x.toString().contains(
                    "SEPTEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> passData("SEPTEMBER 21",username)
                x.toString().contains(
                    "SEPTEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 30) -> passData("SEPTEMBER 30",username)
                x.toString()
                    .contains(
                        "OCTOBER",
                        ignoreCase = true
                    ) && (y.toInt() >= 1 && y <= 7) -> passData(
                    username,
                    "OCTOBER 7"
                )
                x.toString()
                    .contains(
                        "OCTOBER",
                        ignoreCase = true
                    ) && (y.toInt() > 7 && y <= 14) -> passData(
                    "OCTOBER 14",username
                )
                x.toString().contains(
                    "OCTOBER",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> passData("OCTOBER 21",username)
                x.toString().contains(
                    "OCTOBER",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 31) -> passData("OCTOBER 31",username)
                x.toString().contains(
                    "NOVEMBER",
                    ignoreCase = true
                ) && (y.toInt() >= 1 && y <= 7) -> passData("NOVEMBER 7",username)
                x.toString().contains(
                    "NOVEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 7 && y <= 14) -> passData("NOVEMBER 14",username)
                x.toString().contains(
                    "NOVEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> passData("NOVEMBER 21",username)
                x.toString().contains(
                    "NOVEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 30) -> passData("NOVEMBER 30",username)
                x.toString().contains(
                    "DECEMBER",
                    ignoreCase = true
                ) && (y.toInt() >= 1 && y <= 7) -> passData("DECEMBER 7",username)
                x.toString().contains(
                    "DECEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 7 && y <= 14) -> passData("DECEMBER 14",username)
                x.toString().contains(
                    "DECEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 14 && y <= 21) -> passData("DECEMBER 21",username)
                x.toString().contains(
                    "DECEMBER",
                    ignoreCase = true
                ) && (y.toInt() > 21 && y <= 31) -> passData("DECEMBER 31",username)

            }


        }


        //manage expense intent
        mngExpBtn.setOnClickListener {

            //val intentMng = Intent(this, manageExpAct::class.java)
            //startActivity(intentMng)
            val intentMng = Intent(this, manageExpAct::class.java)
            intentMng.putExtra(
                "username",
                username
            )
            startActivity(intentMng)
            finish()
        }

        //edit button
        editBtn.setOnClickListener {
            //edit tex value
            var setWeeklyGoalET = findViewById<EditText>(R.id.setWeeklyGoal)
            //actual value to String
            setWeeklyGoal = setWeeklyGoalET.text.toString()

            // val username1: String = intent.getStringExtra("username").toString()
            Log.d(TAG, setWeeklyGoal)
            setGoal(setWeeklyGoal, username)
            var calc = 4.0 * (setWeeklyGoal.toDouble())
            var calMonthlyGoalET = findViewById<EditText>(R.id.monthlyGoal) as TextView
            calMonthlyGoalET.text = calc.toString()
        }

        var calc = 4.0 * (setWeeklyGoal.toDouble())
        var calMonthlyGoalET = findViewById<EditText>(R.id.monthlyGoal)


        var monthly = calMonthlyGoalET
        var currdate = LocalDate.now()
        var x = currdate.month
        var y = currdate.dayOfMonth
//        val new = x.toString() + " " + y.toString()

//        Log.d(TAG, new)

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


    //Get Data from Database
    fun getData(username: String, z: String, monthly: EditText) {
        //val username2: String = intent.getStringExtra("username").toString()
        val monthly_goal = monthly as TextView
        val db = FirebaseFirestore.getInstance()
        db.collection("user_goal")
            .document(username)
            .collection("date_goal")
            .whereEqualTo("goal_date", z)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                    val text = findViewById<EditText>(R.id.setWeeklyGoal) as TextView
                    text.text = "${document.data.getValue("weeklyGoal")}"
                    monthly_goal.text = "${document.data.getValue("monthlyGoal")}"

//                    dataPass(z)
                }
            }
    }
//
//    fun dataPass(z: String){
//        val datapass = z
//        return dataPass(datapass)
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setGoal(setWeeklyGoal: String, username: String) {
        val username1 = username

        var calendar = Calendar.getInstance()
//        val day = calendar[Calendar.WEEK_OF_MONTH]
//        var period = Period.of(0, 0, 7)
        var currdate = LocalDate.now()
        var x = currdate.month
        var y = currdate.dayOfMonth
//        var addedDate = currdate.plus(period)
//        var day_string: String
        Log.d(TAG, y.toString())
        when {
            x.toString()
                .contains("JANUARY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JANUARY 7"
            )
            x.toString()
                .contains("JANUARY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JANUARY 14"
            )
            x.toString().contains(
                "JANUARY",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> insertGoal(setWeeklyGoal, username1, "JANUARY 21")
            x.toString().contains(
                "JANUARY",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> insertGoal(setWeeklyGoal, username1, "JANUARY 30")
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> insertGoal(setWeeklyGoal, username1, "FEBRUARY 7")
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> insertGoal(setWeeklyGoal, username1, "FEBRUARY 14")
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> insertGoal(setWeeklyGoal, username1, "FEBRUARY 21")
            x.toString().contains(
                "FEBRUARY",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 28) -> insertGoal(setWeeklyGoal, username1, "FEBRUARY 28")
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "MARCH 7"
            )
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() > 7 && y <= 14) ->
                insertGoal(
                    setWeeklyGoal,
                    username1,
                    "MARCH 14"
                )
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> insertGoal(
                setWeeklyGoal,
                username1,
                "MARCH 21"
            )
            x.toString()
                .contains("MARCH", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> insertGoal(
                setWeeklyGoal,
                username1,
                "MARCH 31"
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "APRIL 7"
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> insertGoal(
                setWeeklyGoal,
                username1,
                "APRIL 14"
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() > 21 && y <= 21) -> insertGoal(
                setWeeklyGoal,
                username1,
                "APRIL 21"
            )
            x.toString()
                .contains("APRIL", ignoreCase = true) && (y.toInt() > 21 && y <= 30) -> insertGoal(
                setWeeklyGoal,
                username1,
                "APRIL 30"
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "MAY 07"
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> insertGoal(
                setWeeklyGoal,
                username1,
                "MAY 14"
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> insertGoal(
                setWeeklyGoal,
                username1,
                "MAY 21"
            )
            x.toString()
                .contains("MAY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> insertGoal(
                setWeeklyGoal,
                username1,
                "MAY 31"
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JUNE 7"
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JUNE 14"
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JUNE 21"
            )
            x.toString()
                .contains("JUNE", ignoreCase = true) && (y.toInt() > 21 && y <= 30) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JUNE 30"
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JULY 7"
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JULY 14"
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JULY 21"
            )
            x.toString()
                .contains("JULY", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> insertGoal(
                setWeeklyGoal,
                username1,
                "JULY 31"
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "AUGUST 7"
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> insertGoal(
                setWeeklyGoal,
                username1,
                "AUGUST 14"
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() > 14 && y <= 21) -> insertGoal(
                setWeeklyGoal,
                username1,
                "AUGUST 21"
            )
            x.toString()
                .contains("AUGUST", ignoreCase = true) && (y.toInt() > 21 && y <= 31) -> insertGoal(
                setWeeklyGoal,
                username1,
                "AUGUST 31"
            )
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> insertGoal(setWeeklyGoal, username1, "SEPTEMBER 7")
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> insertGoal(setWeeklyGoal, username1, "SEPTEMBER 14")
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> insertGoal(setWeeklyGoal, username1, "SEPTEMBER 21")
            x.toString().contains(
                "SEPTEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> insertGoal(setWeeklyGoal, username1, "SEPTEMBER 30")
            x.toString()
                .contains("OCTOBER", ignoreCase = true) && (y.toInt() >= 1 && y <= 7) -> insertGoal(
                setWeeklyGoal,
                username1,
                "OCTOBER 7"
            )
            x.toString()
                .contains("OCTOBER", ignoreCase = true) && (y.toInt() > 7 && y <= 14) -> insertGoal(
                setWeeklyGoal,
                username1,
                "OCTOBER 14"
            )
            x.toString().contains(
                "OCTOBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> insertGoal(setWeeklyGoal, username1, "OCTOBER 21")
            x.toString().contains(
                "OCTOBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 31) -> insertGoal(setWeeklyGoal, username1, "OCTOBER 31")
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> insertGoal(setWeeklyGoal, username1, "NOVEMBER 7")
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> insertGoal(setWeeklyGoal, username1, "NOVEMBER 14")
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> insertGoal(setWeeklyGoal, username1, "NOVEMBER 21")
            x.toString().contains(
                "NOVEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 30) -> insertGoal(setWeeklyGoal, username1, "NOVEMBER 30")
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() >= 1 && y <= 7) -> insertGoal(setWeeklyGoal, username1, "DECEMBER 7")
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 7 && y <= 14) -> insertGoal(setWeeklyGoal, username1, "DECEMBER 14")
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 14 && y <= 21) -> insertGoal(setWeeklyGoal, username1, "DECEMBER 21")
            x.toString().contains(
                "DECEMBER",
                ignoreCase = true
            ) && (y.toInt() > 21 && y <= 31) -> insertGoal(setWeeklyGoal, username1, "DECEMBER 31")
        }
    }


    fun passData(z: String, username: String) {

        val intentMn = Intent(this, addExpAct::class.java)
        intentMn.putExtra(
            "username",
            username
        )
        intentMn.putExtra(
            "passcurrdate",
            z
        )
        startActivity(intentMn)
        finish()
    }

    //insert weekly goal
    //    @RequiresApi(Build.VERSION_CODES.O)
    fun insertGoal(setWeeklyGoal: String, username1: String, x: String) {
      //  Log.d(TAG, setWeeklyGoal + username1 + x)
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["username"] = username1
        user["weeklyGoal"] = setWeeklyGoal
        user["monthlyGoal"] = setWeeklyGoal.toDouble() * 4.0
        user["goal_date"] = x

        db.collection("user_goal").document(username1).collection("date_goal").document("for_" + x)
            .set(user)

        db.collection("user_goal").document(username1).collection("date_goal").document("for_" + x)
            .update("items_count",FieldValue.increment(1))
    }

}


