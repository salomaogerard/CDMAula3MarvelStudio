package com.example.cdmaula3.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String idMovie;
    private String title;
    private String overView;
    private String pathPoster;
    private int thumbnail;
    private String coverPhoto;


    protected Movie(Parcel in) {
        idMovie = in.readString();
        title = in.readString();
        overView = in.readString();
        pathPoster = in.readString();
        thumbnail = in.readInt();
        coverPhoto = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idMovie);
        parcel.writeString(title);
        parcel.writeString(overView);
        parcel.writeString(pathPoster);
        parcel.writeInt(thumbnail);
        parcel.writeString(coverPhoto);
    }

    public Movie(String title, String pathPoster, String overView, String idMovie, String coverPhoto) {
        this.coverPhoto = coverPhoto;
        this.idMovie = idMovie;
        this.title = title;
        this.pathPoster = pathPoster;
        this.overView = overView;

    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
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

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }


}
