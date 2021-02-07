package me.kingtux.mavenlibrary.releases;

import java.util.Set;
/**
 * @since 1.0
 */
public interface SnapshotRelease extends ArtifactRelease {
    /**
     * Will pull the latest version of the snapshot
     *
     * @param classifier the object classifier null for no
     * @return
     */
    ArtifactFile getArtifactFile(String classifier);

    ArtifactFile getArtifactFile(String classifier, String timestamp);

    Set<String> getTimestamps();
}
