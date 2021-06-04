package com.dicoding.capspro.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.capspro.R
import com.dicoding.capspro.utils.DataDummy
import com.dicoding.capspro.databinding.FragmentHomeBinding
import com.dicoding.capspro.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView

class HomeFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        val name: TextView = view.findViewById(R.id.yourName)
        val photo: CircleImageView = view.findViewById(R.id.img_profile)
        val btn: Button = view.findViewById(R.id.sign_out_btn)
        name.text = currentUser?.displayName

        Glide.with(this).load(currentUser?.photoUrl).into(photo)

        if (activity != null) {
            val articles = DataDummy.generateDummyCourses()
            val homeAdapter = HomeAdapter()
            homeAdapter.setArticles(articles)

            with(fragmentHomeBinding.rvHome) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = homeAdapter
            }
        }
        btn.setOnClickListener{
            mAuth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}