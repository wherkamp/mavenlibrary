package me.kingtux.mavenlibrary.releases.impl;

import me.kingtux.mavenlibrary.Artifact;
import me.kingtux.mavenlibrary.Repository;
import me.kingtux.mavenlibrary.releases.ArtifactFile;
import me.kingtux.mavenlibrary.releases.ArtifactRelease;
import me.kingtux.mavenlibrary.releases.SnapshotRelease;

import java.util.Set;
/**
 * @since 1.0
 */
public class SnapshotReleaseImpl implements SnapshotRelease {
    public SnapshotReleaseImpl(Artifact artifact, String version, Repository repository) {

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
    public ArtifactFile getArtifactFile(String classifier, String timestamp) {
        return null;
    }

    @Override
    public Set<String> getTimestamps() {
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
