package com.example.mynotes;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mynotes.db.bean.Nota;

import java.util.ArrayList;
import java.util.Comparator;

public class NotaAdapter extends ArrayAdapter<Nota> {
    private final ArrayList<Nota> notas;

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
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
                break;
            case 2: // Prioridade normal
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
                break;
            case 3: // Baixa prioridade
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
                break;
            default:
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.darker_gray);
                break;
        }
        priorityIndicator.setBackgroundColor(priorityColor);

        return convertView;
    }

    public ArrayList<Nota> getItems() {
        return notas;
    }

    public void sortByPriority() {
        notas.sort(Comparator.comparingInt(Nota::getPrioridade));
        notifyDataSetChanged();
    }

    public void sortByTitle() {
        notas.sort((o1, o2) -> o1.getTitulo().compareToIgnoreCase(o2.getTitulo()));
        notifyDataSetChanged();
    }
}
