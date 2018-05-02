package com.mengpeng.mdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnClickListener {
            MDialog.showLoadingDialog(this, false)
            Thread(Runnable {
                Thread.sleep(5000)
                runOnUiThread(Runnable {
                    MDialog.dismissLoadingDialog()
                })
            }).start()
        }
    }
}
