package Model;

public class Incaltaminte  {

    public String nume;
    public String producator;
    public Integer pret;
    public IncaltaminteDisponibila incaltaminteDisponibila;



    public Incaltaminte()
    {

    }
    public Incaltaminte(String nume, String producator, Integer pret,IncaltaminteDisponibila incaltaminteDisponibila) {
        this.nume = nume;
        this.producator = producator;
        this.pret = pret;
        this.incaltaminteDisponibila=incaltaminteDisponibila;

    }

    public String getNume() {
        return nume;
    }

    public String getProducator() {
        return producator;
    }

    public Integer getPret() {
        return pret;
    }

    public IncaltaminteDisponibila getIncaltaminteDisponibila() {
        return incaltaminteDisponibila;
    }

    public void setIncaltaminteDisponibila(IncaltaminteDisponibila incaltaminteDisponibila) {
        this.incaltaminteDisponibila = incaltaminteDisponibila;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Producator: " + producator + '\'' +
                ", Nume: " + nume + '\'' +
                ", Pret: " + pret + incaltaminteDisponibila;
    }
}
