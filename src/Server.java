import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Reprezinta coada de asteptare
 */
public class Server implements Runnable {
    /**
     * clientii din coada respectiva
     */
    private final BlockingQueue<Task> tasks;
    /**
     * timpul de asteptare pentru ca coada sa fie goala
     */
    private final AtomicInteger waitingPeriod;

    public Server() {
        tasks = new LinkedBlockingQueue<>(StartUp.numberOfClients);
        waitingPeriod = new AtomicInteger(0);
    }

    /**
     * Adauga clientul in coada
     * @param newTask Task, clientul care trebuie adaugat in coada
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.getAndIncrement();
    }

    /**
     * Realizeaza functia unei cozi de asteptare. Asteapta un numar de secunde egal cu timpul de procesare al primului
     * client dupa care il elimina din coada
     */
    @Override
    public void run() {
        while (true) {
            try {
                Task t = tasks.peek();
                if (t != null) {
                    if (t.getFirst() == 1)
                        Thread.sleep((long) ((t.getProcessingTime() - 1) * (StartUp.waitTime*1000) + 100));
                    else
                        Thread.sleep((long) ((t.getProcessingTime()) * (StartUp.waitTime*1000) + 100));
                    t = tasks.take();
                    waitingPeriod.getAndDecrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Extrage clientii din coada
     * @return lista de Task, reprezinta clientii care asteapta la coada
     */
    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    /**
     * Getter pentru perioada de asteptare pana cand coada va deveni goala
     * @return AtomicInteger, perioada amintita mai sus
     */
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

}
