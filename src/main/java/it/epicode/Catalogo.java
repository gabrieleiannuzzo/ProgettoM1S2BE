package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Catalogo {
    private HashMap<Long, Pubblicazione> catalogo = new HashMap();

    public void aggiungiPubblicazione(Pubblicazione pubblicazione) throws LibroGiaInseritoException {
        if (catalogo.get(pubblicazione.getIsbn()) != null) throw new LibroGiaInseritoException("Il libro \"" + pubblicazione.getTitolo() + "\" è già presente nel catalogo");
        catalogo.put(pubblicazione.getIsbn(), pubblicazione);
    }

    public Pubblicazione rimuoviPubblicazioneDaIsbn(long isbn){
        return(catalogo.remove(isbn));
    }

    public Pubblicazione ricercaDaIsbn(long isbn){
        return(catalogo.get(isbn));
    }

    public ArrayList<Pubblicazione> ricercaDaTitolo(String titolo){
        ArrayList<Pubblicazione> risultato = new ArrayList<>();

        for (Map.Entry<Long, Pubblicazione> entry:catalogo.entrySet()) {
            Pubblicazione pubblicazione = entry.getValue();
            if (pubblicazione.getTitolo().equals(titolo)) risultato.add(pubblicazione);
        }

        return risultato;
    };

    public ArrayList<Libro> ricercaDaAutore(String autore){
        ArrayList<Libro> risultato = new ArrayList<>();

        for (Map.Entry<Long, Pubblicazione> entry:catalogo.entrySet()) {
            Pubblicazione pubblicazione = entry.getValue();
            if (pubblicazione instanceof Rivista) continue;
            pubblicazione = (Libro) pubblicazione;
            if (((Libro) pubblicazione).getAutore().equals(autore)) risultato.add((Libro) pubblicazione);
        }

        return risultato;
    };

    public void salvaSuDisco(){};

    public void caricaDaDisco(){};

    public HashMap<Long, Pubblicazione> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(HashMap<Long, Pubblicazione> catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "catalogo=" + catalogo +
                '}';
    }
}
