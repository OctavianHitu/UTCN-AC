
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

public class Controller extends Thread {
    private View v;
    private ManagerCoada m;
    private Client[] clienti;

    private int minArr = 0;
    private int maxArr = 0;
    private int minSer = 0;
    private int maxSer = 0;
    private int noOfQueues = 0;
    private int noOfClienti = 0;
    private int simulationTime = 0;

    public Controller(View v) {
        this.v = v;
        v.addStart(new StartListener());
    }

    public void init() {
        m = new ManagerCoada(noOfQueues);
        clienti = new Client[noOfClienti];
        for(int i = 0; i < noOfClienti; i++)
            clienti[i] = new Client(i + 1, minArr, maxArr, minSer, maxSer);
        Arrays.sort(clienti);
        v.init(noOfQueues, noOfClienti);
        this.start();
    }

    public void run() {

        try {
            BufferedWriter fisier = new BufferedWriter(new FileWriter("simulation.txt"));

            int time = 0;
            while (time <= simulationTime) {
                for (Client client : clienti)
                    if (client.getarrivaltime() == time)
                        m.getQueue(m.shortestqueue()).clientAdd(client);

                fisier.write("\nTime " + time + "\nWaiting clients: ");
                System.out.print("\nTime " + time + "\nWaiting clients: ");

                for(int i = 0; i < clienti.length; i++) {
                    if(clienti[i].getarrivaltime() > time) {
                        fisier.write(clienti[i].toString() + "; ");
                        System.out.print(clienti[i].toString() + "; ");
                    }
                }

                fisier.write("\n");
                System.out.println();

                for(int i = 0; i < m.getQueues().length; i++) {
                    fisier.write("Queue " + (i + 1) + ": " + m.getQueue(i).toString() + "\n");
                    System.out.println("Queue " + (i + 1) + ": " + m.getQueue(i).toString());
                }

                for(int i = 0; i < noOfQueues; i++)
                    v.getProgressBarQueue(i).setValue(m.getQueue(i).getnumberofclients());
                v.getActualTimeTextField().setText(String.valueOf(time));

                Thread.sleep(1000);
                time++;
            }

            float avgWaitTime = 0;
            for(int i = 0; i < clienti.length; i++)
                avgWaitTime += clienti[i].getWaitingtime();
            avgWaitTime = avgWaitTime / clienti.length;

            fisier.write("\nAverage wait time: " + avgWaitTime);
            System.out.println("\nAverage wait time: " + avgWaitTime);
            v.getAvgWtimeTextField().setText(String.valueOf(avgWaitTime));

            fisier.close();
            m.setTerminat(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                minArr = Integer.parseInt(v.getMinArrTextField().getText());
                maxArr = Integer.parseInt(v.getMaxArrTextField().getText());
                minSer = Integer.parseInt(v.getMinSerTextField().getText());
                maxSer = Integer.parseInt(v.getMaxSerTextField().getText());
                noOfQueues = Integer.parseInt(v.getQueuesTextField().getText());
                noOfClienti = Integer.parseInt(v.getClientsTextField().getText());
                simulationTime = Integer.parseInt(v.getTimeTextField().getText());
                init();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }
}
