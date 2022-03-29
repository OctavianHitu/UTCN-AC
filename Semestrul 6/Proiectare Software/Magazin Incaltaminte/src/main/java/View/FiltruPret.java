package View;

import Presenter.PresenterFiltru;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiltruPret extends JFrame implements FiltruPretI{
    private JPanel panel1;
    private JButton filtrupret;
    private JTextField pretminimtf;
    private JTextField pretmaximtf;
    private JTextArea afisincal;

    public FiltruPret()
    {
        this.setTitle("Login");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel1);
        this.setVisible(true);

        PresenterFiltru presenterFiltru= new PresenterFiltru(this);
        filtrupret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterFiltru.filtrarefin();
            }
        });
    }

    @Override
    public void afisareincal(String af)
    {
        this.afisincal.setText(af);
    }
    @Override
    public String getPretminimtf() {
        return this.pretminimtf.getText();
    }

    @Override
    public String getPretmaximtf() {
        return this.pretmaximtf.getText();
    }
}
