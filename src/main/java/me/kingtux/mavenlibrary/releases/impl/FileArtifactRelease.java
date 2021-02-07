package me.kingtux.mavenlibrary.releases.impl;

import me.kingtux.mavenlibrary.Artifact;
import me.kingtux.mavenlibrary.Repository;
import me.kingtux.mavenlibrary.releases.ArtifactFile;
import me.kingtux.mavenlibrary.releases.ArtifactRelease;

public class FileArtifactRelease implements ArtifactRelease {
    public FileArtifactRelease(Artifact artifact, String version, Repository repository) {

    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public ArtifactFile getArtifactFile(String classifier) {
        return null;
    }

    @Override
    public Artifact getArtifact() {
        return null;
    }

    @Override
    public Repository getRepository() {
        return null;
    }
}
