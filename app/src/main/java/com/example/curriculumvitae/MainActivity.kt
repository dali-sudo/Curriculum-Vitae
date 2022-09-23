package com.example.curriculumvitae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.text.TextUtils
import android.widget.Toast
import android.util.Patterns.EMAIL_ADDRESS




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.next)
        val resetButton =findViewById<Button>(R.id.reset)
        val Name = findViewById<EditText>(R.id.NametextInputEditLayout)
        val Age = findViewById<EditText>(R.id.AgeTextInput)
        val Email = findViewById<EditText>(R.id.EmailInputText)

        findViewById<SeekBar>(R.id.IOS).setMax(100)
        findViewById<SeekBar>(R.id.Android).setMax(100)
        findViewById<SeekBar>(R.id.Flutter).setMax(100)

        val map = mapOf("IOS" to R.id.IOS,
            "Android" to R.id.Android,
            "Flutter" to R.id.Flutter)

        submitButton.setOnClickListener {
            if( (TextUtils.isEmpty(Name.text.toString())) && (TextUtils.isEmpty(Age.text.toString()))&& (TextUtils.isEmpty(Email.text.toString()))) {

                Toast.makeText(this,"Empty Field Not Allowed!!",Toast.LENGTH_LONG).show()

            }

            else if(!EMAIL_ADDRESS.matcher(Email.text).matches()) {

                Toast.makeText(this, "Wrong Email format", Toast.LENGTH_SHORT).show()

            }
            else {

                checkBest(map)
                checkWorse(map)
                checkAverage(map)

            }



        }

        resetButton.setOnClickListener{
            Name.getText().clear()
            Email.getText().clear()
            Age.getText().clear()
            findViewById<SeekBar>(R.id.IOS).setProgress(0)
            findViewById<SeekBar>(R.id.Android).setProgress(0)
            findViewById<SeekBar>(R.id.Flutter).setProgress(0)


        }




    }

   fun checkBest(seekbars: Map<String,Int>)
   {
        seekbars.forEach(){ entry ->
           if (findViewById<SeekBar>(entry.value).progress > 80 )
           {
               Toast.makeText(this, "vous etes excellent en ${entry.key} ", Toast.LENGTH_SHORT).show()

           }

        }

   }



    fun checkWorse(seekbars: Map<String,Int>)
    {  var x=0

        seekbars.forEach(){ entry ->
            if (findViewById<SeekBar>(entry.value).progress <30 )
            {
                x++

            }

        }
    if(x==3)    Toast.makeText(this, "Vous devez travailler vos skills ", Toast.LENGTH_SHORT).show()
    }

    fun checkAverage(seekbars: Map<String,Int>)
    {  var x=0

        seekbars.forEach(){ entry ->
            if  (findViewById<SeekBar>(entry.value).progress <80 )
                x++

    }
       if (x==3)

       { var y=0
           seekbars.forEach { entry ->
           if (findViewById<SeekBar>(entry.value).progress <30 )
           {  y++ }

       }
           if (y!=3) Toast.makeText(this, "Vous avez de bon skills ", Toast.LENGTH_SHORT).show()

       }
    }
}


