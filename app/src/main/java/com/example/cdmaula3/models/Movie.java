package com.example.cdmaula3.models;

public class Movie {

    private String title;
    private String description;
    private String pathPoster;

    public Movie(String title, String pathPoster) {
        this.title = title;
        this.pathPoster = pathPoster;
    }

    public Movie(String title, String description, String pathPoster) {
        this.title = title;
        this.description = description;
        this.pathPoster = pathPoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathPoster() {
        return pathPoster;
    }

    public void setPathPoster(String pathPoster) {
        this.pathPoster = pathPoster;
    }
}
