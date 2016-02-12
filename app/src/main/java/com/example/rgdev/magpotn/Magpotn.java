package com.example.rgdev.magpotn;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Magpotn extends AppCompatActivity {


    Decompress d;
    SwipeRefreshLayout swiprefreshLayout;
    Button Close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magpotn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.textView).setVisibility(View.INVISIBLE);
        Close=(Button)findViewById(R.id.Close);
        Close.setVisibility(View.INVISIBLE);
        swiprefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swiprefreshLayout.setColorSchemeColors(R.color.colorPrimary);

    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_magpotn, menu);
        return true;
    }*/

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
public void Close(View v) {
    finish();
}


    public void Unzip(View v) {
        findViewById(R.id.textView).setVisibility(View.INVISIBLE);
        swiprefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swiprefreshLayout.setEnabled(true);
                swiprefreshLayout.setRefreshing(true);
            }
        }, 1000);

        new LongOperation().execute("");


    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String zipFile = Environment.getExternalStorageDirectory() + "/MAG/MAGPOTN/app/magpotn.zip";
            String unzipLocation = Environment.getExternalStorageDirectory() + "/MAGPOTN/";

            d = new Decompress(zipFile, unzipLocation);
            d.unzipFile();

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setVisibility(View.VISIBLE);
            tv.setText(d.getStatus());
            swiprefreshLayout.setRefreshing(false);
            swiprefreshLayout.setEnabled(false);
            Close.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }
}
