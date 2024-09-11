package com.example.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mynotes.db.bean.Nota;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NotaAdapter extends ArrayAdapter<Nota> {
    private ArrayList<Nota> notas;

    public NotaAdapter(Context context, ArrayList<Nota> notas) {
        super(context, 0, notas);
        this.notas = notas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Nota nota = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nota, parent, false);
        }

        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);

        // Ajustar o título
        tvTitulo.setText(nota.getTitulo());

        switch (nota.getPrioridade()) {
            case 1: // Alta prioridade
                tvTitulo.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
                break;
            case 2: // Prioridade normal
                tvTitulo.setTextColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
                break;
            case 3: // Baixa prioridade
                tvTitulo.setTextColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
                break;
            default:
                tvTitulo.setTextColor(getContext().getResources().getColor(android.R.color.darker_gray));
                break;
        }

        return convertView;
    }

    // Ordenar diretamente a lista interna e notificar a mudança
    public void sortByPriority() {
        Collections.sort(notas, new Comparator<Nota>() {
            @Override
            public int compare(Nota o1, Nota o2) {
                return Integer.compare(o1.getPrioridade(), o2.getPrioridade());
            }
        });
        notifyDataSetChanged();
    }

    public void sortByTitle() {
        Collections.sort(notas, new Comparator<Nota>() {
            @Override
            public int compare(Nota o1, Nota o2) {
                return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
            }
        });
        notifyDataSetChanged();
    }
}
