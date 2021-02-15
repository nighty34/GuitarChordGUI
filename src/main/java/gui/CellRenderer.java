package gui;

import model.Chord;

import javax.swing.*;
import java.awt.*;


public class CellRenderer  extends JPanel implements  ListCellRenderer<Chord> {


    private JLabel name = new JLabel();
    private JLabel fret = new JLabel();
    private JLabel category = new JLabel();
    private JPanel panel = new JPanel();
    private JTextArea chord = new JTextArea();


    /**
     * Cellrenderer Setup
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Chord> list,
                                                  Chord value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        if(isSelected) {
            chord.setBackground(new Color(200, 200, 200));
            panel.setBackground(new Color(200, 200, 200));
        }else{
            chord.setBackground(new Color(250, 250, 250));
            panel.setBackground(new Color(250, 250, 250));
        }

        chord.setLineWrap(true);
        chord.setText(visualChord(value.getStrings()));
        chord.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));


        fret.setText("Fret " + value.getFret());
        name.setText(value.getName());
        category.setText(value.getCategory());

        setLayout(new BorderLayout());

        panel.setLayout(new BorderLayout());
        panel.add(fret, BorderLayout.NORTH);
        panel.add(name, BorderLayout.CENTER);
        panel.add(category, BorderLayout.SOUTH);

        add(panel, BorderLayout.WEST);
        add(chord, BorderLayout.CENTER);
        add(new JSeparator(), BorderLayout.NORTH);

        setVisible(true);
        return this;
    }

    /**
     * creates a string from a fret
     * @param strings
     * @return
     */
    private String visualChord(Integer[][] strings){
        String output = "+-+-+-+-+-+-+";
        for(int i = 0; strings.length>i; i++){
            output += "\n";
            for(int y = 0; strings[i].length>y; y++){
                if(strings[i][y]==null){
                    output += "| ";
                }else{
                    output += "|" + strings[i][y];
                }
            }
            output += "|\n+-+-+-+-+-+-+";
        }
        return output;
    }
}
