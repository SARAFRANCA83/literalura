package br.com.sfranca.literalura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GutendexBookDTO {

    private int id;
    private String title;

    @JsonProperty("download_count")
    private int downloadCount;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}

