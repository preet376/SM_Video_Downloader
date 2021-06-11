package com.example.videodownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.videodownloader.databinding.ActivityShareChatBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ShareChatActivity extends AppCompatActivity {

    private ActivityShareChatBinding binding;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_chat);

        binding.downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getShareChatData();

            }
        });


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });





        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                super.onAdLoaded();
                Toast.makeText(ShareChatActivity.this,"Ad Loaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.

                super.onAdOpened();

            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.

                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.

                super.onAdClosed();
            }
        });



    }

        private  void getShareChatData()
        {
            URL url = null;
            try {
                url = new URL(binding.shareChatUrl.getText().toString());
                String host = url.getHost();


                if(host.contains("sharechat"))
                {
                    new getsharechatData().execute(binding.shareChatUrl.getText().toString());
                }
                else
                {
                    Toast.makeText(this,"URL is invalid", Toast.LENGTH_LONG).show();
                }

                //url = new URL(Objects.requireNonNull(binding.fbUrl.getText().toString()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        }


    class getsharechatData extends AsyncTask<String, Void, Document>
    {

        Document scDoc;

        @Override
        protected Document doInBackground(String... strings) {

            try {
                scDoc = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return scDoc;
        }

        @Override
        protected void onPostExecute(Document document)
        {
            String videoURL = document.select("meta[property=\"og:video:secure_url\"]").last().attr("content");
            if(!videoURL.equals(""))
            {
                Util.download(videoURL, Util.rootDirectoryFacebook, ShareChatActivity.this,"ShareChat "+ System.currentTimeMillis()+".mp4");
            }
        }
    }



}