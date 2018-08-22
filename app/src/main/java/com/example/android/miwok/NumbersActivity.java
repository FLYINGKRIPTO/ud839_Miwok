
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                    focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
                    )
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                //resume Playback
                mMediaPlayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

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

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> wordseng = new ArrayList<Word>();
        wordseng.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        wordseng.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        wordseng.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        wordseng.add(new Word("four","oyissa",R.drawable.number_four,R.raw.number_four));
        wordseng.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        wordseng.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        wordseng.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        wordseng.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        wordseng.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        wordseng.add(new Word("ten","na'aache",R.drawable.number_ten,R.raw.number_ten));


        WordAdapter adapter  = new WordAdapter(this,wordseng,R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = wordseng.get(position);
                releaseMediaPlayer();

                //Request Audio Focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //use the music stream
                        AudioManager.STREAM_MUSIC,
                        //Request Permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                   // mAudioManager.registerMediaButtonEventReceiver();

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });}
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
        private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }
}