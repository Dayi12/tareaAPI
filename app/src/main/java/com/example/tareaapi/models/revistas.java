package com.example.tareaapi.models;

public class revistas {
    private String issues_id;
    private String volume;
    private String number;
    private String year;
    private String date_published;
    private String title;
    private String doi;
    private String cover;

    public String getIssues_id() {
        return issues_id;
    }

    public void setIssues_id(String issues_id) {
        this.issues_id = issues_id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "revistas{" +
                "issues_id='" + issues_id + "\n" +
                ", volume='" + volume + "\n" +
                ", number='" + number + "\n" +
                ", year='" + year + "\n" +
                ", date_published='" + date_published + '\'' +
                ", title='" + title + "\n" +
                ", doi='" + doi + "\n" +
                ", cover='" + cover + "\n" +
                "}\n\n";
    }
}
