package com.example.mynotes.db.bean;

public class Nota {
    private int id;
    private String titulo;
    private String texto;
    private int prioridade; // 1 - Alta (vermelho), 2 - Normal (azul), 3 - Baixa (amarelo)
    private String photoPath; // Caminho da foto associada à nota

    public Nota() {
        this(0, "", "", 2, null);
    }

    public Nota(String titulo, String texto, int prioridade) {
        this(0, titulo, texto, prioridade, null);
    }

    public Nota(String titulo, String texto, int prioridade, String photoPath) {
        this(0, titulo, texto, prioridade, photoPath);
    }

    public Nota(int id, String titulo, String texto, int prioridade, String photoPath) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.prioridade = prioridade;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
