
import model.Polinom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class testpolinom  {

    Polinom pol1;
    Polinom pol2;
    Polinom polr;//model.polinom rezultat
    Polinom polrc;//model.polinom rezultat corect


    @Test
    public void testsuma()
    {
        pol1= new Polinom();
        pol2= new Polinom();
        polr= new Polinom();
        polrc = new Polinom();

        //(3x^4+3x^3-2x^2+1x^1)
        pol1.testadaugare(3,4);
        pol1.testadaugare(3,3);
        pol1.testadaugare(-2,2);
        pol1.testadaugare(1,1);

        //(2x^3+3x^2+2x^1)
        pol2.testadaugare(2,3);
        pol2.testadaugare(3,2);
        pol2.testadaugare(2,1);

        //(3x^4+5x^3+1x^2+3x^1)
        polrc.testadaugare(3,4);
        polrc.testadaugare(5,3);
        polrc.testadaugare(1,2);
        polrc.testadaugare(3,1);

        polr.adunarepolinoame(pol1,pol2);
        Assertions.assertEquals(polr.printarepolinom(),polrc.printarepolinom());




    }

    @Test
    public void testscadere() {
        pol1 = new Polinom();
        pol2 = new Polinom();
        polr = new Polinom();
        polrc = new Polinom();

        //(3x^4+3x^3-2x^2+1x^1)
        pol1.testadaugare(3, 4);
        pol1.testadaugare(3, 3);
        pol1.testadaugare(-2, 2);
        pol1.testadaugare(1, 1);

        //(2x^3+3x^2+2x^1)
        pol2.testadaugare(2, 3);
        pol2.testadaugare(3, 2);
        pol2.testadaugare(2, 1);

        //(3x^4+1x^3-5x^2-1x^1)
        polrc.testadaugare(3, 4);
        polrc.testadaugare(1, 3);
        polrc.testadaugare(-5, 2);
        polrc.testadaugare(-1, 1);

        polr.scaderepolinom(pol1, pol2);
        Assertions.assertEquals(polr.printarepolinom(), polrc.printarepolinom());
    }

    @Test
    public void testinmultire() {
        pol1 = new Polinom();
        pol2 = new Polinom();
        polr = new Polinom();
        polrc = new Polinom();

        //(2x^4+3x^2)
        pol1.testadaugare(2,4);
        pol1.testadaugare(3,2);


        //(2x^3+1x^1)
        pol2.testadaugare(2,3);
        pol2.testadaugare(1,1);


        //(4x^7+2x^5+6x^5+3x^3)
        polrc.testadaugare(4,7);
        polrc.testadaugare(8,5);
        polrc.testadaugare(3,3);

        polr.inmultirepolinoame(pol1,pol2);
        Assertions.assertEquals(polr.printarepolinom(),polrc.printarepolinom());


    }
    @Test
    public void testderivare()
    {
        pol1 = new Polinom();
       // polr = new model.polinom();
        polrc = new Polinom();

        //(3x^3+2x^2+ 2x^1)
        pol1.testadaugare(3,3);
        pol1.testadaugare(2,2);
        pol1.testadaugare(2,1);

        //(9x^2+4x^1+2)
        polrc.testadaugare(9,2);
        polrc.testadaugare(4,1);
        polrc.testadaugare(2,0);


        pol1.derivarepolinoame();
        Assertions.assertEquals(pol1.printarepolinom(),polrc.printarepolinom());

    }


    @Test
    public void testintegrare()
    {
        pol1 = new Polinom();

        polrc = new Polinom();


        //(9x^2+4x^1+2)
        pol1.testadaugare(9,2);
        pol1.testadaugare(4,1);
        pol1.testadaugare(2,0);

        //(3x^3+2x^2+ 2x^1)
        polrc.testadaugare(3,3);
        polrc.testadaugare(2,2);
        polrc.testadaugare(2,1);


        pol1.integrarepolinom();
        Assertions.assertEquals(pol1.printarepolinom(),polrc.printarepolinom());

    }
}
