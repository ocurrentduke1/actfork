import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import javax.swing.*;

public class interfaz extends JFrame{
    mergesort mergesort = new mergesort();
    ForkJoinPool forkjoin = new ForkJoinPool(5);
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Random random;
    int tam = 0;
    int []arr;
    int []arrfork;
    int []arrexecuter;

    
    public interfaz(){
        this.setBounds(150, 0, 1000, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarcomponentes();
        setLayout(null);
        this.random = new Random();
    }
    private void iniciarcomponentes(){
        JLabel lbsecuencial = new JLabel("tiempo secuencial");
        lbsecuencial.setBounds(200, 300, 200, 20);
        JLabel lbfork = new JLabel("tiempo forkjoin");
        lbfork.setBounds(450, 300, 200, 20);
        JLabel lbexecuter = new JLabel("tiempo");
        lbexecuter.setBounds(700, 300, 200, 20);
        JButton btngenerar = new JButton("generar");
        btngenerar.setBounds(150, 650, 100, 20);
        JButton btnacomodar = new JButton("acomodar");
        btnacomodar.setBounds(300, 650, 100, 20);
        JButton btnfork = new JButton("fork/join");
        btnfork.setBounds(600, 650, 100, 20);
        JButton btnexecuter = new JButton("executer");
        btnexecuter.setBounds(750, 650, 100, 20);
        JButton btnlimpiar = new JButton("limpiar");
        btnlimpiar.setBounds(450, 650, 100, 20);
        JTextArea area1 = new JTextArea("");
        area1.setLineWrap(true);
        area1.setBounds(45, 30, 900, 200);
        area1.setBackground(Color.LIGHT_GRAY);
        JTextArea area2 = new JTextArea("");
        area2.setBounds(45, 400, 900, 200);
        area2.setBackground(Color.LIGHT_GRAY);
        area2.setLineWrap(true);
        JScrollPane scroll1 = new JScrollPane(area1);
        scroll1.setBounds(25,30,900,200);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane scroll2 = new JScrollPane(area2);
        scroll2.setBounds(25,400,900,200);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(lbsecuencial);
        this.getContentPane().add(lbfork);
        this.getContentPane().add(lbexecuter);
        this.getContentPane().add(btngenerar);
        this.getContentPane().add(btnacomodar);
        this.getContentPane().add(btnfork);
        this.getContentPane().add(btnlimpiar);
        this.getContentPane().add(btnexecuter);
        this.getContentPane().add(scroll2);
        this.getContentPane().add(scroll1);

        ActionListener generar = new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                tam = Integer.parseInt(JOptionPane.showInputDialog("ingrese el tamaño del array"));
                arr = new int[tam];
                arrfork = new int[tam];
                arrexecuter = new int[tam];
                for(int i = 0; i < tam; i++){
                    arr[i] = random.nextInt(200)+1;
                    arrfork[i] = arr[i];
                    arrexecuter[i] = arr[i];
                }

                    area1.setText(Arrays.toString(arr));
                
            }
        };
        btngenerar.addActionListener(generar);

        ActionListener organizar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                long inicio = System.nanoTime();
                mergesort.sort(arr,0,arr.length-1);
                long fin = System.nanoTime();
                area2.setText(Arrays.toString(arr));
                long total = (fin - inicio)/1000;
                lbsecuencial.setText("tiempo secuencial: "+ Integer.toString((int) total)+"ms");
            }
            
        };
        btnacomodar.addActionListener(organizar);

        ActionListener fork = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long inicio1 = System.nanoTime();
                forkjoin.invoke(new forkjoin(arrfork,0, arrfork.length-1));
                long fin1 = System.nanoTime();
                area2.setText(Arrays.toString(arrfork));
                long total1 = (fin1 - inicio1)/1000;
                lbfork.setText("tiempo forkjoin: "+ Integer.toString((int) total1)+"ms");
            }
            
        };
        btnfork.addActionListener(fork);

        ActionListener exe = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long inicio2 = System.nanoTime();
                Future<int[]> leftResult = executor.submit(new Executor(arr, l, (l + r) / 2));
                Future<int[]> rightResult = executor.submit(new Executor(arr, (l + r) / 2 + 1, r));

                long fin2 = System.nanoTime();
                area2.setText(Arrays.toString(arrexecuter));
                
                long total2 = (fin2 - inicio2)/1000;
                lbexecuter.setText("tiempo forkjoin: "+ Integer.toString((int) total2)+"ms");
            }
            
        };
        btnexecuter.addActionListener(exe);

        ActionListener limpiar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                area1.setText("");
                area2.setText("");
                arr = null;
                arrfork = null;
                arrexecuter = null;
                
            }
            
        };
        btnlimpiar.addActionListener(limpiar);
         
    }

}