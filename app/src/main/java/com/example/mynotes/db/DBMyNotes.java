package com.example.mynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMyNotes extends SQLiteOpenHelper {
    private static final int VERSAO = 1;

    public DBMyNotes(Context context) {
        super(context, "mynotes.db", null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela "nota"
        db.execSQL("CREATE TABLE nota (" +
                "nota_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nota_titulo VARCHAR(40), " +
                "nota_texto TEXT, " +
                "nota_prioridade INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Exclui a tabela existente (se houver) e cria uma nova
        db.execSQL("DROP TABLE IF EXISTS nota");
        onCreate(db);
    }
}
