package edu.ucmo.devet.model;

public class FileExtension {
    private String extension;
    private int language;

    public FileExtension(String extension, int language) {
        this.extension = extension;
        this.language = language;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
}
