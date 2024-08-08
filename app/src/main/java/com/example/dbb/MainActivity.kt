package com.example.dbb
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dbb.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.collection.LLRBNode.Color
import com.google.firebase.database.collection.LLRBNode.Color.RED
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        database = Firebase.database.reference
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val etName = binding.etName
        val etMail = binding.etMail
        val userId = binding.etUserName
        val userPassword = binding.etPassword
        val check=binding.checkBox


        val db = Firebase.firestore

        binding.btnSignUp.setOnClickListener {
            if(check.isChecked){
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()
            val user = User(name, mail, password, uniqueId);

            db.collection("Users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this,"user registered",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this,"user ",Toast.LENGTH_SHORT).show()
                }
        }else{
                Toast.makeText(this,"Please accept the T&amp;C ",Toast.LENGTH_SHORT).show()
            }
        }
    }
}