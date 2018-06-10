package sample.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Programmer extends Person {
    private Set<String> programmingLanguages;
    private Map<String, String> websiteUserName;  // first is website, then is the username, because the username could be used in multiple websites

    public Programmer(String name, String surname, String EGN) { // constructor cannot be inherited
        super(name, surname, EGN);
        this.programmingLanguages = new LinkedHashSet<>();
        this.websiteUserName = new LinkedHashMap<>();
    }

    public Programmer(String name, String surname, String EGN, Set<String> programmingLanguages, Map<String, String> websiteUserName) {
        super(name, surname, EGN);
        this.programmingLanguages = programmingLanguages;
        this.websiteUserName = websiteUserName;
    }

    public Set<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(Set<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public Map<String, String> getWebsiteUserName() {
        return websiteUserName;
    }

    public void setWebsiteUserName(Map<String, String> websiteUserName) {
        this.websiteUserName = websiteUserName;
    }

    //GETTERS AND SETTERS END___________________________________________________________________________________________

    public String getProfession() {
        return "Programmer";
    }

    public String getProgrammingLanguagesAsString() {
        Iterator iterator = getProgrammingLanguages().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append("\n");
        return sb.toString();
    }

    public String getWebsiteUserNameAsString() {
        Iterator iterator = getWebsiteUserName().keySet().iterator();

        StringBuilder sb = new StringBuilder();

        while (iterator.hasNext()) {
            String currentWebsite = iterator.next().toString();
            sb.append(String.format("Website: %s | User name: %s", currentWebsite, getWebsiteUserName().get(currentWebsite)));
            sb.append("\n");
        }

        return sb.toString();
    }

    public String getAllInformation() {
        StringBuilder sb = new StringBuilder();

        sb.append("Personal information: \n");
        sb.append(getPersonalInformationAsString());

        sb.append("Programming languages: \n");
        sb.append(getProgrammingLanguagesAsString());

        sb.append("Portfolio: \n");
        sb.append(getWebsiteUserNameAsString());

        return sb.toString();
    }

    public void saveToFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(filePath);
        PrintStream fileWriter = new PrintStream(filePath, "UTF-8");
        fileWriter.println(getAllInformation());
        fileWriter.close();
    }

}
