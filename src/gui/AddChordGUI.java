package gui;

import model.Chord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AddChordGUI extends JFrame {

    private Vector<String> caregories;

    private DefaultListModel<Chord> model;

    private Chord chord;

    private JPanel chordFormPanel = new JPanel();
    private JTextField fret = new JTextField();
    private JTextField name = new JTextField();
    private JComboBox<String> category;

    private ChordPane chordPane = new ChordPane();

    private JPanel fretFieldPanel = new JPanel();
    private JPanel nameFieldPanel = new JPanel();
    private JPanel categoryField = new JPanel();

    private JPanel fretPanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private JPanel categoryPanel = new JPanel();

    private JButton saveButton = new JButton("Save");
    private JButton cancelButton = new JButton("Cancel");
    private JButton clearButton = new JButton("Clear");

    private JPanel buttonPanel = new JPanel();
    private JPanel textPanel = new JPanel();
    private JPanel textPanelsec = new JPanel();

    private JLabel fretText = new JLabel("Fret");
    private JLabel nameText = new JLabel("Name");
    private JLabel catText = new JLabel("Category");

    /**
     * Constructor for adding
     * @param model
     */
    public AddChordGUI(DefaultListModel<Chord> model){
        super("Add Chord");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(true);

        setup();

        saveButton.addActionListener(new SaveListener());
        pack();
        setVisible(true);
    }

    /**
     * Constructor for editing
     * @param model
     * @param chord
     */
    public AddChordGUI(DefaultListModel<Chord> model, Chord chord){
        super("Edit Chord");
        this.model = model;
        this.chord = chord;
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(true);

        setup();

        name.setText(chord.getName());
        category.setSelectedIndex(caregories.indexOf(chord));
        fret.setText(chord.getFret() + "");
        chordPane.writeStrings(chord.getStrings());

        saveButton.addActionListener(new SaveToExistingListener());
        pack();
        setVisible(true);
    }

    /**
     * Java swing setup
     */
    private void setup(){
        categories();
        category = new JComboBox<>(caregories);

        fretFieldPanel.setLayout(new BorderLayout());
        fretFieldPanel.add(fret, BorderLayout.CENTER);

        nameFieldPanel.setLayout(new BorderLayout());
        nameFieldPanel.add(name, BorderLayout.NORTH);

        categoryField.setLayout(new BorderLayout());
        categoryField.add(category, BorderLayout.CENTER);

        fretPanel.setLayout(new BorderLayout());
        fretPanel.add(fretFieldPanel, BorderLayout.CENTER);
        fretPanel.add(fretText, BorderLayout.EAST);

        namePanel.setLayout(new BorderLayout());
        namePanel.add(nameFieldPanel, BorderLayout.CENTER);
        namePanel.add(nameText, BorderLayout.EAST);

        categoryPanel.setLayout(new BorderLayout());
        categoryPanel.add(categoryField, BorderLayout.CENTER);
        categoryPanel.add(catText, BorderLayout.EAST);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(fretPanel, BorderLayout.NORTH);
        textPanel.add(namePanel, BorderLayout.CENTER);
        textPanel.add(categoryPanel, BorderLayout.SOUTH);

        textPanelsec.setLayout(new BorderLayout());
        textPanelsec.add(textPanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(clearButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        chordFormPanel.setLayout(new BorderLayout());
        chordFormPanel.add(chordPane, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(textPanelsec, BorderLayout.WEST);
        getContentPane().add(chordFormPanel, BorderLayout.CENTER);


        clearButton.addActionListener(new ClearListener());
        cancelButton.addActionListener(new CancelListener());
    }

    /**
     * Adds all categories to dropdownmenu
     */
    private void categories(){
        caregories = new Vector<String>();
        caregories.add("Dur");
        caregories.add("Mol");
        caregories.add("Maj7");
        caregories.add("Andere");
    }


    /**
     * Listener for Createing a new chord in the model.
     */
    public class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String chordname = name.getText();
            String[][] chordinputs = chordPane.getStrings();
            try {
                Integer chordfret = Integer.parseInt(fret.getText());
                if (chordname.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a name",
                            "Invalid entry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Integer[][] strings = validateStrings(chordinputs);
                    if (strings == null) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid chord entry. Please use numbers between 1-4",
                                "Invalid entry",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        //FIXME: Values
                        model.addElement(new Chord(chordname, chordfret, (String) category.getSelectedItem(), strings));
                        dispose();

                    }
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,
                        "Please enter a  valid fret. (Example: 2)",
                        "Invalid entry",
                        JOptionPane.WARNING_MESSAGE);

            }
        }
    }

    /**
     * Modified save listener for editing.
     * Instead of creating a new chord, it saves the values into the existing chord.
     */
    public class SaveToExistingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String chordname = name.getText();
            String[][] chordinputs = chordPane.getStrings();
            try {
                Integer chordfret = Integer.parseInt(fret.getText());
                if (chordname.isEmpty() || chordname == null) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a name",
                            "Invalid entry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Integer[][] strings = validateStrings(chordinputs);
                    if (strings == null) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid chord entry. Please use numbers between 1-4",
                                "Invalid entry",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        //FIXME: Values
                        chord.setName(chordname);
                        chord.setFret(chordfret);
                        chord.setStrings(strings);

                        model.toString();
                        dispose();

                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Please enter a  valid fret. (Example: 2)",
                        "Invalid entry",
                        JOptionPane.WARNING_MESSAGE);

            }
        }
    }


    /**
     * Listener for "Cancel" button
     * closes this window
     */
    public class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    /**
     * Listener for "Clear" button
     * Resets the form
     */
    public class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            name.setText("");
            fret.setText("");
            category.setSelectedIndex(0);
            chordPane.clearStrings();
        }
    }

    /**
     * Method for validating the strings of the chord and converting it into Integer[][]
     * @param stringStrings
     * @return  strings as Integer[][] or null if is invalid
     */
    private Integer[][] validateStrings(String[][] stringStrings){
        Integer[][] output = new Integer[5][6];
        try {
            for (int i = 0; i < stringStrings.length; i++) {
                for (int y = 0; y < stringStrings[i].length; y++) {
                    String value = stringStrings[i][y];
                    if(!(value == null)) {
                        if (value.contains(" ")) {
                            value = value.replaceAll("\\s", "");
                        }
                        if ((!value.equals(""))) {
                            output[i][y] = Integer.parseInt(value);
                        }
                    }
                }
            }
            return output;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
