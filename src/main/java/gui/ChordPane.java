package gui;

import javax.swing.*;
import java.awt.*;

public class ChordPane extends JPanel {

    private String[][] strings = new String[5][6];
    private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);


    private JLabel rowFillerA = new JLabel("+ - + - + - + - + - + - +");
    public JTextField a0 = new JTextField(" ");
    public JTextField a1 = new JTextField(" ");
    public JTextField a2 = new JTextField(" ");
    public JTextField a3 = new JTextField(" ");
    public JTextField a4 = new JTextField(" ");
    public JTextField a5 = new JTextField(" ");
    public JPanel aRow = new JPanel();
    public JPanel a = new JPanel();

    private JLabel rowFillerB = new JLabel("+ - + - + - + - + - + - +");
    public JTextField b0 = new JTextField(" ");
    public JTextField b1 = new JTextField(" ");
    public JTextField b2 = new JTextField(" ");
    public JTextField b3 = new JTextField(" ");
    public JTextField b4 = new JTextField(" ");
    public JTextField b5 = new JTextField(" ");
    public JPanel bRow = new JPanel();
    public JPanel b = new JPanel();

    private JLabel rowFillerC = new JLabel("+ - + - + - + - + - + - +");
    public JTextField c0 = new JTextField(" ");
    public JTextField c1 = new JTextField(" ");
    public JTextField c2 = new JTextField(" ");
    public JTextField c3 = new JTextField(" ");
    public JTextField c4 = new JTextField(" ");
    public JTextField c5 = new JTextField(" ");
    public JPanel cRow = new JPanel();
    public JPanel c = new JPanel();

    private JLabel rowFillerD = new JLabel("+ - + - + - + - + - + - +");
    public JTextField d0 = new JTextField(" ");
    public JTextField d1 = new JTextField(" ");
    public JTextField d2 = new JTextField(" ");
    public JTextField d3 = new JTextField(" ");
    public JTextField d4 = new JTextField(" ");
    public JTextField d5 = new JTextField(" ");
    public JPanel dRow = new JPanel();
    public JPanel d = new JPanel();


    private JLabel rowFillerE = new JLabel("+ - + - + - + - + - + - +");
    public JTextField e0 = new JTextField(" ");
    public JTextField e1 = new JTextField(" ");
    public JTextField e2 = new JTextField(" ");
    public JTextField e3 = new JTextField(" ");
    public JTextField e4 = new JTextField(" ");
    public JTextField e5 = new JTextField(" ");
    public JPanel eRow = new JPanel();
    public JPanel e = new JPanel();

    public JPanel fRow = new JPanel();
    private JLabel rowFillerF = new JLabel("+ - + - + - + - + - + - +");


    /**
     * Constructor
     */
    ChordPane(){
        setLayout(new GridLayout(11, 1));
        rowFillerA.setFont(font);
        rowFillerB.setFont(font);
        rowFillerC.setFont(font);
        rowFillerD.setFont(font);
        rowFillerE.setFont(font);
        rowFillerF.setFont(font);


        aRow.add(rowFillerA);
        bRow.add(rowFillerB);
        cRow.add(rowFillerC);
        dRow.add(rowFillerD);
        eRow.add(rowFillerE);
        fRow.add(rowFillerF);


        add(aRow);
        add(addRow("a"));
        add(bRow);
        add(addRow("b"));
        add(cRow);
        add(addRow("c"));
        add(dRow);
        add(addRow("d"));
        add(eRow);
        add(addRow("e"));
        add(fRow);

        setVisible(true);
    }

    /**
     * swing setup
     * @param fret
     * @return
     */
    private JPanel addRow(String fret){
        try {
            JPanel label = (JPanel) getClass().getField(fret).get(this);
            for(int i = 0; i<=5; i++){
                    String value = fret + i;
                    label.add(new JLabel("| "));
                    JTextField textfield = (JTextField) getClass().getField(value).get(this);
                    textfield.setFont(font);
                    label.add(textfield);

            }
            label.add(new JLabel(" |"));
            label.setFont(font);
            return label;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns content of the form
     * @return
     */
    public String[][] getStrings() {
        try {
            for(int i = 0; i<=4; i++) {
                String fretname;
                switch (i) {
                    case 0:
                        fretname = "a";
                        break;
                    case 1:
                        fretname = "b";
                        break;
                    case 2:
                        fretname = "c";
                        break;
                    case 3:
                        fretname = "d";
                        break;
                    case 4:
                        fretname = "e";
                        break;
                    case 5:
                        fretname = "f";
                        break;
                    default:
                        fretname = "a";
                        break;

                }
                for (int y = 0; y<=5; y++) {
                    String accessField = fretname + y;
                    JTextField textfield = (JTextField) getClass().getField(accessField).get(this);
                    strings[i][y] = textfield.getText();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return strings;
    }




    /**
     * Method for clearing all fields
     */
    public void clearStrings(){
        clearRow("a");
        clearRow("b");
        clearRow("c");
        clearRow("d");
        clearRow("e");
    }

    /**
     * Method for clearing a specific row
     * @param fret
     */
    private void clearRow(String fret){
        try {
            for (int i = 0; i <= 5; i++) {
                String value = fret + i;
                JTextField textfield = (JTextField) getClass().getField(value).get(this);
                textfield.setText("");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Method for filling the form (for editing)
     * @param strings
     */
    public void writeStrings(Integer[][] strings) {
        try {
            for(int i = 0; i<=4; i++) {
                String fretname;
                switch (i) {
                    case 0:
                        fretname = "a";
                        break;
                    case 1:
                        fretname = "b";
                        break;
                    case 2:
                        fretname = "c";
                        break;
                    case 3:
                        fretname = "d";
                        break;
                    case 4:
                        fretname = "e";
                        break;
                    case 5:
                        fretname = "f";
                        break;
                    default:
                        fretname = "a";
                        break;
                }
                for (int y = 0; y<=5; y++) {
                    String accessField = fretname + y;
                    JTextField textfield = (JTextField) getClass().getField(accessField).get(this);
                    String output = strings[i][y] + "";
                    if(output.equals("null") || output == null){
                        output = " ";
                    }
                    textfield.setText(output);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Integer[][] getStringsAsIneteger(){
        String[][] stringStrings = getStrings();
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
