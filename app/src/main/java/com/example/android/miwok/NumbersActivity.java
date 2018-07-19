
package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> wordseng = new ArrayList<String>();
        wordseng.add("one");
        wordseng.add("two");
        wordseng.add("three");
        wordseng.add("four");
        wordseng.add("five");
        wordseng.add("six");
        wordseng.add("seven");
        wordseng.add("eight");
        wordseng.add("nine");
        wordseng.add("ten");

         //Now we need to add these to textViews
       //first we need to find the Layout in which we have to add the child views
        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);
        //Now we need to create and add textViews to the linear layout just created
        TextView wordView = new TextView(this);
        wordView.setText(wordseng.get(0));
        //Now I am going to add wordView as the child to the parent view rootview
        rootView.addView(wordView);
    }
}
