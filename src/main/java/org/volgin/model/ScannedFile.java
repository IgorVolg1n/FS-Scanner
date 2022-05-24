package org.volgin.model;

import javax.persistence.*;

@Entity
@Table(name = "files", schema = "fs_scanner")
public class ScannedFile {

    public ScannedFile() {
    }

    public ScannedFile(String path, boolean isDir) {
        this.filePath = path;
        this.isDirectory = isDir;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "file_path")
    private String filePath;
    @Column(name = "is_directory")
    private boolean isDirectory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }
}
