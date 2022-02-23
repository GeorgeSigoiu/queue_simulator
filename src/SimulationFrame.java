import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

/**
 * Reprezinta interfata grafica in care se pot vizualiza rezultatele simularii si decurgerea acesteia
 */
public class SimulationFrame {
    private final JComboBox strategyCombo = new JComboBox();
    private final JPanel timerPanel = new JPanel();
    private final JPanel queuePanel = new JPanel();
    private final JScrollPane scrollPane1 = new JScrollPane();
    private final JTextArea queueOddTxt = new JTextArea();
    private final JScrollPane scrollPane2 = new JScrollPane();
    private final JTextArea queueEvenTxt = new JTextArea();
    private final List<JTextArea> txtAreas = new ArrayList<>();
    private final JTextArea timerTxt = new JTextArea();
    JFrame frame;
    private JTextField clientsTxtField;
    private JTextField queuesTxtField;
    private JTextField minArrivalTimeTxtField;
    private JTextField maxArrivalTimeTxtField;
    private JTextField minProcessingTimeTxtField;
    private JTextField maxProcessingTimeTxtField;
    private JTextField waitTimeTxtField;
    private JTextField timeLimitTxtField;

    public SimulationFrame() {
        initialize();
    }

    /**
     * Creeaza interfata
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(40, 20, 1400, 800);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        queuePanel.setBounds(10, 10, 411, 718);

        queuePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        frame.getContentPane().add(queuePanel);
        queuePanel.setLayout(new GridLayout(1, 0, 0, 0));

        queuePanel.add(scrollPane1);

        queueOddTxt.setFont(new Font("Monospaced", Font.PLAIN, 20));
        scrollPane1.setViewportView(queueOddTxt);

        queuePanel.add(scrollPane2);

        queueEvenTxt.setFont(new Font("Monospaced", Font.PLAIN, 20));
        scrollPane2.setViewportView(queueEvenTxt);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(442, 168, 918, 560);
        frame.getContentPane().add(scrollPane);

        JPanel panel1 = new JPanel();
        scrollPane.setViewportView(panel1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        JPanel panel = new JPanel();
        panel1.add(panel);

        for (int i = 1; i <= StartUp.numberOfClients; i++) {
            JTextArea area = new JTextArea();
            area.setText("client " + i + "\r\nT arrival\r\n   2\r\nT processing\r\n   4");
            txtAreas.add(area);
            panel.add(area);
            if (i % 12 == 0) {
                panel = new JPanel();
                panel1.add(panel);
            }
        }

        timerPanel.setBounds(525, 10, 464, 41);
        frame.getContentPane().add(timerPanel);
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));

        timerTxt.setFont(new Font("Monospaced", Font.PLAIN, 20));
        timerPanel.add(timerTxt);

        clientsTxtField = new JTextField();
        clientsTxtField.setText("4");
        clientsTxtField.setBounds(544, 73, 32, 19);
        frame.getContentPane().add(clientsTxtField);
        clientsTxtField.setColumns(10);

        JLabel clientsLabel = new JLabel("Num. clients: ");
        clientsLabel.setBounds(454, 74, 80, 16);
        frame.getContentPane().add(clientsLabel);

        JLabel queuesLabel = new JLabel("Num. queues");
        queuesLabel.setBounds(454, 100, 80, 16);
        frame.getContentPane().add(queuesLabel);

        JLabel minArrivalTimeLabel = new JLabel("Min arrival time:");
        minArrivalTimeLabel.setBounds(607, 74, 121, 16);
        frame.getContentPane().add(minArrivalTimeLabel);

        JLabel maxArrivalTimeLabel = new JLabel("Max arrival time:");
        maxArrivalTimeLabel.setBounds(607, 100, 121, 16);
        frame.getContentPane().add(maxArrivalTimeLabel);

        JLabel minProcessingTimeLabel = new JLabel("Min processing time:");
        minProcessingTimeLabel.setBounds(776, 74, 121, 16);
        frame.getContentPane().add(minProcessingTimeLabel);

        queuesTxtField = new JTextField();
        queuesTxtField.setText("2");
        queuesTxtField.setColumns(10);
        queuesTxtField.setBounds(544, 99, 32, 19);
        frame.getContentPane().add(queuesTxtField);

        minArrivalTimeTxtField = new JTextField();
        minArrivalTimeTxtField.setText("2");
        minArrivalTimeTxtField.setColumns(10);
        minArrivalTimeTxtField.setBounds(712, 73, 32, 19);
        frame.getContentPane().add(minArrivalTimeTxtField);

        maxArrivalTimeTxtField = new JTextField();
        maxArrivalTimeTxtField.setText("10");
        maxArrivalTimeTxtField.setColumns(10);
        maxArrivalTimeTxtField.setBounds(712, 99, 32, 19);
        frame.getContentPane().add(maxArrivalTimeTxtField);

        minProcessingTimeTxtField = new JTextField();
        minProcessingTimeTxtField.setText("3");
        minProcessingTimeTxtField.setColumns(10);
        minProcessingTimeTxtField.setBounds(909, 73, 32, 19);
        frame.getContentPane().add(minProcessingTimeTxtField);

        maxProcessingTimeTxtField = new JTextField();
        maxProcessingTimeTxtField.setText("7");
        maxProcessingTimeTxtField.setColumns(10);
        maxProcessingTimeTxtField.setBounds(909, 99, 32, 19);
        frame.getContentPane().add(maxProcessingTimeTxtField);

        JLabel maxProcessingTimeLabel = new JLabel("Max processing time:");
        maxProcessingTimeLabel.setBounds(776, 102, 121, 16);
        frame.getContentPane().add(maxProcessingTimeLabel);

        strategyCombo.setModel(new DefaultComboBoxModel(new String[]{"Shortest waiting time", "Shortest queue length"}));
        strategyCombo.setBounds(990, 72, 184, 21);
        frame.getContentPane().add(strategyCombo);

        JLabel waitTimeLabel = new JLabel("Wait time");
        waitTimeLabel.setBounds(1201, 76, 61, 13);
        frame.getContentPane().add(waitTimeLabel);

        waitTimeTxtField = new JTextField();
        waitTimeTxtField.setText("1");
        waitTimeTxtField.setColumns(10);
        waitTimeTxtField.setBounds(1261, 73, 32, 19);
        frame.getContentPane().add(waitTimeTxtField);

        JLabel timeLimitLabel = new JLabel("Time limit: ");
        timeLimitLabel.setBounds(987, 102, 80, 16);
        frame.getContentPane().add(timeLimitLabel);

        timeLimitTxtField = new JTextField();
        timeLimitTxtField.setText("30");
        timeLimitTxtField.setColumns(10);
        timeLimitTxtField.setBounds(1064, 101, 32, 19);
        setInformations();
        frame.getContentPane().add(timeLimitTxtField);
    }

    /**
     * Seteaza timpul curent la care se afla simularea
     *
     * @param time Integer, momentul de timp la care se afla simularea
     * @param msg  String, mesajul care va fi afisat
     */
    public void setTimer(int time, String msg) {
        timerTxt.setText("");
        timerTxt.setText(" Time: " + time + "   " + msg);
    }

