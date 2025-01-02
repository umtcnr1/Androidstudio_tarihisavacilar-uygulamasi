package com.umut.umutcinarmbldv;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (internetVarMi()) {
            Toast.makeText(this, "İnternet Bağlantısı Var", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "İnternet Bağlantısı Yok!", Toast.LENGTH_LONG).show();
        }

        ImageView logo = findViewById(R.id.loadingImage);
        Animation donus = AnimationUtils.loadAnimation(this, R.anim.rotate);
        logo.startAnimation(donus);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    private boolean internetVarMi() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}