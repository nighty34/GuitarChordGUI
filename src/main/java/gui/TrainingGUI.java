package gui;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;
import model.Chord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TrainingGUI extends JFrame {

    private DefaultListModel<Chord> model;

    private Chord activeChord;

    private BlockingQueue<Chord> chordQueue;

    private JLabel chordIndicator = new JLabel("TEMP_INDICATOR");
    private JLabel instructions = new JLabel("Bitte gib den Akkord ein!");

    private JPanel chordPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    private JButton nextButton = new JButton("Next");

    private ChordPane chordPane = new ChordPane();


    TrainingGUI(DefaultListModel<Chord> model){
        super("Training");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(true);

        setup();

        setVisible(true);
    }



    private void setup(){

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chordPane, BorderLayout.WEST);
        mainPanel.add(chordIndicator, BorderLayout.NORTH);
        mainPanel.add(instructions, BorderLayout.EAST);
        mainPanel.add(nextButton, BorderLayout.SOUTH);

        nextButton.addActionListener(new FinishListener());

        add(mainPanel);
        pack();



        List<Chord> shuffleList = new ArrayList<>();
        for(int i = 0; i<model.getSize(); i++){
            shuffleList.add(model.getElementAt(i));
        }
        Collections.shuffle(shuffleList);

        chordQueue = new LinkedBlockingDeque<>(shuffleList);

        setUpNext();
    }

    private Chord getNextChord(){
        return chordQueue.poll();
    }

    private boolean setUpNext(){
        activeChord = getNextChord();
        if(activeChord == null){
            return false;
        }
        chordIndicator.setText(activeChord.getName());

        return true;
    }

    public class FinishListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(validatePane()){
                System.out.println("TRUE");
                if(!setUpNext()){
                    System.out.println("CLOSE");
                }
            }else{
                System.out.println("FALSE");
            }
        }
    }

    private boolean validatePane(){
        Integer[][] activeStrings = activeChord.getStrings();
        for(int i = 0; i < activeStrings.length; i++){
            for(int y = 0; y < activeStrings[i].length; y++){
                if(!(activeStrings[i][y] == chordPane.getStringsAsIneteger()[i][y])){
                    return false;
                }
            }
        }

        return true;
    }







}
