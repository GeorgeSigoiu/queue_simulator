/**
 * Reprezinta modurile in care se pot adauga clientii in cozile de asteptare: in fucntie de coada cu cel mai mic timp de asteptare
 * sau in functie de coada cu cei mai putini clienti
 */
public enum SelectionPolicy {
    SHORTEST_QUEUE, SHORTEST_TIME
}
