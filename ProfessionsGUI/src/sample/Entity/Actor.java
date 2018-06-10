package sample.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Actor extends Person {
    private boolean atLeastOneOscar;
    private double netWorth; // TODO maybe BigDecimal would be a better type of data
    private Map<String, LocalDate> movieYear;

    public Actor(String name, String surname, String EGN) {
        super(name, surname, EGN);
        this.atLeastOneOscar = false;
        this.netWorth = 0.0;
        this.movieYear = new LinkedHashMap<>();
    }

    public Actor(String name, String surname, String EGN, boolean atLeastOneOscar, double netWorth, Map<String, LocalDate> movieYear) {
        super(name, surname, EGN); // TODO what's going on here?
        this.atLeastOneOscar = atLeastOneOscar;
        this.netWorth = netWorth;
        this.movieYear = movieYear;
    }

    public boolean getAtLeastOneOscar() {
        return atLeastOneOscar;
    }

    public void setAtLeastOneOscar(boolean atLeastOneOscar) {
        this.atLeastOneOscar = atLeastOneOscar;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public Map<String, LocalDate> getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Map<String, LocalDate> movieYear) {
        this.movieYear = movieYear;
    }

    //CONSTRUCTORS, GETTERS AND SETTERS END_____________________________________________________________________________

    public String getProfession() {
        return "Actor";
    }

    public String getMoviesDatesAsString() {
        StringBuilder sb = new StringBuilder();

        Iterator iterator = this.movieYear.keySet().iterator();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (iterator.hasNext()) {
            String currentMovie = iterator.next().toString();
            String appendThis = String.format("Movie title: %s | Date released: %s%n", currentMovie, dateFormat.format(getMovieYear().get(currentMovie)));
            sb.append(appendThis);
        }

        return sb.toString();
    }

    public String getAllInformation() { // could be and override to the ".toString" method
        StringBuilder sb = new StringBuilder();

        sb.append(getPersonalInformationAsString());

        sb.append("Net worth: " + getNetWorth() + "\n");

        sb.append("Has at least one oscar: " + this.atLeastOneOscar + "\n");

        sb.append(getMoviesDatesAsString());

        return sb.toString();
    }

    public void addMovieDate(String movieName, String date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, dateFormat);
        this.movieYear.put(movieName, localDate);
    }

    public void saveInformation(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(filePath);
        PrintStream fileWriter = new PrintStream(file, "UTF-8");
        fileWriter.println(getAllInformation());
        fileWriter.close();
    }

}
