package com.example.android.bakingapp.food;

import java.io.Serializable;

public class Steps  implements Serializable {

    private String id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;



    public Steps(String id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }


}