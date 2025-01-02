package com.umut.umutcinarmbldv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SavasciVeritabani extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SavascilarDB";
    private static final int DATABASE_VERSION = 2;

    public SavasciVeritabani(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE savascilar (" +
                   "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "isim TEXT, " +
                   "video_id TEXT, " +
                   "aciklama TEXT)");

        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS savascilar");
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        String[][] veriler = {
            {"YENİÇERİ", "-QHwNvLVzBU", 
             "Yeniçeri Ocağı, Osmanlı İmparatorluğu'nun merkez ordusu ve ilk düzenli ordusuydu.\n\n" +
             "Kuruluş: 1362\nKaldırılış: 1826\n\n" +
             "Yeniçeriler, Osmanlı ordusunun en seçkin birlikleriydi. Devşirme sistemiyle toplanan Hristiyan çocukların İslam ve Türk kültürüyle eğitilmesiyle oluşturulmuştu. Sıkı bir eğitim ve disiplin altında yetiştirilen Yeniçeriler, padişahın özel muhafız birliği olarak da görev yapardı.\n\n" +
             "Önemli Özellikleri:\n" +
             "• Düzenli maaş alan ilk ordu\n" +
             "• Ateşli silahları kullanan ilk Osmanlı birliği\n" +
             "• Savaş zamanı padişahın çadırını korumakla görevli\n" +
             "• Barış zamanında şehir güvenliğini sağlama görevi"},
            
            {"VİKİNG", "jJY3mmnQlGg", 
             "Vikingler, 8. yüzyılın sonlarından 11. yüzyıla kadar Kuzey Avrupa'da yaşamış savaşçı topluluklardır.\n\n" +
             "Dönem: MS 793-1066\nKöken: İskandinavya\n\n" +
             "Viking savaşçıları, üstün denizcilik becerileri ve cesur savaş taktikleriyle ünlüydü. Uzun gemileriyle Avrupa kıyılarına düzenledikleri akınlarla tarihte derin izler bıraktılar.\n\n" +
             "Savaş Özellikleri:\n" +
             "• Üstün gemi yapım teknolojisi\n" +
             "• Balta ve kılıç kullanımında uzmanlaşma\n" +
             "• Kalkan duvarı taktiği\n" +
             "• Berserker adı verilen özel savaşçı sınıfı"},
            
            {"SAMURAY", "SKTeZML9B50", 
             "Samuraylar, Japonya'nın feodal döneminde var olan soylu savaşçı sınıfıdır.\n\n" +
             "Dönem: 1185-1868\nKöken: Japonya\n\n" +
             "Samuraylar, Bushido (Savaşçı Yolu) adı verilen katı bir ahlak ve onur koduna göre yaşarlardı. Sadakat, cesaret, dürüstlük ve onur en önemli değerleriydi.\n\n" +
             "Savaş Sanatları:\n" +
             "• Katana (Samuray kılıcı) kullanımı\n" +
             "• Yay ve ok (Kyudo) sanatı\n" +
             "• Zırh (Yoroi) kullanımı\n" +
             "• Atlı savaş teknikleri"}
        };

        for (String[] veri : veriler) {
            ContentValues satir = new ContentValues();
            satir.put("isim", veri[0]);
            satir.put("video_id", veri[1]);
            satir.put("aciklama", veri[2]);
            db.insert("savascilar", null, satir);
        }
    }
    public Cursor savasciGetir(String isim) {
        return getReadableDatabase().query("savascilar", null, 
            "isim=?", new String[]{isim}, null, null, null);
    }
} 