package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadBackground extends AsyncTask<String, Void, Drawable> {


    private String imageUrl , imageName;
    private ConstraintLayout constraintLayout;

    public LoadBackground(String url, String file_name, ConstraintLayout constraintLayout) {
        this.imageUrl = url;
        this.imageName = file_name;
        this.constraintLayout= constraintLayout;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Drawable doInBackground(String... urls) {

        try {
            InputStream is = (InputStream) this.fetch(this.imageUrl);
            Drawable d = Drawable.createFromStream(is, this.imageName);

            return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Object fetch(String address) throws MalformedURLException,IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }

    @Override
    protected void onPostExecute(Drawable result) {
        super.onPostExecute(result);
        constraintLayout.setBackground(result);


    }
}