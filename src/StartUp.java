import java.awt.EventQueue;

import javax.swing.*;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reprezinta fereastra principala in care se adauga datele pentru pornirea simularii
 */
public class StartUp {
    public static int timeLimit;
    public static int maxProcessingTime;
    public static int minProcessingTime;
    public static int maxArrivalTime;
    public static int minArrivalTime;
    public static int numberOfServers;
    public static int numberOfClients;
    public static SelectionPolicy selectionPolicy;
    public static double waitTime;

    public static void main(String[] args) {
        new StartUp();
    }

    public StartUp() {
        initialize();
    }

    /**
     * Creeaza fereastra
     */
    private void initialize() {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.activeCaption);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Time limit");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel.setBounds(37, 37, 88, 23);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblProcessingTime = new JLabel("Processing time");
        lblProcessingTime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblProcessingTime.setBounds(37, 70, 127, 23);
        frame.getContentPane().add(lblProcessingTime);

        JLabel lblArrivalTime = new JLabel("Arrival time");
        lblArrivalTime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblArrivalTime.setBounds(37, 103, 88, 23);
        frame.getContentPane().add(lblArrivalTime);

        JLabel lblClientsNumber = new JLabel("Clients number");
        lblClientsNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblClientsNumber.setBounds(37, 136, 101, 23);
        frame.getContentPane().add(lblClientsNumber);

        JLabel lblQueuesNumber = new JLabel("Queues number");
        lblQueuesNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblQueuesNumber.setBounds(37, 169, 101, 23);
        frame.getContentPane().add(lblQueuesNumber);

        JLabel lblQueuesNumber_1 = new JLabel("Selection policy");
        lblQueuesNumber_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblQueuesNumber_1.setBounds(37, 202, 101, 23);
        frame.getContentPane().add(lblQueuesNumber_1);

        JComboBox policyBox = new JComboBox();
        policyBox.setModel(new DefaultComboBoxModel(new String[]{"Shortest waiting time", "Shortest queue"}));
        policyBox.setBounds(159, 204, 176, 21);
        frame.getContentPane().add(policyBox);

        JButton startBtn = new JButton("Start");
        startBtn.setBounds(37, 235, 85, 21);
        frame.getContentPane().add(startBtn);

        JTextField timeLimitTxt = new JTextField();
        timeLimitTxt.setBounds(159, 40, 70, 19);
        frame.getContentPane().add(timeLimitTxt);
        timeLimitTxt.setColumns(10);

        JTextField minProcessingTimeTxt = new JTextField();
        minProcessingTimeTxt.setColumns(10);
        minProcessingTimeTxt.setBounds(159, 73, 48, 19);
        frame.getContentPane().add(minProcessingTimeTxt);

        JTextField clientsTxt = new JTextField();
        clientsTxt.setColumns(10);
        clientsTxt.setBounds(159, 136, 70, 19);
        frame.getContentPane().add(clientsTxt);

        JTextField queuesTxt = new JTextField();
        queuesTxt.setColumns(10);
        queuesTxt.setBounds(159, 172, 70, 19);
        frame.getContentPane().add(queuesTxt);

        JTextField maxProcessingTimeTxt = new JTextField();
        maxProcessingTimeTxt.setColumns(10);
        maxProcessingTimeTxt.setBounds(233, 73, 48, 19);
        frame.getContentPane().add(maxProcessingTimeTxt);

        JTextField minArrivalTimeTxt = new JTextField();
        minArrivalTimeTxt.setColumns(10);
        minArrivalTimeTxt.setBounds(159, 103, 48, 19);
        frame.getContentPane().add(minArrivalTimeTxt);

        JTextField maxArrivalTimeTxt = new JTextField();
        maxArrivalTimeTxt.setColumns(10);
        maxArrivalTimeTxt.setBounds(233, 106, 48, 19);
        frame.getContentPane().add(maxArrivalTimeTxt);

        JLabel lblNewLabel_1 = new JLabel("-");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(217, 109, 23, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("-");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1_1.setBounds(217, 76, 23, 13);
        frame.getContentPane().add(lblNewLabel_1_1);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timeLimit = Integer.parseInt(timeLimitTxt.getText());
                    maxArrivalTime = Integer.parseInt(maxArrivalTimeTxt.getText());
                    minArrivalTime = Integer.parseInt(minArrivalTimeTxt.getText());
                    maxProcessingTime = Integer.parseInt(maxProcessingTimeTxt.getText());
                    minProcessingTime = Integer.parseInt(minProcessingTimeTxt.getText());
                    numberOfClients = Integer.parseInt(clientsTxt.getText());
                    numberOfServers = Integer.parseInt(queuesTxt.getText());
                    waitTime = 1;
                    if (policyBox.getSelectedItem().equals("Shortest waiting time"))
                        selectionPolicy = SelectionPolicy.SHORTEST_TIME;
                    else selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
                    SimulationManager gen = new SimulationManager();
                    Thread t = new Thread(gen);
                    t.start();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(startBtn,"Toate valorile trebuie sa fie intregi!");
                }
            }
        });
    }
}
