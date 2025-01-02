package com.umut.umutcinarmbldv;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class DetayActivity extends AppCompatActivity {
    private YouTubePlayerView videoPlayer;
    private SavasciVeritabani veritabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        videoPlayer = findViewById(R.id.youtubePlayerView);
        TextView baslik = findViewById(R.id.titleText);
        TextView aciklama = findViewById(R.id.descriptionText);

        getLifecycle().addObserver(videoPlayer);

        veritabani = new SavasciVeritabani(this);
        String savasciIsmi = getIntent().getStringExtra("warrior_name");
        baslik.setText(savasciIsmi + " SAVAŞÇISI");
        Cursor veriSorgu = veritabani.savasciGetir(savasciIsmi);
        if (veriSorgu != null && veriSorgu.moveToFirst()) {
            try {
                String videoId = veriSorgu.getString(veriSorgu.getColumnIndexOrThrow("video_id"));
                String aciklamaMetni = veriSorgu.getString(veriSorgu.getColumnIndexOrThrow("aciklama"));

                aciklama.setText(aciklamaMetni);

                videoPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, "Veri yükleme hatası!", Toast.LENGTH_SHORT).show();
            }
            veriSorgu.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoPlayer != null) {
            videoPlayer.release();
        }
    }
} 