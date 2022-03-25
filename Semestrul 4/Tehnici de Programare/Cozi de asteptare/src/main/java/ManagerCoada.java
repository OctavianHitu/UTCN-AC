public class ManagerCoada {

    private Coada[] que;
    private boolean terminat = false;

    public ManagerCoada(int numarCozi) {
        que = new Coada[numarCozi];
        for (int i = 0; i < numarCozi; i++) {
            que[i] = new Coada(this);
        }
    }

    public int shortestqueue() {
        int minim = que[0].getWaitTime();
        int nrcoada = 0;

        for (int i = 1; i < que.length; i++) {
            if (que[i].getWaitTime() < minim) {
                minim = que[i].getWaitTime();
                nrcoada = i;
            }
        }
        return nrcoada;
    }

    public Coada getQueue(int i) {
        return que[i];
    }

    public void setTerminat(boolean terminat) {
        this.terminat = terminat;
    }

    public boolean getTerminat() {
        return terminat;
    }

    public Coada[] getQueues() {
        return que;
    }
}
