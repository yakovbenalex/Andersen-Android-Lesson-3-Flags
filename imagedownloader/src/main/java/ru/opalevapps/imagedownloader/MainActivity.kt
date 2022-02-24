package ru.opalevapps.imagedownloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var editTextImageUri: EditText
    lateinit var imageViewPicture: ImageView

    lateinit var imageUri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextImageUri = findViewById(R.id.editTextImageUri)
        imageViewPicture = findViewById(R.id.imageViewPicture)
    }

    // load image from url by Picasso library
    fun loadPicturePicasso(view: View) {
        imageUri = editTextImageUri.text.toString()

        Picasso.Builder(this)
            .listener { picasso, uri, exception ->
                Log.e("DownloadImageTaskError", exception.toString())
                showErrorImageLoading(exception.toString())
            }
            .build()
            .load(imageUri)
            .into(imageViewPicture)
    }

    // load image from url by Android internal tools
    fun loadPictureAndroid(view: View) {
        DownloadImageTask(imageViewPicture, editTextImageUri).execute()
    }

    // download image from url without 3d-party libraries
    class DownloadImageTask(var image: ImageView, var editText: EditText) : AsyncTask<String?, Void?, Bitmap?>() {
        override fun doInBackground(vararg p0: String?): Bitmap? {
            val imageUri = editText.text.toString()
            var bmp: Bitmap? = null
            try {
                val inp: InputStream = URL(imageUri).openStream()
                bmp = BitmapFactory.decodeStream(inp)
            } catch (e: Exception) {
                Log.e("DownloadImageTaskError", e.toString())
                e.printStackTrace()
            }
            return bmp
        }

        override fun onPostExecute(result: Bitmap?) {
            image.setImageBitmap(result)
        }
    }

    // show error image loading message
    fun showErrorImageLoading(error: String){
        Toast.makeText(
            this,
            "Image loading error \n${error}",
            Toast.LENGTH_LONG)
            .show()
    }
}