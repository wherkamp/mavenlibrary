package me.kingtux.mavenlibrary;

import java.util.List;

public class ArtifactResolver {
    private List<Repository> repositories;

    public ArtifactResolver(List<Repository> repositories) {
        this.repositories = repositories;
    }
}
