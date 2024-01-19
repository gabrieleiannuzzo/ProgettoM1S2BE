package it.epicode;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Catalogo {
    private HashMap<Long, Pubblicazione> catalogo = new HashMap();
    private Logger logger = LoggerFactory.getLogger("Progetto");

    public void aggiungiPubblicazione(Pubblicazione pubblicazione) throws LibroGiaInseritoException, RivistaGiaInseritaException {
        if (catalogo.get(pubblicazione.getIsbn()) != null) {
            if (pubblicazione instanceof Rivista) throw new RivistaGiaInseritaException("La rivista \"" + pubblicazione.getTitolo() + "\" è già presente nel catalogo");
            throw new LibroGiaInseritoException("Il libro \"" + pubblicazione.getTitolo() + "\" è già presente nel catalogo");
        }
        catalogo.put(pubblicazione.getIsbn(), pubblicazione);
    }

    public Pubblicazione rimuoviPubblicazioneDaIsbn(long isbn)throws PubblicazioneInesistenteException{
        Pubblicazione pubblicazione = catalogo.remove(isbn);

        if (pubblicazione == null) throw new PubblicazioneInesistenteException("ISBN non presente nel catalogo");

        return pubblicazione;
    }

    public Pubblicazione ricercaDaIsbn(long isbn) throws PubblicazioneInesistenteException{
        Pubblicazione pubblicazione = catalogo.get(isbn);

        if (pubblicazione == null) throw new PubblicazioneInesistenteException("ISBN non presente nel catalogo");

        return pubblicazione;
    }

    public ArrayList<Pubblicazione> ricercaDaTitolo(String titolo) throws PubblicazioneInesistenteException{
        ArrayList<Pubblicazione> risultato = new ArrayList<>();

        for (Map.Entry<Long, Pubblicazione> entry:catalogo.entrySet()) {
            Pubblicazione pubblicazione = entry.getValue();
            if (pubblicazione.getTitolo().equals(titolo)) risultato.add(pubblicazione);
        }

        if (risultato.size() == 0) throw new PubblicazioneInesistenteException("Titolo non presente nel catalogo");

        return risultato;
    };

    public ArrayList<Libro> ricercaDaAutore(String autore) throws PubblicazioneInesistenteException{
        ArrayList<Libro> risultato = new ArrayList<>();

        for (Map.Entry<Long, Pubblicazione> entry:catalogo.entrySet()) {
            Pubblicazione pubblicazione = entry.getValue();
            if (pubblicazione instanceof Rivista) continue;
            if (((Libro) pubblicazione).getAutore().equals(autore)) risultato.add((Libro) pubblicazione);
        }

        if (risultato.size() == 0) throw new PubblicazioneInesistenteException("Autore non presente nel catalogo");

        return risultato;
    };

    public void salvaSuDisco(){
        File file = new File("files/file.txt");
        List<Pubblicazione> listaPubblicazioni = new ArrayList<>(catalogo.values());
        String stringaPubblicazioni = listaPubblicazioni.stream().map(p -> {
            String stringa = p.getIsbn()+"@"+p.getTitolo()+"@"+p.getAnnoPubblicazione()+"@"+p.getNumeroPagine()+"@";
            if (p instanceof Libro) {
                stringa = stringa.concat(((Libro) p).getAutore()+"@"+((Libro)p).getGenere());
            } else {
                stringa = stringa.concat(((Rivista) p).getPeriodicita().toString());
            }
            return stringa;
        }).collect(Collectors.joining("#"));

        try {
            FileUtils.writeStringToFile(file, stringaPubblicazioni, Charset.defaultCharset(), true);
        } catch (IOException e){
            logger.error(e.getMessage());
            System.out.println(e);
        }
    };

    public ArrayList<Pubblicazione> caricaDaDisco(){
        File file = new File("files/file.txt");

        try {
            String stringaPubblicazioni = FileUtils.readFileToString(file, Charset.defaultCharset());
            String[] stringheSingolaPubblicazione = stringaPubblicazioni.split("#");
            ArrayList<Pubblicazione> listaPubblicazioni = Arrays.stream(stringheSingolaPubblicazione).map(s -> {
                String[] stringaCaratteristichePubblicazione = s.split("@");
                Pubblicazione p;
                if (stringaCaratteristichePubblicazione.length == 5) {
                    Periodicita periodicita;
                    switch (stringaCaratteristichePubblicazione[4]) {
                        case "MENSILE":
                            periodicita = Periodicita.MENSILE;
                            break;
                        case "SETTIMANALE":
                            periodicita = Periodicita.SETTIMANALE;
                            break;
                        default:
                            periodicita = Periodicita.SEMESTRALE;
                    }
                    p = new Rivista(Long.parseLong(stringaCaratteristichePubblicazione[0]), stringaCaratteristichePubblicazione[1], Integer.parseInt(stringaCaratteristichePubblicazione[2]), Integer.parseInt(stringaCaratteristichePubblicazione[3]), periodicita);
                } else {
                    p = new Libro(Long.parseLong(stringaCaratteristichePubblicazione[0]), stringaCaratteristichePubblicazione[1], Integer.parseInt(stringaCaratteristichePubblicazione[2]), Integer.parseInt(stringaCaratteristichePubblicazione[3]), stringaCaratteristichePubblicazione[4], stringaCaratteristichePubblicazione[5]);
                }

                return p;
            }).collect(Collectors.toCollection(ArrayList::new));


            return listaPubblicazioni;
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println(e);
        }

        return null;
    };

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
