package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends Menu implements Serializable {

    private List<Baseproduct> items;

    public CompositeProduct (String nm, List<Baseproduct> items)
    {
        this.name=nm;
        this.items= items;

    }

    @Override
    public float priceadd()
    {
        float total=0;
        for(Menu pro : items)
        {
            total+=pro.priceadd();
        }
        return total;
    }

    public double fataddcomp()
    {
        float total=0;
        for(Baseproduct pro : items)
        {
            total+=pro.getFat();
        }
        return total;

    }

    public double ratingcompose()
    {
        float total=0;
        float i=0;
        for(Baseproduct pro : items)
        {
            i++;
            total+=pro.getRating();
        }
        return total/i;

    }

    public double caloriesaddcomp()
    {
        float total=0;
        for(Baseproduct pro : items)
        {
            total+=pro.getCalories();
        }
        return total;

    }
    public double sodiumaddcomp()
    {
        float total=0;
        for(Baseproduct pro : items)
        {
            total+=pro.getSodium();
        }
        return total;

    }
    public double proteinaddcomp()
    {
        float total=0;
        for(Baseproduct pro : items)
        {
            total+=pro.getProtein();
        }
        return total;

    }




}
