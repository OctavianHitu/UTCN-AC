package Model;

public class BuilderIncaltaminteDisp {

    public Integer marime;
    public Integer numarexemplareinitiale;
    public Integer Numarexemplarevandute;

    public  BuilderIncaltaminteDisp()
    {
        this.marime=0;
        this.numarexemplareinitiale=0;
        this.Numarexemplarevandute=0;
    }

    public BuilderIncaltaminteDisp setmarimeb(Integer marime) {
        this.marime = marime;
        return this;
    }

    public BuilderIncaltaminteDisp setNumarexemplareinitialeb(Integer numarexemplareinitiale) {
        this.numarexemplareinitiale = numarexemplareinitiale;
        return this;
    }

    public BuilderIncaltaminteDisp setNumarexemplarevanduteb(Integer numarexemplarevandute) {
        Numarexemplarevandute = numarexemplarevandute;
        return this;
    }

    public IncaltaminteDisponibila build()
    {
        return new IncaltaminteDisponibila(marime,numarexemplareinitiale,Numarexemplarevandute);
    }

}
