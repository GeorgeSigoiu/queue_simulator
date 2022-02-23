import java.util.List;

/**
 * Reprezinta strategia de adaugare a unui client la coada cu cei mai putini clienti
 */
public class ConcreteStrategyQueue implements Strategy {
    /**
     * Adauga un client in coada (server-ul) cu cei mai putini clienti
     * @param servers lista de server-e, cozile la care se pot aseza clientii
     * @param t Task, clientul care se aseaza la coada
     */
    @Override
    public void addTask(List<Server> servers, Task t) {
        int indxServer = 0, minNumberOfTasks = 99999999, indx = 0;
        for (Server s : servers) {
            if (s.getWaitingPeriod().get() < minNumberOfTasks) {
                minNumberOfTasks = s.getWaitingPeriod().get();
                indxServer = indx;
            }
            indx++;
        }
        if(servers.get(indxServer).getTasks().size()==0){
            t.setFirst(1);
        }
        servers.get(indxServer).addTask(t);
    }
}
