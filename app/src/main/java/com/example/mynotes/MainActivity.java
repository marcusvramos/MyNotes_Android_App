package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.db.bean.Agenda;
import com.example.mynotes.db.bean.Nota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNotes;
    private NotaAdapter adapter;
    private Agenda agenda;
    private TextView tvEmptyList; // TextView para mostrar a lista vazia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        tvEmptyList = findViewById(R.id.tvEmptyList); // Inicializando o TextView
        agenda = new Agenda(this);

        ArrayList<Nota> notas = agenda.listarNotas("");
        adapter = new NotaAdapter(this, notas);
        listViewNotes.setAdapter(adapter);

        // Verificar se a lista está vazia
        checkIfListIsEmpty(notas);

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nota nota = (Nota) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, NoteDetailsActivity.class);
                intent.putExtra("nota_id", nota.getId());
                startActivity(intent);
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Nota nota = (Nota) parent.getItemAtPosition(position);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmar Exclusão")
                        .setMessage("Você tem certeza que deseja apagar esta anotação?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (agenda.apagarNota(nota.getId())) {
                                    adapter.remove(nota);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Anotação apagada!", Toast.LENGTH_SHORT).show();
                                    checkIfListIsEmpty(adapter.getItems());
                                } else {
                                    Toast.makeText(MainActivity.this, "Erro ao apagar anotação.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_new_note) {
            startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
        } else if (id == R.id.menu_sort_priority) {
            adapter.sortByPriority();
        } else if (id == R.id.menu_sort_alpha) {
            adapter.sortByTitle();
        } else if (id == R.id.menu_close) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToNewNote(View view) {
        startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<Nota> notas = agenda.listarNotas("");
        adapter.clear();
        adapter.addAll(notas);
        adapter.notifyDataSetChanged();

        checkIfListIsEmpty(notas);
    }

    private void checkIfListIsEmpty(ArrayList<Nota> notas) {
        if (notas.isEmpty()) {
            tvEmptyList.setVisibility(View.VISIBLE);
            listViewNotes.setVisibility(View.GONE);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            listViewNotes.setVisibility(View.VISIBLE);
        }
    }
}
