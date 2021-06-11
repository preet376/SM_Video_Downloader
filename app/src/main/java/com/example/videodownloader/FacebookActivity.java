package com.example.videodownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.videodownloader.databinding.ActivityFacebookBinding;
import com.example.videodownloader.databinding.ActivityFacebookBindingImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class FacebookActivity extends AppCompatActivity {


    private ActivityFacebookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_facebook);

        binding.downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFacebookData();

            }
        });

    }

    private  void getFacebookData()
    {
        URL url = null;
        try {
            url = new URL(binding.fbUrl.getText().toString());
            String host = url.getHost();


            if(host.contains("facebook.com"))
            {
                new getFbData().execute(binding.fbUrl.getText().toString());
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

    class getFbData extends AsyncTask<String, Void, Document>
    {

        Document fbDoc;

        @Override
        protected Document doInBackground(String... strings) {

            try {
                fbDoc = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return fbDoc;
        }

        @Override
        protected void onPostExecute(Document document)
        {
            String videoURL = document.select("meta[property=\"og:video\"]").last().attr("content");
            if(!videoURL.equals(""))
            {
                Util.download(videoURL, Util.rootDirectoryFacebook, FacebookActivity.this,"facebook "+ System.currentTimeMillis()+".mp4");
            }
        }
    }
}