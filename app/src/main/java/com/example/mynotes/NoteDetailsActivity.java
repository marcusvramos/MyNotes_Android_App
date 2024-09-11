package com.example.mynotes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.db.bean.Agenda;
import com.example.mynotes.db.bean.Nota;

public class NoteDetailsActivity extends AppCompatActivity {

    private TextView tvNoteTitle, tvNoteText, tvNotePriority;
    private Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        tvNoteTitle = findViewById(R.id.tvNoteTitle);
        tvNoteText = findViewById(R.id.tvNoteText);
        tvNotePriority = findViewById(R.id.tvNotePriority);
        agenda = new Agenda(this);

        int notaId = getIntent().getIntExtra("nota_id", -1);
        if (notaId != -1) {
            Nota nota = agenda.listarNotas("nota_id=" + notaId).get(0);

            tvNoteTitle.setText(nota.getTitulo());
            tvNoteText.setText(nota.getTexto());

            String prioridadeTexto;
            switch (nota.getPrioridade()) {
                case 1:
                    prioridadeTexto = "Alta Prioridade";
                    tvNotePriority.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    break;
                case 2:
                    prioridadeTexto = "Prioridade Normal";
                    tvNotePriority.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                    break;
                case 3:
                    prioridadeTexto = "Baixa Prioridade";
                    tvNotePriority.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                    break;
                default:
                    prioridadeTexto = "Prioridade Desconhecida";
                    tvNotePriority.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    break;
            }
            tvNotePriority.setText(prioridadeTexto);
        }
    }
}
