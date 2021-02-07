package me.kingtux.mavenlibrary;

import me.kingtux.mavenlibrary.releases.ArtifactRelease;
import me.kingtux.mavenlibrary.releases.impl.ArtifactReleaseImpl;
import me.kingtux.mavenlibrary.releases.impl.FileArtifactRelease;
import me.kingtux.mavenlibrary.releases.impl.FileSnapshotRelease;
import me.kingtux.mavenlibrary.releases.impl.SnapshotReleaseImpl;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * The core class to this library handling artifact resolving.
 * @since 1.0
 */
public class ArtifactResolver {
    private List<Repository> repositories;

    public ArtifactResolver(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    /**
     * Resolves all versions for a specific artifact
     *
     * @param artifact the artifact to resolve
     * @return all found artifact versions
     */
    public List<ArtifactRelease> resolveReleases(Artifact artifact) {
        return Collections.emptyList();
    }

    public ArtifactRelease resolveRelease(Artifact artifact, String version) {
        for (Repository repository : repositories) {
            ArtifactRelease release = resolveRelease(artifact, version, repository);
            if (release != null) return release;
        }
        return null;
    }

    public ArtifactRelease resolveRelease(Artifact artifact, String version, Repository repository) {
        if (repository instanceof LocalRepository) {
            String s = repository.formatURL(artifact, version);
            File file = new File(s);
            File metadata = new File(file, "maven-metadata.xml");
            if (!metadata.exists()) return null;
            if (version.endsWith("-SNAPSHOT")) {
                return new FileSnapshotRelease(artifact, version, repository);
            } else {
                return new FileArtifactRelease(artifact, version, repository);
            }
        } else {
            String s = repository.formatURL(artifact, version) + "/maven-metadata.xml";
            if (!WebHelper.doesFileExist(s)) return null;
            if (version.endsWith("-SNAPSHOT")) {
                return new SnapshotReleaseImpl(artifact, version, repository);
            } else {
                return new ArtifactReleaseImpl(artifact, version, repository);
            }
        }
    }
}
