package com.philoxenic.ithute

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.philoxenic.ithute.model.MainItem
import com.philoxenic.ithute.adapter.MainAdapter;
import androidx.core.view.GravityCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val mainitems = ArrayList<MainItem>()
        mainitems.add(MainItem("Assigned Tasks", "Keep Up to date with your school work. Earn points by completing tasks.", R.drawable.ic_assigned))
        mainitems.add(MainItem("Schedule", "Keep track of upcoming tasks.", R.drawable.ic_schedule))
        mainitems.add(MainItem("Report", "Keep track of your grades.", R.drawable.ic_report))
        mainitems.add(MainItem("Leaderboard", "Track who are your grade top achievers. The leaderboard tracks which learners have earned the most points.", R.drawable.ic_leaderboard))
        mainitems.add(MainItem("Engage", "Keep in touch with school mates.", R.drawable.ic_engage))
        mainitems.add(MainItem("Digital Diary", "Saved collection of notes, tutorials and collectibles.", R.drawable.ic_diary))

        val adapter = MainAdapter(mainitems)

        recyclerView.adapter = adapter

        adapter.onItemClick = {
                mainitems ->

            Toast.makeText(this, mainitems.task, Toast.LENGTH_SHORT).show()

            if (mainitems.task.equals("Assigned Tasks")){
                val intent = Intent (this, TaskActivity::class.java)
                intent.putExtra("MainItem", mainitems)
                startActivity(intent)
            }
            else if (mainitems.task.equals("Schedule")){
                val intent = Intent (this, ScheduleActivity::class.java)
                intent.putExtra("MainItem", mainitems)
                startActivity(intent)
            }
            else if (mainitems.task.equals("Report")){
                val intent = Intent (this, ReportActivity::class.java)
                intent.putExtra("MainItem", mainitems)
                startActivity(intent)
            }

        }
        //create bottom navigation view object
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigationView.setSelectedItemId(R.id.bottomNav)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home ->  bottomNavigationView.setSelectedItemId(R.id.menu_home)
                R.id.menu_notifications -> {
                    val intent = Intent(this, NotificationsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.menu_settings ->  {
                    val intent = Intent(this, Settings::class.java)
                    startActivity(intent)
                    finish()
                }

            }
            true
        }
    }

    //appbar - toolbar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_help -> {
                val intent = Intent(this, HelpActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}