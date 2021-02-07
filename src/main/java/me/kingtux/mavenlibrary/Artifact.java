package me.kingtux.mavenlibrary;

import me.kingtux.mavenlibrary.releases.ArtifactRelease;

import java.util.Collections;
import java.util.List;

public class Artifact {
    private String groupID;
    private String artifactID;

    public Artifact(String groupID, String artifactID) {
        this.groupID = groupID;
        this.artifactID = artifactID;
    }

    public List<ArtifactRelease> resolveReleases(ArtifactResolver artifactResolver) {
        return Collections.emptyList();
    }

    public ArtifactRelease resolveRelease(ArtifactResolver artifactResolver, String version) {
        return null;
    }

}
