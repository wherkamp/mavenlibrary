package me.kingtux.mavenlibrary.releases;

import me.kingtux.mavenlibrary.metadata.SnapshotMetadata;

import java.util.Set;

/**
 * @since 1.0
 */
public interface SnapshotRelease extends ArtifactRelease {
    /**
     * Will pull the latest version of the snapshot
     *
     * @param extension the file extension
     * @return the artifact file
     */
    ArtifactFile getArtifactFile(String extension);

    ArtifactFile getArtifactFileByTimestamp(String extension, String timestamp);

    ArtifactFile getArtifactFileByTimestamp(String extension, String timestamp, String classifier);


    SnapshotMetadata getSnapshotMetaData();
}
