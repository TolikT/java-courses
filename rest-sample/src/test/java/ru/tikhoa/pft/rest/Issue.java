package ru.tikhoa.pft.rest;


public class Issue {
    private int id;
    private String subject;
    private String description;

    public String getStatus() {
        return status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    private String status;

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != issue.id) return false;
        if (subject != null ? !subject.equals(issue.subject) : issue.subject != null) return false;
        if (description != null ? !description.equals(issue.description) : issue.description != null) return false;
        return status != null ? status.equals(issue.status) : issue.status == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

}
