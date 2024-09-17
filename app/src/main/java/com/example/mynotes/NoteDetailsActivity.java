package com.example.mynotes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.mynotes.db.bean.Agenda;
import com.example.mynotes.db.bean.Nota;

public class NoteDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        TextView tvNoteTitle = findViewById(R.id.tvNoteTitle);
        TextView tvNoteText = findViewById(R.id.tvNoteText);
        TextView tvNotePriority = findViewById(R.id.tvNotePriority);
        ImageView ivNoteIcon = findViewById(R.id.ivNoteIcon);
        ImageView ivNoteImage = findViewById(R.id.ivNoteImage); // Novo ImageView para a imagem
        Agenda agenda = new Agenda(this);

        int notaId = getIntent().getIntExtra("nota_id", -1);
        if (notaId != -1) {
            Nota nota = agenda.listarNotas("nota_id=" + notaId).get(0);

            tvNoteTitle.setText(nota.getTitulo());
            tvNoteText.setText(nota.getTexto());

            String prioridadeTexto;
            int color;
            switch (nota.getPrioridade()) {
                case 1:
                    prioridadeTexto = "Alta Prioridade";
                    color = ContextCompat.getColor(this, android.R.color.holo_red_dark);
                    break;
                case 2:
                    prioridadeTexto = "Prioridade Normal";
                    color = ContextCompat.getColor(this, android.R.color.holo_orange_dark);
                    break;
                case 3:
                    prioridadeTexto = "Baixa Prioridade";
                    color = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
                    break;
                default:
                    prioridadeTexto = "Prioridade Desconhecida";
                    color = ContextCompat.getColor(this, android.R.color.darker_gray);
                    break;
            }
            tvNotePriority.setTextColor(color);
            ivNoteIcon.setColorFilter(color);
            tvNotePriority.setText(prioridadeTexto);

            if (nota.getPhotoPath() != null && !nota.getPhotoPath().isEmpty()) {
                Bitmap bitmap = BitmapFactory.decodeFile(nota.getPhotoPath());
                if (bitmap != null) {
                    ivNoteImage.setImageBitmap(bitmap);
                    ivNoteImage.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
