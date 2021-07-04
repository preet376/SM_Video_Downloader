package com.example.videodownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.videodownloader.databinding.ActivityInstaBinding;
import com.hcr2bot.instagramvideosdownloader.InstaVideo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstaActivity extends AppCompatActivity {

    private ActivityInstaBinding binding;
    String videoURL, video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insta);

        binding.downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    getVideo();

                InstaVideo.downloadVideo(InstaActivity.this,binding.instaUrl.getText().toString());


            }
        });

    }

//    private void getVideo()
//    {
//        if(TextUtils.isEmpty(binding.instaUrl.getText().toString()))
//        {
//            Toast.makeText(this, "Please Enter the URL",Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            if(binding.instaUrl.getText().toString().contains("instagram")) {
//                String replace;
//
//                if (binding.instaUrl.getText().toString().contains("?utm_source=ig_web_copy_link")) {
//                    replace = "?utm_source=ig_web_copy_link";
//                    video = binding.instaUrl.getText().toString().replace(replace, "");
//                }
//
//                else if  (binding.instaUrl.getText().toString().contains("?utm_medium=copy_link"))
//                {
//                    replace = "?utm_medium=copy_link";
//                    video = binding.instaUrl.getText().toString().replace(replace, "");
//                }
//                else {
//                    video = binding.instaUrl.getText().toString();
//                }
//
//
//                ApiUtilities.getApiInterface().getInfo(video).enqueue(new Callback<InstaModel>() {
//                    @Override
//                    public void onResponse(Call<InstaModel> call, Response<InstaModel> response) {
//                        if(response.body() != null) {
//
//                            videoURL = response.body().getInfo().get(0).getVideoUrl();
//
//                            Log.d("InstaVideo", "onResponse: " + videoURL);
//
//                            Util.download(videoURL,Util.rootDirectoryInsta,InstaActivity.this, "instagram "+System.currentTimeMillis()+".mp4");
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<InstaModel> call, Throwable t) {
//
//                        Log.d("InstaVideo", "onFailure: " + t.getMessage());
//
//                    }
//                });
//
//            }
//            else
//            {
//                Toast.makeText(this,"Invalid URL",Toast.LENGTH_LONG).show();
//            }
//        }
//    }

}