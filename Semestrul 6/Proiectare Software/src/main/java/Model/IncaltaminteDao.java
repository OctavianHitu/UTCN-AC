package Model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncaltaminteDao {


    protected Logger LOGGER = Logger.getLogger("incaltaminte");

    private String createfindallquery() {
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        str.append(" * ");
        str.append(" FROM ");
        str.append("incaltaminte");
        return str.toString();
    }

    public List<IncaltaminteMagazin> findallincalataminte()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createfindallquery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            List<IncaltaminteMagazin> incaltaminteMagazins = new ArrayList<>();
            while(resultSet.next())
            {
                String numemagazin=resultSet.getObject("numemagazin").toString();
                String nume=resultSet.getObject("nume").toString();
                String producator=resultSet.getObject("producator").toString();
                String pret=resultSet.getObject("pret").toString();
                String marime=resultSet.getObject("marime").toString();
                String numarexemplareinitiale=resultSet.getObject("numarexemplareinitiale").toString();
                String numarexemplarevandute=resultSet.getObject("numarexemplarevandute").toString();

               // IncaltaminteDisponibila inc= new IncaltaminteDisponibila(Integer.parseInt(marime),Integer.parseInt(numarexemplareinitiale),Integer.parseInt(numarexemplarevandute));
                IncaltaminteDisponibila inc = new BuilderIncaltaminteDisp().setmarimeb(Integer.parseInt(marime)).setNumarexemplareinitialeb(Integer.parseInt(numarexemplareinitiale)).setNumarexemplarevanduteb(Integer.parseInt(numarexemplarevandute)).build();
                Incaltaminte incaltaminte= new Incaltaminte(nume,producator,Integer.parseInt(pret),inc);

                boolean found= false;
                int index =-1;
                for(IncaltaminteMagazin ind1:incaltaminteMagazins )
                {
                    if(ind1.getNumemagazin().equals(numemagazin))
                    {
                        found= true;
                        index= incaltaminteMagazins.indexOf(ind1);
                    }
                }
                if(!found)
                {
                    IncaltaminteMagazin incaltaminteMagazin= new IncaltaminteMagazin(numemagazin);
                    incaltaminteMagazin.addincaltaminteinmagazin(incaltaminte);
                    incaltaminteMagazins.add(incaltaminteMagazin);

                }
                else
                {
                    incaltaminteMagazins.get(index).addincaltaminteinmagazin(incaltaminte);
                }

            }
            return incaltaminteMagazins;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Incaltaminte" + "DAO:findall " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public void insertincaltamintedao(IncaltaminteMagazin incaltaminteMagazin)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO incaltaminte VALUE ( '";

        query= query + incaltaminteMagazin.getNumemagazin()+"','";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getNume()+"','";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getProducator()+"','";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getPret()+"',";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getIncaltaminteDisponibila().getMarime()+",";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getIncaltaminteDisponibila().getNumarexemplareinitiale()+",";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getIncaltaminteDisponibila().getNumarexemplarevandute()+")";

        connection =ConnectionFactory.getConnection();
        try
        {
            statement=connection.prepareStatement(query);
            statement.execute();

        }catch (SQLException e)
        {
            JOptionPane.showConfirmDialog(new JFrame(),"Nu am putut insera in tabela o incaltaminte"+e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void deleteincaltamintedao(IncaltaminteMagazin incaltaminteMagazin)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM incaltaminte WHERE numemagazin =";
        query += "'";
        query= query + incaltaminteMagazin.getNumemagazin()+"' and nume= '";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getNume()+"' and producator= '";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getProducator()+"'and pret= '";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getPret()+"' and marime= ";
        query=query+ incaltaminteMagazin.getIncaltamintes().get(0).getIncaltaminteDisponibila().getMarime();

        connection = ConnectionFactory.getConnection();

        try {
            statement = connection.prepareStatement(query);
            statement.execute();
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Nu am putut sterge Incaltamintea din tabela"+e.getMessage());

        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
    }


}
