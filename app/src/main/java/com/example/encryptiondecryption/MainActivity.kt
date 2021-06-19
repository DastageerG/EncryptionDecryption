package com.example.encryptiondecryption

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encryptiondecryption.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity()
{
    var  _binding:ActivityMainBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    } // onCreate

    /**
     * Name - Ghulam Dastageer
     * Roll No - 2K18/IT/44
     */

    fun onClickEncrypt(view: View)
    {
        var text:String? = binding.editTextEncrypt.text.toString()
            .trim().toLowerCase(Locale.ROOT) // take input from text field and lower case it
        var key:String? = binding.editTextShiftKeyEncryption.text.toString() // take shift key from editText field
        val shiftKey = keyInRange(key!!) // make sure the key is in range and not null
        var plainText = ""
        for(c in text!!.toCharArray())
        {
            if(c.toInt() in 97..122 || c.toInt() == 32) //  97 to 122 is range of a to z and 32 is ascii code of  space " "
            {
                plainText+=c // make sure the plain text contains value within a to z
            }
        } // for closed
        binding.textViewCipherText.text = "Cipher text : "+encrypt(plainText,shiftKey) // show the result on text field
    } // onClick Encrypt closed closed



    // this method return the result as StringBuffer
    private fun encrypt(plainText: String, shiftKey: Int): StringBuffer
    {
        val result = StringBuffer()
        for (element in plainText)
        {
            if(element.isLowerCase())
            {
                val c = ((element.toInt() + shiftKey- 97) % 26 + 97).toChar() // generate substitute
                result.append(c)
            }
            else
            {
                result.append(element)
            }
             // append is used for concat
        } // for closed
        return result
    } // encrypt closed




    // this method return the result as StringBuffer
    fun onClickDecrypt(view: View)
    {
        var text:String? = binding.editTextDecrypt.text.toString()
            .trim().toLowerCase(Locale.ROOT)  // take input from text field and lower case it
        var key:String? = binding.editTextShiftKeyDecryption.text.toString() // take shift key from editText field
        val shiftKey = keyInRange(key!!) // make sure the key is in range and not null
        var plainText = ""
        for(c in text!!.toCharArray())
        {
            if(c.toInt() in 97..122 || c.toInt() == 32 )
            {
                plainText+=c // make sure the plain text contains value within a to z
            }
        }
        binding.textViewPlainText.text = "Plain text : "+decrypt(plainText,shiftKey) // show the result on text field
    } // onClick Decrypt closed closed


    private fun decrypt(plainText: String, shiftKey: Int): StringBuffer
    {
        val result = StringBuffer()
        for (element in plainText)
        {
            if(element.isLowerCase())
            {
                var c = ((element.toInt() - shiftKey - 97) % 26 + 97).toChar() // generate substitute
                if(c < 'a')
                {
                    c =  element - shiftKey- 97 + 122 +1
                }
                result.append(c)
            }
            else
            {
                result.append(element)
            }
        } // for closed
        return result
    } // decrypt closed



    private fun keyInRange(key: String?): Int
    {
        try{
            val shiftKey = key?.toInt()
            if(shiftKey!! >=0 && shiftKey <=25)
            {
                return shiftKey
            }
            else
            {
                Toast.makeText(this,"Enter between 0 to 25",Toast.LENGTH_SHORT).show()
                binding.textViewCipherText.text = ""
                binding.textViewPlainText.text = ""
            }
        }catch (e:Exception)
        {
            Log.d("exception", "keyInRange: "+e.message)
            Toast.makeText(this,"Using default shift key '3' for operation",Toast.LENGTH_SHORT).show()
        }
        return 4   // default shift key is 4
    } // keyInRange closed


} // MainActivity closed