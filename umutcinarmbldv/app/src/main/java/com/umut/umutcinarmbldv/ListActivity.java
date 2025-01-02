package com.umut.umutcinarmbldv;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String[] isimler = {"YENİÇERİ", "VİKİNG", "SAMURAY"};
        int[] resimler = {R.drawable.yeniceri, R.drawable.viking, R.drawable.samuray};

        ArrayList<HashMap<String, Object>> liste = new ArrayList<>();
        for (int i = 0; i < isimler.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("isim", isimler[i]);
            map.put("resim", resimler[i]);
            liste.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                liste,
                R.layout.list_item_warrior,
                new String[]{"isim", "resim"},
                new int[]{R.id.warriorName, R.id.warriorImage}
        );
        ListView listView = findViewById(R.id.warriorList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetayActivity.class);
            intent.putExtra("warrior_name", isimler[position]);
            startActivity(intent);
        });
    }
}