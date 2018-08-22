/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> wordseng = new ArrayList<Word>();
        wordseng.add(new Word("Father", "әpә", R.drawable.family_father, R.raw.family_father));
        wordseng.add(new Word("Mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        wordseng.add(new Word("Son ", "angsi", R.drawable.family_son, R.raw.family_son));
        wordseng.add(new Word("Daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        wordseng.add(new Word("Older Brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_sister));
        wordseng.add(new Word("Younger Brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        wordseng.add(new Word("older Sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        wordseng.add(new Word("Younger Sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        wordseng.add(new Word("Grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        wordseng.add(new Word("GrandFather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter adapter = new WordAdapter(this, wordseng, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = wordseng.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
    }

