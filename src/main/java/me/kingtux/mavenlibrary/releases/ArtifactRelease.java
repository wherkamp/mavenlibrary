package me.kingtux.mavenlibrary.releases;

import me.kingtux.mavenlibrary.Artifact;
import me.kingtux.mavenlibrary.Repository;
import me.kingtux.mavenlibrary.metadata.ArtifactMetadata;

/**
 * @since 1.0
 */
public interface ArtifactRelease {


    String getVersion();


    ArtifactFile getArtifactFile(String extension);

    ArtifactFile getArtifactFile(String classifier, String extension);

    Artifact getArtifact();

    Repository getRepository();

    ArtifactMetadata getArtifactMetadata();
}
