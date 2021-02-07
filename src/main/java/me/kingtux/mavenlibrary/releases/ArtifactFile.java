package me.kingtux.mavenlibrary.releases;

import java.io.File;
import java.io.IOException;

public class ArtifactFile {
    private String downloadLocation;
    private ArtifactRelease release;

    public ArtifactFile(String downloadLocation, ArtifactRelease release) {
        this.downloadLocation = downloadLocation;
        this.release = release;
    }

    /**
     * Will download the artifact if not found in the local repository.
     *
     * @return the File once downloaded if required.
     * @throws IOException if the download fails.
     */
    public File getFile() throws IOException {
        //TODO implement download
        return null;
    }

}