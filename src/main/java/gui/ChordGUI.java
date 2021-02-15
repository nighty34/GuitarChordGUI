package gui;

import model.Chord;
import model.Chordmodel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChordGUI extends JFrame {

    private JPanel buttonPannel = new JPanel();

    private JButton addChord = new JButton("Add Chord");
    private JButton removeChord = new JButton("Remove Chord");
    private JButton editChord = new JButton("Edit Chord");

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

        buttonPannel.setLayout(new GridLayout(7, 1));
        buttonPannel.add(addChord);
        buttonPannel.add(removeChord);
        buttonPannel.add(editChord);

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
