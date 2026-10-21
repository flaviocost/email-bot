package org.example;

import java.util.Locale;

public class Email {
    private String remetente;
    private String destinatario;
    private String assunto;
    private String corpo;
    private int numero;

    public Email(int numero, String remetente, String destinatario, String assunto, String corpo){
        this.numero = numero;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.assunto= assunto;
    }

    public Email(String remetente, String destinatario, String assunto, String corpo){
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.assunto = assunto;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public int getNumero(){return numero; }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }


    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public boolean contemPalavraChave(String palavra){
        return this.assunto.toLowerCase(Locale.ROOT).contains(palavra.toLowerCase(Locale.ROOT));

    }
}

