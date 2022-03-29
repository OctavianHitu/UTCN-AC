package Model;

public class IncaltaminteDisponibila {

    public Integer marime;
    public Integer numarexemplareinitiale;
    public Integer Numarexemplarevandute;

    public IncaltaminteDisponibila(Integer marimi, Integer numarexemplareinitiale, Integer Numarexemplarevandute) {
        this.marime = marimi;
        this.numarexemplareinitiale = numarexemplareinitiale;
        this.Numarexemplarevandute = Numarexemplarevandute;
    }

    public Integer getMarime() {
        return marime;
    }

    public void setMarimi(Integer marimi) {
        this.marime = marimi;
    }

    public Integer getNumarexemplareinitiale() {
        return numarexemplareinitiale;
    }

    public void setNumarexemplareinitiale(Integer numarexemplareinitiale) {
        this.numarexemplareinitiale = numarexemplareinitiale;
    }

    public Integer getGetNumarexemplarevandute() {
        return Numarexemplarevandute;
    }

    public void setGetNumarexemplarevandute(Integer getNumarexemplarevandute) {
        this.Numarexemplarevandute = getNumarexemplarevandute;
    }


    @Override
    public String toString() {
        return
                ", NumarExemplareInitiale:" + numarexemplareinitiale +
                ", NumarExemplareVandute:" + Numarexemplarevandute +
                        ", Marime:" + marime ;
    }
}
