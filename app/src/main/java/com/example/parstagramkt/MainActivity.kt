package com.example.parstagramkt

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

/**
 * Let user create a post by taking a photo with their camera
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->

            when (item.itemId) {

                R.id.action_home -> {
                    // TODO: Navigate to the home screen
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.action_compose -> {
                    // TODO: Navigate to the compose screen
                    Toast.makeText(this, "Compose", Toast.LENGTH_SHORT).show()
                }
                R.id.action_profile -> {
                    // TODO: Navigate to the Profile screen
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }
            }
            // Return true to say that we've handled this user interaction on the item
            true
        }

        queryPosts()
    }

    // Query for all posts in my server
    fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        // Find all Post objects
        query.include(Post.KEY_USER)
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    // Something went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser()?.username)
                        }
                    }
                }
            }

        })
    }

    companion object {
        const val TAG = "MainActivity"
    }

}