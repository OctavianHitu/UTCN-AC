
import java.util.Random;

public class Client implements Comparable<Client> {
        private int id;
        private int arrivaltime = 0;
        private int servicetime = 0;
        private int waitingtime = 0;

        public int getarrivaltime() {
                return arrivaltime;
        }

        public int getservicetime() {
                return servicetime;
        }

        public void setservicetime(int ser) {
                this.servicetime = ser;
        }

        public int getWaitingtime() {
                return waitingtime;
        }

        public void setWaitingtime(int wat) {
                this.waitingtime = wat;
        }

        public Client(int id, int minArrival, int maxArrival, int minService, int maxService) {
                this.id = id;
                this.waitingtime = 0;
                rndarrivaltime(minArrival, maxArrival);
                rndsericetime(minService, maxService);
        }

        @Override
        public String toString() {
                return "(" + id + "," + arrivaltime + "," + servicetime + ")";
        }

        public void rndarrivaltime(int minat, int maxat) {
                Random r = new Random();
                this.arrivaltime = r.nextInt(maxat - minat + 1) + minat;
        }

        public void rndsericetime(int minst, int maxst) {
                Random r = new Random();
                this.servicetime = r.nextInt(maxst - minst + 1) + minst;
        }

        @Override
        public int compareTo(Client o) {
                return this.arrivaltime - o.arrivaltime;
        }
}
