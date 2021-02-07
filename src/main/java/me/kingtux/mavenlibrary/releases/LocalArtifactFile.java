package me.kingtux.mavenlibrary.releases;

import me.kingtux.mavenlibrary.Artifact;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LocalArtifactFile extends ArtifactFile {
    public LocalArtifactFile(String downloadLocation, ArtifactRelease release) {
        super(downloadLocation, release);
    }

    @Override
    public File download(File file) throws IOException {
        File file1 = new File(getDownloadLocation());
        File finalFile = file;
        if (file.isDirectory()) {
            String[] split = getDownloadLocation().split("/");
            finalFile = new File(file, split[split.length]);
        }
        Files.copy(file1.toPath(), finalFile.toPath());
        return finalFile;
    }
}
