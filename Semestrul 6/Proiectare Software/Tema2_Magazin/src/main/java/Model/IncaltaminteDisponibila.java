package Model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


public class IncaltaminteDisponibila {

    public Integer marime;
    public Integer numarexemplareinitiale;
    public Integer Numarexemplarevandute;

    public IncaltaminteDisponibila(Integer marimi, Integer numarexemplareinitiale, Integer Numarexemplarevandute) {
        this.marime = marimi;
        this.numarexemplareinitiale = numarexemplareinitiale;
        this.Numarexemplarevandute = Numarexemplarevandute;
    }
    @Override
    public String toString() {
        return
                ", NumarExemplareInitiale:" + numarexemplareinitiale +
                        ", NumarExemplareVandute:" + Numarexemplarevandute +
                        ", Marime:" + marime ;
    }
}
