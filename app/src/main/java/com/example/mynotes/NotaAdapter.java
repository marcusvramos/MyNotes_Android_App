package com.example.mynotes;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
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
        ImageView ivFotoMiniatura = convertView.findViewById(R.id.ivFotoMiniatura);

        tvTitulo.setText(nota.getTitulo());

        int priorityColor;
        switch (nota.getPrioridade()) {
            case 1:
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
                break;
            default:
                priorityColor = ContextCompat.getColor(getContext(), android.R.color.darker_gray);
                break;
        }
        priorityIndicator.setBackgroundColor(priorityColor);

        if (nota.getPhotoPath() != null && !nota.getPhotoPath().isEmpty()) {
            Bitmap bitmap = BitmapFactory.decodeFile(nota.getPhotoPath());
            ivFotoMiniatura.setImageBitmap(bitmap);
            ivFotoMiniatura.setVisibility(View.VISIBLE);

            ivFotoMiniatura.setOnClickListener(v -> {
                ImagePreviewDialogFragment dialogFragment = ImagePreviewDialogFragment.newInstance(nota.getPhotoPath());
                if (getContext() instanceof FragmentActivity) {
                    FragmentActivity activity = (FragmentActivity) getContext();
                    dialogFragment.show(activity.getSupportFragmentManager(), "image_preview");
                }
            });
        } else {
            ivFotoMiniatura.setVisibility(View.GONE);
        }

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
