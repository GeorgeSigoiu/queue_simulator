import java.util.List;
/**
 * Reprezinta strategia de adaugare a unui client la coada cu cel mai mic timp de asteptare (pana cand ultimul client ajunge primul)
 */
public class ConcreteStrategyTime implements Strategy {

    /**
     * Adauga un client in coada (server-ul) cu cel mai mic timp de asteptare
     * @param servers lista de server-e, cozile la care se pot aseza clientii
     * @param t Task, clientul care se aseaza la coada
     */
    @Override
    public void addTask(List<Server> servers, Task t) {
        int indxServer = 0, minWaitingTime = 999999999, indx = 0;
        for (Server s : servers) {
            int waitingTime=0;
            for(Task task:s.getTasks()){
                waitingTime+=task.getProcessingTime();
            }
            if(waitingTime<minWaitingTime){
                minWaitingTime=waitingTime;
                indxServer=indx;
            }
            indx++;
        }
        if(servers.get(indxServer).getTasks().size()==0){
            t.setFirst(1);
        }
        servers.get(indxServer).addTask(t);
    }
}
