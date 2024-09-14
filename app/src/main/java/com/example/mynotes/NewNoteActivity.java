package com.example.mynotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.db.bean.Agenda;
import com.example.mynotes.db.bean.Nota;


public class NewNoteActivity extends AppCompatActivity {

    private EditText etTitulo, etTexto;
    private RadioGroup rgPrioridade;
    private Button btnSalvar;
    private Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etTitulo = findViewById(R.id.etTitulo);
        etTexto = findViewById(R.id.etTexto);
        rgPrioridade = findViewById(R.id.rgPrioridade);
        btnSalvar = findViewById(R.id.btnSalvar);
        agenda = new Agenda(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTitulo.getText().toString();
                String texto = etTexto.getText().toString();
                int prioridade = getSelectedPriority();

                if (!titulo.isEmpty() && prioridade != -1) {
                    Nota nota = new Nota(titulo, texto, prioridade);
                    boolean sucesso = agenda.cadastrarNota(nota);
                    if (sucesso) {
                        Toast.makeText(NewNoteActivity.this, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NewNoteActivity.this, "Erro ao salvar anotação.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NewNoteActivity.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getSelectedPriority() {
        int selectedId = rgPrioridade.getCheckedRadioButtonId();
        if (selectedId == R.id.rbAlta) {
            return 1;
        } else if (selectedId == R.id.rbNormal) {
            return 2;
        } else if (selectedId == R.id.rbBaixa) {
            return 3;
        } else {
            return -1;
        }
    }
}
