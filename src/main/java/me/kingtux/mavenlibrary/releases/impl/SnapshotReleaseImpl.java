package me.kingtux.mavenlibrary.releases.impl;

import me.kingtux.mavenlibrary.Artifact;
import me.kingtux.mavenlibrary.Repository;
import me.kingtux.mavenlibrary.WebHelper;
import me.kingtux.mavenlibrary.metadata.ArtifactMetadata;
import me.kingtux.mavenlibrary.metadata.SnapshotMetadata;
import me.kingtux.mavenlibrary.releases.ArtifactFile;
import me.kingtux.mavenlibrary.releases.ArtifactRelease;
import me.kingtux.mavenlibrary.releases.SnapshotRelease;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.Set;

/**
 * @since 1.0
 */
public class SnapshotReleaseImpl implements SnapshotRelease {
    private ArtifactMetadata artifactMetadata;
    private SnapshotMetadata snapshotMetadata;
    private Artifact artifact;
    private String version;
    private Repository repository;

    public SnapshotReleaseImpl(Artifact artifact, String version, Repository repository) {
        this.artifact = artifact;
        this.version = version;
        this.repository = repository;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public ArtifactFile getArtifactFile(String extension) {
        return getArtifactFileByTimestamp(extension, getSnapshotMetaData().getTimestamp() + "-" + getSnapshotMetaData().getBuildNumber());
    }

    @Override
    public ArtifactFile getArtifactFile(String classifier, String extension) {
        return getArtifactFileByTimestamp(extension, getSnapshotMetaData().getTimestamp() + "-" + getSnapshotMetaData().getBuildNumber(), classifier);
    }

    @Override
    public ArtifactFile getArtifactFileByTimestamp(String extension, String timestamp) {
        String s = repository.formatURL(artifact, version);
        StringBuilder builder = new StringBuilder(s);
        builder.append("/").append(artifact.getArtifactID()).append("-").append(version.replace("-SNAPSHOT","")).append("-").append(timestamp).append(".").append(extension);
        return new ArtifactFile(builder.toString(), this);
    }

    @Override
    public ArtifactFile getArtifactFileByTimestamp(String extension, String timestamp, String classifier) {
        String s = repository.formatURL(artifact, version);
        StringBuilder builder = new StringBuilder(s);
        builder.append("/").append(artifact.getArtifactID()).append("-").append(version.replace("-SNAPSHOT","")).append("-").append(timestamp).append("-").append(classifier).append(".").append(extension);
        return new ArtifactFile(builder.toString(), this);
    }


    @Override
    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public Repository getRepository() {
        return repository;
    }

    @Override
    public SnapshotMetadata getSnapshotMetaData() {
        if (snapshotMetadata == null) {
            String s = repository.formatURL(artifact, version) + "/maven-metadata.xml";
            try {
                Document document = WebHelper.toDocument(s);
                snapshotMetadata = new SnapshotMetadata(document, artifact);

            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
        return snapshotMetadata;
    }

    @Override
    public ArtifactMetadata getArtifactMetadata() {
        if (artifactMetadata == null) {
            String s = repository.formatURL(artifact) + "/maven-metadata.xml";
            try {
                Document document = WebHelper.toDocument(s);
                artifactMetadata = new ArtifactMetadata(document, artifact);

            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
        return artifactMetadata;
    }
}
