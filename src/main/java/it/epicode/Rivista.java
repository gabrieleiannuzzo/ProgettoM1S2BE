package it.epicode;

public class Rivista extends Pubblicazione{
    private Periodicita periodicita;

    public Rivista (long isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "periodicita=" + periodicita +
                '}';
    }
}
