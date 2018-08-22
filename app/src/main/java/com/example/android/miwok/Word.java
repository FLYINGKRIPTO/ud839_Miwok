package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResource = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;
    private  static  final  int NO_IMAGE_PROVIDED = -1;
    public Word(String defaultTranslation,String miwokTranslation,int audioResourceId )
    {
       this.mDefaultTranslation = defaultTranslation;
       this.mMiwokTranslation = miwokTranslation;
       mAudioResourceId = audioResourceId;
    }
    public Word(String defaultTranslation,String miwokTranslation,int imageResource,int audioResourceId)
    {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResource = imageResource;
        this.mAudioResourceId = audioResourceId;
    }

    public  String getDefaultTranslator()
    {
        return mDefaultTranslation;
    }
    public String getMiwokTranslation()
    {
        return  mMiwokTranslation;
    }
    public int getImageResource()
    {
        return mImageResource;
    }
    public  boolean hasImage()
    {
       return mImageResource != NO_IMAGE_PROVIDED;
    }
    public int getmAudioResourceId()
    {
        return mAudioResourceId;
    }

}
