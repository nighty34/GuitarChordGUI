package model;

import javax.swing.*;
import java.util.Vector;

public class Chordmodel extends DefaultListModel<Chord> {
    private Vector<Chord> chords; //FIXME: Init

    /**
     * Constructor
     */
    public Chordmodel(){
        chords = new Vector<>();
    }


    /**
     * Returns size of chords
     * @return
     */
    @Override
    public int getSize() {
        return chords.size();
    }

    /**
     * Adds a Element to chords
     * @param chord
     */
    @Override
    public void addElement(Chord chord){
        chords.add(chord);
        fireContentsChanged(this, 0, getSize());
    }


    /**
     * Removes element at index
     * @param index
     * @return
     */
    @Override
    public Chord remove(int index) {
        Chord chord = chords.remove(index);
        fireContentsChanged(this, 0, getSize());
        return chord;

    }

    /**
     * Removes element from chords from object
     * @param chord
     * @return
     */
    @Override
    public boolean removeElement(Object chord) {
        return chords.removeElement(chord);

    }

    /**
     * Returns element at certain index
     * @param index
     * @return
     */
    @Override
    public Chord getElementAt(int index) {
        return chords.get(index);
    }

    /**
     * toString overrides was changed to update model
     * @return
     */
    @Override
    public String toString() {
        fireContentsChanged(this, 0, getSize());
        return chords.toString();
    }

}
