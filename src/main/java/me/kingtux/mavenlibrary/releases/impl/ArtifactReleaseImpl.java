package me.kingtux.mavenlibrary.releases.impl;

import me.kingtux.mavenlibrary.Artifact;
import me.kingtux.mavenlibrary.Repository;
import me.kingtux.mavenlibrary.WebHelper;
import me.kingtux.mavenlibrary.metadata.ArtifactMetadata;
import me.kingtux.mavenlibrary.metadata.SnapshotMetadata;
import me.kingtux.mavenlibrary.releases.ArtifactFile;
import me.kingtux.mavenlibrary.releases.ArtifactRelease;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.Set;

/**
 * @since 1.0
 */
public class ArtifactReleaseImpl implements ArtifactRelease {
    private ArtifactMetadata artifactMetadata;
    private SnapshotMetadata snapshotMetadata;
    private Artifact artifact;
    private String version;
    private Repository repository;

    public ArtifactReleaseImpl(Artifact artifact, String version, Repository repository) {
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
        String s = repository.formatURL(artifact, version);
        StringBuilder builder = new StringBuilder(s);
        builder.append("/").append(artifact.getArtifactID()).append("-").append(version).append(".").append(extension);
        return new ArtifactFile(builder.toString(), this);
    }

    @Override
    public ArtifactFile getArtifactFile(String extension, String classifier) {
        String s = repository.formatURL(artifact, version);
        StringBuilder builder = new StringBuilder(s);
        builder.append("/").append(artifact.getArtifactID()).append("-").append(version).append("-").append(classifier).append(".").append(extension);
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
