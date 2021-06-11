package com.example.videodownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.videodownloader.databinding.ActivityYoutubeBinding;

import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class YoutubeActivity extends AppCompatActivity {

    private ActivityYoutubeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_youtube);

        binding.downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String values = binding.getText().toString();
                String newLink = binding.ytUrl.getText().toString();
                YouTubeUriExtractor youTubeUriExtractor = new YouTubeUriExtractor(YoutubeActivity.this) {
                    @Override
                    public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {

                        if(ytFiles != null)
                        {
                            int tag = 22;
                            String newLink = binding.ytUrl.getText().toString();
                            newLink = ytFiles.get(tag).getUrl();
                            Uri uri = Uri.parse(newLink);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setTitle(videoTitle);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, videoTitle+".mp4");
                            ((DownloadManager)YoutubeActivity.this.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
                            request.allowScanningByMediaScanner();
                            Toast.makeText(YoutubeActivity.this, "Downloading Started...", Toast.LENGTH_LONG).show();

                        }
                    }
                };

                youTubeUriExtractor.execute(newLink);
            }
        });
    }
}