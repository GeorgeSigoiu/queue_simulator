import java.util.ArrayList;
import java.util.List;

/**
 * Realizeaza asezarea clientilor in coada potrivita
 */
public class Scheduler {
    /**
     * lista de cozi de asteptare
     */
    private final List<Server> servers=new ArrayList<>();
    /**
     * strategia dupa care se adauga clineti in coada
     */
    private Strategy strategy;

    /**
     * Realizeaza "desciderea cozilor de asteptare"
     */
    public Scheduler(){
        for(int i=0;i<StartUp.numberOfServers;i++){
            Server server=new Server();
            servers.add(server);
            Thread t=new Thread(server);
            t.start();
        }
    }

    /**
     * Alege strategia de adaugare a clientilor in cozi
     * @param policy SelectionPolicy, strategia de adaugare
     */
    public void changeStrategy(SelectionPolicy policy){
        if(policy==SelectionPolicy.SHORTEST_QUEUE){
            strategy=new ConcreteStrategyQueue();
        }
        if(policy==SelectionPolicy.SHORTEST_TIME){
            strategy=new ConcreteStrategyTime();
        }
    }

    /**
     * Adauga clientul in coada potrivita, in functia de strategia aleasa
     * @param t Task, clientul care trebuie introdus intr-o coada
     */
    public void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }

    /**
     * Extrage toate cozile la care pot astepta clientii
     * @return lista de Server
     */
    public List<Server> getServers(){
        return  servers;
    }

}

