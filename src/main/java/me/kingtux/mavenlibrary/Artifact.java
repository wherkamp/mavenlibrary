package me.kingtux.mavenlibrary;

import me.kingtux.mavenlibrary.releases.ArtifactRelease;

import java.util.List;

/**
 * A object representation of a Artifact within the Maven
 *
 * @since 1.0
 */
public class Artifact {
    private String groupID;
    private String artifactID;
    private String version;

    /**
     * Constructor for creating the object
     *
     * @param groupID    the groupID
     * @param artifactID the artifactID
     */
    public Artifact(String groupID, String artifactID) {
        this.groupID = groupID;
        this.artifactID = artifactID;
    }

    /**
     * Constructor for creating the object
     *
     * @param groupID    the groupID
     * @param artifactID the artifactID
     * @param version    The artifact version.
     */
    public Artifact(String groupID, String artifactID, String version) {
        this.groupID = groupID;
        this.artifactID = artifactID;
        this.version = version;
    }

    /**
     * Resolve releases inside the avaible repositories
     *
     * @param artifactResolver the artifactResolver
     * @return the found releases
     */
    public List<ArtifactRelease> resolveAllReleases(ArtifactResolver artifactResolver) {
        return artifactResolver.resolveAllReleases(this);
    }

    /**
     * Resolve for a specific version
     *
     * @param artifactResolver the artifactResolver
     * @param version          the specific version
     * @return the found release
     */
    public ArtifactRelease resolveRelease(ArtifactResolver artifactResolver, String version) {
        return artifactResolver.resolveRelease(this, version);
    }

    /**
     * Resolve for a specific version
     *
     * @param artifactResolver the artifactResolver
     * @return the found release
     */
    public ArtifactRelease resolveRelease(ArtifactResolver artifactResolver) {
        if (version == null) throw new IllegalArgumentException("Missing version argument");
        return artifactResolver.resolveRelease(this, version);
    }

    public String getGroupID() {
        return groupID;
    }

    public String getVersion() {
        return version;
    }

    public String getArtifactID() {
        return artifactID;
    }
}
