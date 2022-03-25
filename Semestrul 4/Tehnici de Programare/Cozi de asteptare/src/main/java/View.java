
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class View extends JFrame {

    private JTextField minArrTextField;
    private JTextField maxArrTextField;
    private JTextField minSerTextField;
    private JTextField maxSerTextField;
    private JTextField timeTextField;
    private JTextField queuesTextField;
    private JTextField clientsTextField;
    private JTextField avgWtimeTextField;
    private JTextField actualTimeTextField;
    private JButton startButton;
    private JPanel panel = new JPanel();
    private JPanel queuePanel;
    private JProgressBar[] progressBarQueue;

    public JProgressBar getProgressBarQueue(int index) {
        return progressBarQueue[index];
    }

    public JTextField getMinArrTextField() {
        return minArrTextField;
    }

    public JTextField getMaxArrTextField() {
        return maxArrTextField;
    }

    public JTextField getMinSerTextField() {
        return minSerTextField;
    }

    public JTextField getMaxSerTextField() {
        return maxSerTextField;
    }

    public JTextField getTimeTextField() {
        return timeTextField;
    }

    public JTextField getQueuesTextField() {
        return queuesTextField;
    }

    public JTextField getClientsTextField() {
        return clientsTextField;
    }

    public JTextField getActualTimeTextField() {
        return actualTimeTextField;
    }

    public JTextField getAvgWtimeTextField() {
        return avgWtimeTextField;
    }

    public View() {
        this.setBounds(10, 10, 1000, 784);
        this.setTitle("Shop Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBounds(10, 10, 1000, 784);
        this.add(panel);
        panel.setLayout(null);

        queuePanel = new JPanel(new GridLayout(1, 0, 10, 10));
        queuePanel.setBounds(10, 10, 700, 740);
        panel.add(queuePanel);

        JLabel actualTimeLabel = new JLabel("Time :");
        actualTimeLabel.setBounds(768, 453, 124, 19);
        panel.add(actualTimeLabel);

        actualTimeTextField = new JTextField();
        actualTimeTextField.setBounds(891, 450, 66, 22);
        panel.add(actualTimeTextField);

        JLabel avgWtimeLabel = new JLabel("Average wait time :");
        avgWtimeLabel.setBounds(748, 488, 124, 19);
        panel.add(avgWtimeLabel);

        avgWtimeTextField = new JTextField();
        avgWtimeTextField.setBounds(891, 485, 66, 22);
        panel.add(avgWtimeTextField);

        JLabel minArrLabel = new JLabel("Min Arrival Time :");
        minArrLabel.setBounds(748, 522, 124, 19);
        panel.add(minArrLabel);

        minArrTextField = new JTextField();
        minArrTextField.setBounds(891, 520, 66, 22);
        panel.add(minArrTextField);

        JLabel maxArrLabel = new JLabel("Max Arrival Time:");
        maxArrLabel.setBounds(748, 558, 111, 16);
        panel.add(maxArrLabel);

        maxArrTextField = new JTextField();
        maxArrTextField.setBounds(891, 555, 66, 22);
        panel.add(maxArrTextField);

        JLabel minSerLabel = new JLabel("Min Service Time:");
        minSerLabel.setBounds(748, 593, 111, 16);
        panel.add(minSerLabel);

        minSerTextField = new JTextField();
        minSerTextField.setBounds(891, 590, 66, 22);
        panel.add(minSerTextField);

        JLabel maxSerLabel = new JLabel("Max Service Time:");
        maxSerLabel.setBounds(748, 630, 111, 16);
        panel.add(maxSerLabel);

        maxSerTextField = new JTextField();
        maxSerTextField.setBounds(891, 627, 66, 22);
        panel.add(maxSerTextField);

        JLabel timeLabel = new JLabel("Time to simulate:");
        timeLabel.setBounds(748, 665, 111, 16);
        panel.add(timeLabel);

        timeTextField = new JTextField();
        timeTextField.setBounds(891, 662, 66, 22);
        panel.add(timeTextField);

        JLabel queuesLabel = new JLabel("No. of Queues:");
        queuesLabel.setBounds(748, 700, 111, 16);
        panel.add(queuesLabel);

        queuesTextField = new JTextField();
        queuesTextField.setBounds(891, 697, 66, 22);
        panel.add(queuesTextField);

        JLabel clientsLabel = new JLabel("No. of Clients:");
        clientsLabel.setBounds(748, 735, 112, 16);
        panel.add(clientsLabel);

        clientsTextField = new JTextField();
        clientsTextField.setBounds(891, 732, 66, 22);
        panel.add(clientsTextField);

        startButton = new JButton("Start");
        startButton.setBounds(748, 400, 76 + 112, 25);
        panel.add(startButton);



        this.setVisible(true);
    }


    void init(int noOfQueues, int noOfClients) {
        progressBarQueue = new JProgressBar[noOfQueues];
        queuePanel.removeAll();

        for (int i = 0; i < noOfQueues; i++) {
            JPanel auxPanel = new JPanel(new BorderLayout());
            auxPanel.add(new JLabel("Queue: " + (i + 1)), BorderLayout.PAGE_START);
            progressBarQueue[i] = new JProgressBar(0, noOfClients);
            progressBarQueue[i].setValue(0);
            progressBarQueue[i].setOrientation(SwingConstants.VERTICAL);
            progressBarQueue[i].setStringPainted(true);
            auxPanel.add(progressBarQueue[i], BorderLayout.CENTER);
            queuePanel.add(auxPanel);
        }
        queuePanel.revalidate();
        queuePanel.repaint();
    }

    void addStart(ActionListener sal) {
        startButton.addActionListener(sal);
    }

}