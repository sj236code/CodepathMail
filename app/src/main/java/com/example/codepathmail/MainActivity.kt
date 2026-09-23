package com.example.codepathmail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var emails: MutableList<Email>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the RecyclerView
        val emailsRv = findViewById<RecyclerView>(R.id.emailsRv)

        // Get the first 10 emails
        emails = EmailFetcher.getEmails()

        // Create the adapter
        val adapter = EmailAdapter(emails)

        // Connect the adapter to the RecyclerView
        emailsRv.adapter = adapter

        // Display emails vertically
        emailsRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.loadMoreBtn).setOnClickListener {

            // Get the next 5 emails
            val newEmails = EmailFetcher.getNext5Emails()

            // Add them to our current list
            emails.addAll(newEmails)

            // Tell the RecyclerView that the data changed
            adapter.notifyDataSetChanged()
        }
    }
}