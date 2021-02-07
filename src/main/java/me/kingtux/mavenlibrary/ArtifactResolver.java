package me.kingtux.mavenlibrary;

import me.kingtux.mavenlibrary.releases.ArtifactRelease;

import java.util.Collections;
import java.util.List;

public class ArtifactResolver {
    private List<Repository> repositories;

    public ArtifactResolver(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public List<ArtifactRelease> resolveReleases(Artifact artifact) {
        return Collections.emptyList();
    }

    public ArtifactRelease resolveRelease(Artifact artifact, String version) {
        return null;
    }
}
