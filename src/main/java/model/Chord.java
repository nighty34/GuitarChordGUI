package model;

public class Chord {


    private Integer[][] strings;
    private String name;
    private String category;
    private int fret;

    /**
     * Constructor
     * @param name
     * @param fret
     * @param category
     * @param strings
     */
    public Chord(String name, int fret, String category, Integer[][] strings){
        this.name = name;
        this.fret = fret;
        this.category = category;
        this.strings = strings;
    }

    /**
     * Constructor
     * @param name
     * @param fret
     * @param category
     */
    public Chord(String name, int fret, String category){
        this.name = name;
        this.fret = fret;
        this.category = category;
        this.strings = new Integer[5][6];
    }

    //Getter & Setter
    public void setFret(int fret) {
        this.fret = fret;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrings(Integer[][] strings) {
        this.strings = strings;
    }


    public String getName() {
        return name;
    }

    public int getFret() {
        return fret;
    }

    public Integer[][] getStrings() {
        return strings;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
