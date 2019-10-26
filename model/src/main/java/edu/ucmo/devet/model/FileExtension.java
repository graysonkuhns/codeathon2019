package edu.ucmo.devet.model;

public class FileExtension {
    private final String extension;
    private final int language;

    public FileExtension(String extension, int language) {
        this.extension = extension;
        this.language = language;
    }

    public String getExtension() {
        return extension;
    }

    public int getLanguage() {
        return language;
    }
}
