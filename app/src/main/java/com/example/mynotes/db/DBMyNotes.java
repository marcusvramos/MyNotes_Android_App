package com.example.mynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMyNotes extends SQLiteOpenHelper {
    private static final int VERSAO = 2;

    public DBMyNotes(Context context) {
        super(context, "mynotes.db", null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE nota (" +
                "nota_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nota_titulo VARCHAR(40), " +
                "nota_texto TEXT, " +
                "nota_prioridade INTEGER, " +
                "nota_photo_path TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE nota ADD COLUMN nota_photo_path TEXT;");
        }
    }
}
