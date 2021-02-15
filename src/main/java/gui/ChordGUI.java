package gui;

import data.Datahandler;
import model.Chord;
import model.Chordmodel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChordGUI extends JFrame {

    private JPanel buttonPannel = new JPanel();

    private JButton addChord = new JButton("Add Chord");
    private JButton removeChord = new JButton("Remove Chord");
    private JButton editChord = new JButton("Edit Chord");
    private JButton trainChord = new JButton("Training");
    private JButton exportChords = new JButton("Export Chords");
    private JButton importChords = new JButton("Import Chords");

    private JScrollPane scrollPane;
    private JList<Chord> chordList = new JList<>();

    private DefaultListModel model;

    /**
     * Constructor
     * @param model
     */
    public ChordGUI(DefaultListModel<Chord> model){
        super("Chords");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.model = model;

        setup();

        pack();
        setVisible(true);
    }

    /**
     * Swing Setup
     */
    private void setup(){
        chordList.setModel(model);
        chordList.setCellRenderer(new CellRenderer());

        scrollPane = new JScrollPane(chordList);

        addChord.addActionListener(new AddListener());
        removeChord.addActionListener(new RemoveListener());
        editChord.addActionListener(new EditListener());
        trainChord.addActionListener(new TrainListener());
        exportChords.addActionListener(new ExportListener());
        importChords.addActionListener(new ImportListener());

        buttonPannel.setLayout(new GridLayout(7, 1));
        buttonPannel.add(addChord);
        buttonPannel.add(removeChord);
        buttonPannel.add(editChord);
        buttonPannel.add(trainChord);
        buttonPannel.add(exportChords);
        buttonPannel.add(importChords);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.WEST);
        getContentPane().add(buttonPannel, BorderLayout.CENTER);

        chordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Listener for the "Add Chord" button
     */
    public class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddChordGUI(model);
        }
    }

    /**
     * Listener for exporting Chords
     */
    public class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Datahandler.getInstance().writeFile(new File("lol.json"), model);
        }
    }

    /**
     * Listener for import Chords
     */
    public class ImportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Datahandler.getInstance().readFile(new File("lol.json"));
        }
    }

    /**
     * Listener for opening Trainer
     */
    public class TrainListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TrainingGUI(model);
        }
    }

    /**
     * Listener for the "Remove Chord" button
     */
    public class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
             if(!chordList.isSelectionEmpty()) {
                 model.remove(chordList.getSelectedIndex());
             }else{
                 JOptionPane.showMessageDialog(null,
                         "Please select a chord first",
                         "Warning",
                         JOptionPane.WARNING_MESSAGE);
             }
        }
    }

    /**
     * Listener for the "Edit Chord" button
     */
    public class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!chordList.isSelectionEmpty()) {
                new AddChordGUI(model, chordList.getSelectedValue());
            }else{
                JOptionPane.showMessageDialog(null,
                    "Please select a chord first",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        }
    }

    public static void main(String[] args) {
        Chordmodel model = new Chordmodel();
        Integer[][] strings = new Integer[5][6];
        strings[1][1] = 2;
        strings[1][2] = 1;
        new ChordGUI(model);
        Chord chord = new Chord("E", 0, "Mol");
        chord.setStrings(strings);
        model.addElement(chord);
    }




}
