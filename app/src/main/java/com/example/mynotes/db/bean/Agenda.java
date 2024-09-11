package com.example.mynotes.db.bean;

import android.content.Context;
import com.example.mynotes.db.bean.Nota;
import com.example.mynotes.db.dal.NotaDAL;

import java.util.ArrayList;

public class Agenda {
    private NotaDAL notaDAL;

    public Agenda(Context context) {
        notaDAL = new NotaDAL(context);
    }

    public boolean cadastrarNota(Nota nota) {
        return notaDAL.salvar(nota);
    }

    public boolean apagarNota(long id) {
        return notaDAL.apagar(id);
    }

    public ArrayList<Nota> listarNotas(String filtro) {
        return notaDAL.get(filtro);
    }
}
