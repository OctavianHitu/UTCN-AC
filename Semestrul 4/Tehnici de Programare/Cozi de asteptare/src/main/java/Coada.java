
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada extends Thread {

        private BlockingQueue<Client> coada = new LinkedBlockingQueue<Client>();
        private AtomicInteger waitingTime = new AtomicInteger(0);
        private ManagerCoada managerCoada;

        public Coada(ManagerCoada managerCoada) {
                this.managerCoada = managerCoada;
                this.start();
        }

        public int getnumberofclients() {
                return coada.size();
        }

        public int getWaitTime() {
                return waitingTime.get();
        }

        public void clientAdd(Client clnt) {
                clnt.setWaitingtime(waitingTime.get());
                waitingTime.addAndGet(clnt.getservicetime());
                coada.add(clnt);
        }

        public String toString() {
                String out = "";
                for(Client c: coada)
                        out = out + c.toString() + "; ";
                return out;
        }

        public void run() {
                Client clnt;
                while (managerCoada.getTerminat() == false) {
                        if (coada.isEmpty() != true) {
                                try {
                                        clnt = coada.peek();
                                        while (clnt.getservicetime() > 1) {
                                                clnt.setservicetime(clnt.getservicetime() - 1);
                                                waitingTime.decrementAndGet();
                                                Thread.sleep(1000);
                                        }
                                        waitingTime.decrementAndGet();
                                        coada.poll();//elimina client
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }


}
