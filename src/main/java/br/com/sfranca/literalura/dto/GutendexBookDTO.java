package br.com.sfranca.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexBookDTO {

    private int id;
    private String title;

    @JsonProperty("download_count")
    private int downloadCount;

    private List<String> languages;

    private List<GutendexAuthorDTO> authors;

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

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<GutendexAuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<GutendexAuthorDTO> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "GutendexBookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", downloadCount=" + downloadCount +
                ", languages=" + languages +
                ", authors=" + authors +
                '}';
    }
}
