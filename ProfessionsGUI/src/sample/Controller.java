package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Entity.Actor;
import sample.Entity.Person;
import sample.Entity.Programmer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO add to the GUI a way of putting data on the additional fields for the programmer and actor classes

public class Controller implements Initializable {
    public TextField personName;
    public TextField personSurname;
    public TextField personEgn;
    public TextField programmerName;
    public TextField programmerSurname;
    public TextField programmerEgn;
    public TextField actorName;
    public TextField actorSurname;
    public TextField actorEgn;
    public ComboBox chooseSomeoneCb;
    public HashMap<String, Person> namePerson;
    public ObservableList<String> ol;
    public TextArea textArea;
    public Map.Entry<String, Person> lastPerson;


    public void initialize(URL url, ResourceBundle rb) {
        this.namePerson = new LinkedHashMap<>();
        this.ol = FXCollections.observableArrayList();
    }


    public void registerPersonBtn(ActionEvent actionEvent) {
        Person person = new Person(this.personName.getText(), this.personSurname.getText(), this.personEgn.getText());
        this.namePerson.put(this.personName.getText(), person);
        ol.add(this.personName.getText());
        this.chooseSomeoneCb.setItems(ol);
    }

    public void registerProgrammerBtn(ActionEvent actionEvent) {
        Programmer programmer = new Programmer(this.programmerName.getText(), this.programmerSurname.getText(), this.programmerEgn.getText());
        this.namePerson.put(this.programmerName.getText(), programmer);
        ol.add(this.programmerName.getText());
        this.chooseSomeoneCb.setItems(ol);
    }

    public void registerActorBtn(ActionEvent actionEvent) {
        Actor actor = new Actor(this.actorName.getText(), this.actorSurname.getText(), this.actorEgn.getText());
        this.namePerson.put(this.actorName.getText(), actor);
        ol.add(this.actorName.getText());
        this.chooseSomeoneCb.setItems(ol);
    }

    public void visualizeInfoBtn(ActionEvent actionEvent) {
        String name = this.chooseSomeoneCb.getValue().toString();
        textArea.setText(namePerson.get(name).getAllInformation());

        for (Map.Entry<String, Person> currentPersonKvp : this.namePerson.entrySet()) {
            if (currentPersonKvp.getKey().equals(name)) {
                this.lastPerson = currentPersonKvp;
                break;
            }
        }

    }

    public void saveTextAreaToFile(ActionEvent actionEvent) throws FileNotFoundException, UnsupportedEncodingException {
        String lastPersonsProfessionInTextArea = this.lastPerson.getValue().getProfession();

        String filePathToSaveInformation = "src\\sample\\TextFiles\\";

        switch (lastPersonsProfessionInTextArea) {
            case "Jobless":
                filePathToSaveInformation += "Person.txt";
                break;

            case "Programmer":
                filePathToSaveInformation += "Programmer.txt";
                break;

            case "Actor":
                filePathToSaveInformation += "Actor.txt";
                break;
        }

        lastPerson.getValue().saveToFile(filePathToSaveInformation);
    }
}
