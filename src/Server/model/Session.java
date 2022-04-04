package Server.model;

public class Session {

    private char letter;
    private String id1;
    private String id2;
    private String name1;
    private String name2;
    private String animal1;
    private String animal2;
    private String country1;
    private String country2;
    private String thing1;
    private String thing2;
    private int[] points1;
    private int[] points2;

    public Session(char letter){
        this.letter = letter;
        this.points1 = new int[4];
        this.points2 = new int[4];
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getAnimal1() {
        return animal1;
    }

    public void setAnimal1(String animal1) {
        this.animal1 = animal1;
    }

    public String getAnimal2() {
        return animal2;
    }

    public void setAnimal2(String animal2) {
        this.animal2 = animal2;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public String getThing1() {
        return thing1;
    }

    public void setThing1(String thing1) {
        this.thing1 = thing1;
    }

    public String getThing2() {
        return thing2;
    }

    public void setThing2(String thing2) {
        this.thing2 = thing2;
    }

    public int[] getPoints1() {
        return points1;
    }

    public void setPoints1(int[] points1) {
        this.points1 = points1;
    }

    public int[] getPoints2() {
        return points2;
    }

    public void setPoints2(int[] points2) {
        this.points2 = points2;
    }
}
