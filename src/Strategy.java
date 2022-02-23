import java.util.List;

public interface Strategy {
    /**
     * Adaugarea unui client in coada cea mai potrivita lui
     * @param servers lista de Server, cozile in care se poate adauga clientul
     * @param t Task, clientul
     */
    public void addTask(List<Server>servers,Task t) ;
}
