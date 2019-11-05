package com.canlioya.textspanexercise

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Sample long string to span
        val sampleText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\nHere is a bullet point: \nthis is a bullet point."
        val spannable = SpannableString(sampleText)

        //Set background color for a piece of text
        spannable.setSpan(BackgroundColorSpan(Color.YELLOW), 22, 32, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        //Set bold a piece of text
        spannable.setSpan(StyleSpan(BOLD), 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE )

        //Set different typeface for a piece of text
        spannable.setSpan(TypefaceSpan("monospace"), 0, 10, Spannable.SPAN_INCLUSIVE_INCLUSIVE )
        //If we want to set a custom font, we need a custom typeface span
        val customType = Typeface.createFromAsset(assets, "fa-solid-900.ttf")
        spannable.setSpan(CustomTypeFaceSpan(customType), 524, 540, Spannable.SPAN_INCLUSIVE_INCLUSIVE )

        //Set bullet points to a part of the text
        val bulletColor = ContextCompat.getColor(this, android.R.color.holo_green_light)
        spannable.setSpan(BulletSpan(40, bulletColor), 606, 621, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        //Insert an image into text with ImageSpan
        spannable.setSpan(ImageSpan(this, R.drawable.ic_plus, DynamicDrawableSpan.ALIGN_BASELINE), 11,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannable.setSpan(CustomClickableSpan(), 240, 244, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannable
    }
}
