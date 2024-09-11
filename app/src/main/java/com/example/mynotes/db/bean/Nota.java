package com.example.mynotes.db.bean;

public class Nota {
    private int id;
    private String titulo;
    private String texto;
    private int prioridade; // 1 - Alta (vermelho), 2 - Normal (azul), 3 - Baixa (amarelo)

    public Nota() {
        this(0, "", "", 2);
    }

    public Nota(String titulo, String texto, int prioridade) {
        this(0, titulo, texto, prioridade);
    }

    public Nota(int id, String titulo, String texto, int prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.prioridade = prioridade;
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
}
