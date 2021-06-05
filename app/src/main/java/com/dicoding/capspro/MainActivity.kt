package com.dicoding.capspro


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.dicoding.capspro.databinding.ActivityMainBinding
import com.dicoding.capspro.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        val name: TextView = findViewById(R.id.yourName)
        val photo: CircleImageView = findViewById(R.id.img_profile)
        name.text = currentUser?.displayName

        Glide.with(this).load(currentUser?.photoUrl).into(photo)

       binding.signOutBtn.setOnClickListener{
            mAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }**/

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, FormAddUpdateActivity::class.java)
            startActivityForResult(intent, FormAddUpdateActivity.REQUEST_ADD)
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_report, R.id.navigation_forum, R.id.navigation_maps
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}