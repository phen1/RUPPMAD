package kh.edu.rupp.fe.ruppmad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import kh.edu.rupp.fe.ruppmad.db.DbManager;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
    }

    public void onLoginHistoryClick(View view){
        DbManager dbManager = new DbManager(this);
        long[] histories = dbManager.getAllLoginHistory();
        for(long history:histories){
            Log.d("rupp", "History: " + formatDate(history));
        }
    }

    private String formatDate(long timeStamp){
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        return simpleDateFormat.format(date);
    }

}
