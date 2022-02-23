import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reprezinta manager-ul care controleaza comportamentul cozilor si clientilor
 */
public class SimulationManager implements Runnable {

    private final Scheduler scheduler;
    private final SimulationFrame frame;
    private final List<Task> generatedTasks = new ArrayList<>();

    public SimulationManager() {
        scheduler = new Scheduler();
        scheduler.changeStrategy(StartUp.selectionPolicy);
        frame = new SimulationFrame();
        frame.frame.setVisible(true);
        generateNRandomTasks();
    }

    /**
     * Creeaza clientii
     */
    public void generateNRandomTasks() {
        for (int i = 0; i < StartUp.numberOfClients; i++) {
            int arrivalT = (int) (Math.random() * (StartUp.maxArrivalTime - StartUp.minArrivalTime + 1) + StartUp.minArrivalTime);
            int processingT = (int) (Math.random() * (StartUp.maxProcessingTime - StartUp.minProcessingTime + 1) + StartUp.minProcessingTime);
            Task t = new Task(i + 1, arrivalT, processingT);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks);
    }

    /**
     * Descrie functionarea simularii
     */
    @Override
    public void run() {
        File fileOutput = new File("logEvents.txt");
        FileWriter write = null;
        try {
            write = new FileWriter(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert write != null;
        PrintWriter pw = new PrintWriter(write);

        int currentTime = 0;
        List<Task> clients = new ArrayList<>(generatedTasks);
        while (currentTime <= StartUp.timeLimit) {
            System.out.println("-------Time: " + currentTime + "----------------------");
            pw.println("-------Time: " + currentTime + "----------------------");
            List<Task> tasksRemoved = new ArrayList<>();
            for (Task t : generatedTasks) {
                if (t.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(t);
                    tasksRemoved.add(t);
                }
            }
            for (Task t : tasksRemoved) {
                generatedTasks.remove(t);
            }
            tasksRemoved.clear();

            System.out.print("Wainting clients: ");
            pw.print("Wainting clients: ");
            for (Task t : generatedTasks) {
                if (t.getArrivalTime() - currentTime >= 0) {
                    System.out.print("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getProcessingTime() + "); ");
                    pw.print("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getProcessingTime() + "); ");
                } else {
                    System.out.print("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getProcessingTime2(scheduler.getServers(), t) + "); ");
                    pw.print("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getProcessingTime2(scheduler.getServers(), t) + "); ");
                }
            }
            System.out.println();
            pw.println();

            updateUIFrame(clients, currentTime);
            if (generatedTasks.size() == 0 && serversAreEmpty(scheduler.getServers())) {
                frame.setTimer(currentTime, "(Nu mai sunt clienti)");
                break;
            }

            queuesContent(scheduler.getServers(), pw);

            waitPeriod(StartUp.waitTime * 1000);

            currentTime++;
        }
        if (currentTime < StartUp.timeLimit)
            queuesContent(scheduler.getServers(), pw);
        System.out.println();
        pw.println();
        pw.close();
    }

    /**
     * Afiseaza in consola starea fiecarei cozi, de asemenea scrie aceste informatii intr-in fisier
     * @param servers lista de Server, cozile de asteptare pentru care se afiseaza informatiile
     * @param pw PrintWriter, obiectul cu care se efectueaza scrierea in fisier
     */
    private void queuesContent(List<Server> servers, PrintWriter pw) {
        int indx = 1;
        for (Server s : servers) {
            System.out.print("Queue " + indx + ": ");
            pw.print("Queue " + indx + ": ");
            tasksFromQueue(s, pw);
            System.out.println();
            pw.println();
            indx++;
        }
    }

    /**
     * Afiseaza in consola si scrie intr-un fisier informatiile privind fiecare client dintr-o coada de astepatare
     * @param s Server, coada pentru care se afiseaza informatiile
     * @param pw PrintWriter, obiectul care scrie informatiile in fisier
     */
    private void tasksFromQueue(Server s, PrintWriter pw) {
        for (Task t : s.getTasks()) {
            System.out.print("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getProcessingTime2() + "); ");
            pw.print("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getProcessingTime2() + "); ");
        }
    }

    /**
     * Opreste thread-ul pentru un anumit numar de milisecunde
     * @param miliseconds Double, timpul in care thread-ul este oprit
     */
    private void waitPeriod(double miliseconds) {
        try {
            Thread.sleep((long) miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualizeaza starea fiecarui client (cat are de asteptat la coada)
     * @param clients lista de Task, lista cu toti clienti din magazin
     * @param currentTime Integer, momentul de timp la care se afla simularea
     */
    private void updateUIFrame(List<Task> clients, int currentTime) {
        for (Task t : clients) {
            if (t.getArrivalTime() - currentTime >= 0) {
                frame.setClientTxt(t.getArrivalTime() - currentTime, t.getProcessingTime(), t.getID());
            } else frame.setClientTxt(0, t.getProcessingTime2(scheduler.getServers(), t), t.getID());
        }
        frame.setTimer(currentTime, "");
        String[] str = createStringQueue();
        frame.setQueueEvenTxt(str[1]);
        frame.setQueueOddTxt(str[0]);
    }

    /**
     * True - daca servere nu mai au clienti, altfel false
     * @param servers lista de Server, lista de cozi de asteptare
     * @return boolean
     */
    private boolean serversAreEmpty(List<Server> servers) {
        boolean empty = true;
        for (Server s : servers)
            if (s.getTasks().size() != 0) {
                empty = false;
                break;
            }
        return empty;
    }

    /**
     * Creeaza model de afisare pentru cozile din interfata
     * @return sir de String
     */
    private String[] createStringQueue() {
        int indx = 0;
        int indxs1, indxs2;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (Server s : scheduler.getServers()) {
            if (indx % 2 == 0) {
                indxs1 = 0;
                s1.append("Coada ").append(indx + 1).append("\n");
                for (Task t : s.getTasks()) {
                    s1.append("-client ").append(t.getID()).append("\n");
                    indxs1++;
                }
                s1.append("-\n".repeat(Math.max(0, StartUp.numberOfClients / StartUp.numberOfServers - indxs1)));
            } else {
                indxs2 = 0;
                s2.append("Coada ").append(indx + 1).append("\n");
                for (Task t : s.getTasks()) {
                    s2.append("-client ").append(t.getID()).append("\n");
                    indxs2++;
                }
                s2.append("-\n".repeat(Math.max(0, StartUp.numberOfClients / StartUp.numberOfServers - indxs2)));
            }
            indx++;
        }
        String[] str = new String[2];
        str[0] = s1.toString();
        str[1] = s2.toString();
        return str;
    }


}
