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
        View priorityIndicator = convertView.findViewById(R.id.priorityIndicator);

        tvTitulo.setText(nota.getTitulo());

        int priorityColor;
        switch (nota.getPrioridade()) {
            case 1: // Alta prioridade
                priorityColor = getContext().getResources().getColor(android.R.color.holo_red_light);
                break;
            case 2: // Prioridade normal
                priorityColor = getContext().getResources().getColor(android.R.color.holo_orange_light);
                break;
            case 3: // Baixa prioridade
                priorityColor = getContext().getResources().getColor(android.R.color.holo_blue_light);
                break;
            default:
                priorityColor = getContext().getResources().getColor(android.R.color.darker_gray);
                break;
        }
        priorityIndicator.setBackgroundColor(priorityColor);

        return convertView;
    }


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
