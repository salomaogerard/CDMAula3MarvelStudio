package com.example.cdmaula3.models;

public class Movie {

    private String title;
    private String overView;
    private String pathPoster;
    private int thumbnail;
    private int coverPhoto;


    public Movie(String title, int thumbnail, int coverPhoto) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
    }


    public Movie(String title, String pathPoster) {
        this.title = title;
        this.pathPoster = pathPoster;
    }

    public Movie(String title, String pathPoster, String overView) {
        this.title = title;
        this.pathPoster = pathPoster;
        this.overView = overView;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getPathPoster() {
        return pathPoster;
    }

    public void setPathPoster(String pathPoster) {
        this.pathPoster = pathPoster;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}
