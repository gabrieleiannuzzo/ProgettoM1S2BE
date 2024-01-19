package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public abstract class Pubblicazione {
    private long isbn;
    private String titolo;
    private int annoPubblicazione, numeroPagine;

    private static HashSet<Long> listaIsbn = new HashSet<>();

    public Pubblicazione (long isbn, String titolo, int annoPubblicazione, int numeroPagine){
        Random random = new Random();
        if (isbn == 0) {
            while (true) {
                if (listaIsbn.add(random.nextLong())) break;
            }
        } else {
            this.isbn = isbn;
        }
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public static HashSet<Long> getListaIsbn() {
        return listaIsbn;
    }

    public static void setListaIsbn(HashSet<Long> listaIsbn) {
        Pubblicazione.listaIsbn = listaIsbn;
    }

    // Non inserisco il metodo toString perchè lo inserirò nelle sottoclassi
}
