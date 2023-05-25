package com.example.app.entity;


public class FileResponse {
    private static int counter = 1;
    private int id;


    private String fileName;

    public FileResponse(String fileName) {
        this.id = counter++;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
