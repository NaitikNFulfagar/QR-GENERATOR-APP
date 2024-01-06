package com.naitik.qrgenerator

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var myqr   : ImageView
    private lateinit var mytext : EditText
    private lateinit var mybtn  : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myqr = findViewById(R.id.qr)
        mytext = findViewById(R.id.mydata)
        mybtn = findViewById(R.id.genqrbtn)

        mybtn.setOnClickListener {
            val data = mytext.text.toString().trim()

            if(data.isEmpty()){
                Toast.makeText(this,"Enter Some Data",Toast.LENGTH_SHORT).show()
            }
            else{
                val writer = QRCodeWriter()
                try {

                    val bitMatrix = writer.encode(data , BarcodeFormat.QR_CODE,512,512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for (x in 0 until width){
                        for(y in 0 until height){
                            bmp.setPixel(x,y,if (bitMatrix[x,y]) Color.BLACK else Color.WHITE )
                        }
                    }
                    myqr.setImageBitmap(bmp)
                }catch (e:WriterException){
                    e.printStackTrace()

                }
            }
        }


    }
}