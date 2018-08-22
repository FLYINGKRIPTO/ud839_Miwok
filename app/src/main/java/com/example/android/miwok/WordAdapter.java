package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;
    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    public WordAdapter(Activity context, ArrayList<Word> wordseng,int colorResourceId) {
        super(context, 0, wordseng);
        mColorResourceId = colorResourceId;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

    }
        Word currentWord = getItem(position);
        TextView engTextView = listItemView.findViewById(R.id.list_item_1);
        engTextView.setText(currentWord.getDefaultTranslator());
        TextView miwokTextVIew = listItemView.findViewById(R.id.list_item_2);
        miwokTextVIew.setText(currentWord.getMiwokTranslation());
        ImageView image = listItemView.findViewById(R.id.image_item);
      if(currentWord.hasImage()) {
          image.setImageResource(currentWord.getImageResource());
          image.setVisibility(View.VISIBLE);
      }
      else
      {
          image.setVisibility(View.GONE);
      }
      //Set the theme color for the list item
      View textContainer = listItemView.findViewById(R.id.text_container);
      int color = ContextCompat.getColor(getContext(), mColorResourceId);
      textContainer.setBackgroundColor(color);
      return listItemView;
    }
}

