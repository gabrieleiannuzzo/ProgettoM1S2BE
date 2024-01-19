package it.epicode;

public class UsaCatalogo {
    public static void main(String[] args) {
        //Logger logger = LoggerFactory.getLogger("Progetto");

        Libro libro1 = new Libro(0, "Libro 1", 2024, 100, "Gabriele", "Fantasy");
        Libro libro2 = new Libro(0, "Libro 2", 2020, 100, "Luca", "Fantasy");
        Libro libro3 = new Libro(0, "Libro 3", 1988, 100, "Giacomo", "Giallo");
        Libro libro4 = new Libro(0, "Libro 4", 2012, 100, "Marco", "Fantasy");
        Libro libro5 = new Libro(0, "Libro 5", 1999, 100, "Gabriele", "Fantasy");
        Libro libro6 = new Libro(0, "Libro 6", 2005, 100, "Marco", "Romanzo");
        Libro libro7 = new Libro(0, "Libro 7", 2003, 100, "Marco", "Thriller");
        Libro libro8 = new Libro(0, "Libro 8", 2021, 100, "Gabriele", "Giallo");
        Libro libro9 = new Libro(0, "Libro 9", 1876, 100, "Luca", "Romanzo");
        Libro libro10 = new Libro(0, "Libro 10", 2018, 100, "Giacomo", "Thriller");

        Rivista rivista1 = new Rivista(0, "Rivista 1", 2000, 100, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista(0, "Rivista 2", 2000, 100, Periodicita.MENSILE);
        Rivista rivista3 = new Rivista(0, "Rivista 3", 2000, 100, Periodicita.SETTIMANALE);
        Rivista rivista4 = new Rivista(0, "Rivista 4", 2000, 100, Periodicita.SEMESTRALE);
        Rivista rivista5 = new Rivista(0, "Rivista 5", 2000, 100, Periodicita.MENSILE);
        Rivista rivista6 = new Rivista(0, "Rivista 6", 2000, 100, Periodicita.MENSILE);
        Rivista rivista7 = new Rivista(0, "Rivista 7", 2000, 100, Periodicita.SETTIMANALE);
        Rivista rivista8 = new Rivista(0, "Rivista 8", 2000, 100, Periodicita.SEMESTRALE);
        Rivista rivista9 = new Rivista(0, "Rivista 9", 2000, 100, Periodicita.SETTIMANALE);
        Rivista rivista10 = new Rivista(0, "Rivista 10", 2000, 100, Periodicita.SEMESTRALE);

        Catalogo catalogo = new Catalogo();

        try {
            catalogo.aggiungiPubblicazione(libro1);
            catalogo.aggiungiPubblicazione(libro2);
            catalogo.aggiungiPubblicazione(libro3);
            catalogo.aggiungiPubblicazione(libro4);
            catalogo.aggiungiPubblicazione(libro5);
            catalogo.aggiungiPubblicazione(libro6);
            catalogo.aggiungiPubblicazione(libro7);
            catalogo.aggiungiPubblicazione(libro8);
            catalogo.aggiungiPubblicazione(libro9);
            catalogo.aggiungiPubblicazione(libro10);
            catalogo.aggiungiPubblicazione(rivista1);
            catalogo.aggiungiPubblicazione(rivista2);
            catalogo.aggiungiPubblicazione(rivista3);
            catalogo.aggiungiPubblicazione(rivista4);
            catalogo.aggiungiPubblicazione(rivista5);
            catalogo.aggiungiPubblicazione(rivista6);
            catalogo.aggiungiPubblicazione(rivista7);
            catalogo.aggiungiPubblicazione(rivista8);
            catalogo.aggiungiPubblicazione(rivista9);
            catalogo.aggiungiPubblicazione(rivista10);
        } catch (Exception e) {
            //logger.error(e.getMessage());
            System.out.println(e);
        }

        try {
            long isbn = libro1.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            isbn = libro2.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            isbn = libro3.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            isbn = libro4.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            isbn = libro5.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            isbn = libro6.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            isbn = libro7.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            isbn = libro8.getIsbn();
            catalogo.rimuoviPubblicazioneDaIsbn(isbn);
            System.out.println(catalogo.getCatalogo());
        } catch (PubblicazioneInesistenteException e) {
            //logger.error(e.getMessage());
            System.out.println(e);
        }

        System.out.println(catalogo.caricaDaDisco());
    }
}
