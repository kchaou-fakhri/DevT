package Model;

public class Job {

                private String id;
                private String title;
                private String description;
                private String location;
                private String filde;
                private String budget;
                 private String mail;

    public Job( String title, String description, String location, String filde, String budget) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.filde = filde;
        this.budget = budget;
    }

    public Job( String title, String description, String location, String filde, String budget, String mail) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.filde = filde;
        this.budget = budget;
        this.mail = mail;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFilde() {
        return filde;
    }

    public void setFilde(String filde) {
        this.filde = filde;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
