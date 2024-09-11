package com.example.mynotes.db.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.mynotes.db.bean.Nota;
import com.example.mynotes.db.util.Conexao;

import java.util.ArrayList;

public class NotaDAL {
    private Conexao con;
    private final String TABLE = "nota";

    public NotaDAL(Context context) {
        con = new Conexao(context);
        con.conectar();
    }

    public boolean salvar(Nota o) {
        ContentValues dados = new ContentValues();
        dados.put("nota_titulo", o.getTitulo());
        dados.put("nota_texto", o.getTexto());
        dados.put("nota_prioridade", o.getPrioridade());
        return con.inserir(TABLE, dados) > 0;
    }

    public boolean alterar(Nota o) {
        ContentValues dados = new ContentValues();
        dados.put("nota_id", o.getId());
        dados.put("nota_titulo", o.getTitulo());
        dados.put("nota_texto", o.getTexto());
        dados.put("nota_prioridade", o.getPrioridade());
        return con.alterar(TABLE, dados, "nota_id=" + o.getId()) > 0;
    }

    public boolean apagar(long id) {
        return con.apagar(TABLE, "nota_id=" + id) > 0;
    }

    public Nota get(int id) {
        Nota o = null;
        Cursor cursor = con.consultar("select * from " + TABLE + " where nota_id=" + id);
        if (cursor.moveToFirst()) {
            o = new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        }
        cursor.close();
        return o;
    }

    public ArrayList<Nota> get(String filtro) {
        ArrayList<Nota> objs = new ArrayList<>();
        String sql = "select * from " + TABLE;
        if (!filtro.equals("")) {
            sql += " where " + filtro;
        }

        Cursor cursor = con.consultar(sql);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                objs.add(new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return objs;
    }
}
