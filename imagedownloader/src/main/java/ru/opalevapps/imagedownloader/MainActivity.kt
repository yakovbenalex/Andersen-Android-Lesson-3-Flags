package ru.opalevapps.imagedownloader

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    lateinit var editTextImageUri: EditText
    lateinit var imageViewPicture: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextImageUri = findViewById(R.id.editTextImageUri)
        imageViewPicture = findViewById(R.id.imageViewPicture)
    }

    // load image from url by Picasso library
    fun loadPicturePicasso(view: View) {
        val imageUri = editTextImageUri.text.toString()

        Picasso.Builder(this)
            .listener { picasso, uri, exception ->
                Toast.makeText(this, "Image loading error \n${exception.toString()}", Toast.LENGTH_LONG)
                    .show()
            }
            .build()
            .load(imageUri)
            .into(imageViewPicture)
    }

    fun loadPictureAndroid(view: View) {
        Toast.makeText(this, "TODO: load image by android sdk", Toast.LENGTH_LONG).show()
        // TODO: load image by android sdk
    }
}