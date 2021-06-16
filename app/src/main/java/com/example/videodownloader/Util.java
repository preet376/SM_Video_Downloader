package com.example.videodownloader;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class Util
{
        public static String rootDirectoryFacebook = "/My Story Saver/facebook/";

        public static String shareChatDirectoryFacebook = "/My Story Saver/sharechat/";

    public static String rootDirectoryInsta = "/My Story Saver/instagram/";


    public static File RootDirectory = new File(Environment.getExternalStorageDirectory()
                +"/Download/MyStorySaver/facebook");

        public static void createFolder()
        {
            if(!RootDirectory.exists())
            {
                RootDirectory.mkdirs();
            }
        }

        public static void download(String downloadPath, String destinationPath, Context context, String fileName)
        {
            Toast.makeText(context, "Downloading Started...", Toast.LENGTH_LONG).show();

            Uri uri = Uri.parse(downloadPath);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle(fileName);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationPath+fileName);
            ((DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
        }
}