    /**
     * Seteaza mesajul care apare pentru fiecare client (descrie starea clientului)
     *
     * @param tArrival Integer, timpul ramas pana cand va ajunge la coada
     * @param tService Integer, timpul necesar ca clientul sa fie servit
     * @param id       Integer, id-ul clientului
     */
    public void setClientTxt(int tArrival, int tService, int id) {
        txtAreas.get(id - 1).setText("client " + id + "\r\nT arrival\r\n   " + tArrival + "\r\nT processing\r\n   " + tService);
    }

    /**
     * Seteaza mesajul pentru coloana cu cozile cu numar par
     * @param s String, mesajul care va fi afisat
     */
    public void setQueueEvenTxt(String s) {
        queueEvenTxt.setText(s);
    }
    /**
     * Seteaza mesajul pentru coloana cu cozile cu numar impar
     * @param s String, mesajul care va fi afisat
     */
    public void setQueueOddTxt(String s) {
        queueOddTxt.setText(s);
    }

    /**
     * Afiseaza datele cu care a fost pornita simularea
     */
    private void setInformations() {
        waitTimeTxtField.setText(StartUp.waitTime + "");
        timeLimitTxtField.setText(StartUp.timeLimit + "");
        clientsTxtField.setText(StartUp.numberOfClients + "");
        queuesTxtField.setText(StartUp.numberOfServers + "");
        minArrivalTimeTxtField.setText(StartUp.minArrivalTime + "");
        maxArrivalTimeTxtField.setText(StartUp.maxArrivalTime + "");
        minProcessingTimeTxtField.setText(StartUp.minProcessingTime + "");
        maxProcessingTimeTxtField.setText(StartUp.maxProcessingTime + "");
        if (StartUp.selectionPolicy.equals(SelectionPolicy.SHORTEST_TIME))
            strategyCombo.setSelectedIndex(0);
        else strategyCombo.setSelectedIndex(1);
    }
}
