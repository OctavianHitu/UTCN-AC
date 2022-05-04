package Model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Incaltaminte {

    public String nume;
    public String producator;
    public Integer pret;
    public IncaltaminteDisponibila incaltaminteDisponibila;

    public Incaltaminte(String nume, String producator, Integer pret,IncaltaminteDisponibila incaltaminteDisponibila) {
        this.nume = nume;
        this.producator = producator;
        this.pret = pret;
        this.incaltaminteDisponibila=incaltaminteDisponibila;

    }

    @Override
    public String toString() {
        return "Producator: " + producator + '\'' +
                ", Nume: " + nume + '\'' +
                ", Pret: " + pret + incaltaminteDisponibila;
    }
}
