package sample.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Person {
    protected String name;
    protected String surname;
    protected String EGN;

    public Person(String name, String surname, String EGN) {
        this.name = name;
        this.surname = surname;
        this.EGN = EGN;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEGN() {
        return EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public String getProfession() {
        return "Jobless";
    }

    public void printPersonalInformation(){
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ");
        sb.append(getName());
        sb.append("\n");

        sb.append("Surname: ");
        sb.append(getSurname());
        sb.append("\n");

        sb.append("EGN: ");
        sb.append(getEGN());
        sb.append("\n");

        sb.append("Profession: ");
        sb.append(getProfession());
        sb.append("\n");

        System.out.print(sb.toString());
    }

    public String getPersonalInformationAsString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ");
        sb.append(getName());
        sb.append("\n");

        sb.append("Surname: ");
        sb.append(getSurname());
        sb.append("\n");

        sb.append("EGN: ");
        sb.append(getEGN());
        sb.append("\n");

        sb.append("Profession: ");
        sb.append(getProfession());
        sb.append("\n");

        return sb.toString();
    }

    public String getAllInformation() {
        return getPersonalInformationAsString();
    }

    public void saveToFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(filePath);
        PrintStream fileWriter = new PrintStream(filePath, "UTF-8");
        fileWriter.println(getAllInformation());
        fileWriter.close();
    }

}
