package com.example.mynotes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.mynotes.db.bean.Agenda;
import com.example.mynotes.db.bean.Nota;

import java.io.File;
import java.io.IOException;

public class NewNoteActivity extends AppCompatActivity {

    private EditText etTitulo, etTexto;
    private RadioGroup rgPrioridade;
    private ImageView ivFoto;
    private Agenda agenda;
    private String photoPath = null;
    private Uri photoUri;

    private final ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    ivFoto.setVisibility(View.VISIBLE);
                    Bitmap imageBitmap = BitmapFactory.decodeFile(photoPath);
                    ivFoto.setImageBitmap(imageBitmap);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etTitulo = findViewById(R.id.etTitulo);
        etTexto = findViewById(R.id.etTexto);
        rgPrioridade = findViewById(R.id.rgPrioridade);
        ivFoto = findViewById(R.id.ivFoto);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        Button btnTirarFoto = findViewById(R.id.btnTirarFoto);
        agenda = new Agenda(this);

        btnTirarFoto.setOnClickListener((View v) -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = createImageFile();
                if (photoFile != null) {
                    photoUri = FileProvider.getUriForFile(this, "com.example.mynotes.fileprovider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    takePictureLauncher.launch(takePictureIntent);
                }
            }
        });

        btnSalvar.setOnClickListener((View v) -> {
            String titulo = etTitulo.getText().toString();
            String texto = etTexto.getText().toString();
            int prioridade = getSelectedPriority();

            if (!titulo.isEmpty() && prioridade != -1) {
                Nota nota = new Nota(titulo, texto, prioridade, photoPath); // Salvar com a foto
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
        });
    }

    private File createImageFile() {
        File photoFile = null;
        try {
            String fileName = "nota_" + System.currentTimeMillis();
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            photoFile = File.createTempFile(fileName, ".jpg", storageDir);
            photoPath = photoFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photoFile;
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
