package model;

public class Monom {
    private float coeficient;
    private int exponent;

    public Monom()
    {
    }

    public Monom(float coeficient, int exponent)
    {
        this.coeficient =coeficient;
        this.exponent =exponent;

    }


    public float getcoeficient()
    {
        return this.coeficient;
    }
    public int getexponent()
    {
        return this.exponent;
    }
    
    public void negare()
    {
        this.coeficient=(-1) * this.coeficient;
    }

    public void adunaremonoame(Monom mon)
    {
        this.coeficient= this.coeficient + mon.getcoeficient();
    }

    public String toString()
    {
        if(this.exponent !=0 && this.coeficient >0)
        {
            return "+" + String.format("%.2f",this.coeficient) + "X^"+ this.exponent;
        }else if(this.exponent != 0 && this.coeficient <0)
        {
            return String.format("%.2f",this.coeficient) +"X^" + this.exponent;
        }else if(this.exponent==0 && this.coeficient>0)
        {
            return "+" + String.format("%.2f",this.coeficient);
        }else if(this.exponent==0 && this.coeficient<0) {
            return " " +String.format("%.2f",this.coeficient);
        }else if(this.coeficient==0)
        {return " ";}
        else
            return " ";
    }

        public void inmultiremonoame(Monom mon)
        {
            this.coeficient= this.coeficient* mon.coeficient;
            this.exponent = this.exponent + mon.exponent;

        }

        public void derivaremonom()
        {
            if(this.exponent >1)
            {
                this.coeficient=this.coeficient * this.exponent;
                this.exponent = this.exponent -1;
            }else if(this.exponent ==1)
            {
                this.exponent=0;

            }else
            {
                this.coeficient=0;
            }
        }

        public void integraremonom()
        {
            this.coeficient=this.coeficient /(this.exponent +1);
            this.exponent= this.exponent +1;
        }

}
