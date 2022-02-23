import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Reprezinta clientul
 */
public class Task implements Comparable {
    private final int arrivalTime;
    private final int processingTime;
    private final int ID;
    private int processingTime2;
    private int firstPeriod = 0;
    private int first = 0;

    /**
     * Creeaza clientul
     * @param ID Integer, id-ul clientului
     * @param arrivalTime Integer, timpul dupa care seaseaza la coada
     * @param processingTime Integer, timpul cat dureaza servirea acestuia (timpul cat dureaza sa plateasca bunurile achizitionate)
     */
    public Task(int ID, int arrivalTime, int processingTime) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        processingTime2 = processingTime;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    /**
     * Calculeaza timpul de astepatare astefel incat sa fie afisat corect in interfata
     * @param servers lista de Server, lista de cozi deschise
     * @param t task, clientul pentru care se calculeaza timpul pentru procesare
     * @return Integer
     */
    public int getProcessingTime2(List<Server> servers, Task t) {
        if (taskIsFirstInQueue(servers, t)) {

            if (firstPeriod == 1 || first==1)
                processingTime2--;
            else firstPeriod = 1;

        } else if (processingTime2 == 1) {
            processingTime2 = 0;
        }
        return processingTime2;
    }

    /**
     * True - daca un anumit client este primul intr-o coada, altfel false
     * @param servers lista de Server, lista de cozi in care se cauta
     * @param t Task, clientul pentru care se verifica daca este primul in coada
     * @return boolean
     */
    private boolean taskIsFirstInQueue(List<Server> servers, Task t) {
        for (Server s : servers) {
            if (s.getTasks().peek() == t) return true;
        }
        return false;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        Task t = (Task) o;
        return arrivalTime - t.arrivalTime;
    }

    @Override
    public String toString() {
        return "Task " + ID + "\nta=" + arrivalTime + ", ts=" + processingTime + "\n";
    }

    public int getProcessingTime2() {
        return processingTime2;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }
}
