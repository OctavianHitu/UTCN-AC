package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class EmployeeFrame extends JFrame implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        JOptionPane.showMessageDialog(new JFrame(), "Comanda dumneavoastra tocmai a fost preluata de un angajat!");
    }
}
