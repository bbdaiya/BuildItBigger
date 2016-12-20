package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.bbdaiya.myapplication.backend.myApi.MyApi;
import com.example.jokedisplay.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by bbdaiya on 18-Dec-16.
 */
public class EndPointsAsyncTask extends AsyncTask<Context, Void, String> {
    static MyApi myApiService = null;
    Context context;
    @Override
    protected String doInBackground(Context... params) {
        if(myApiService==null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbiggergcm-152910.appspot.com/_ah/api/");
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://192.168.0.103:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            myApiService = builder.build();
        }
        context = params[0];
        try{
            return myApiService.tellJoke().execute().getData();
        }catch (IOException e){
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String s) {

        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, s);
        context.startActivity(intent);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
