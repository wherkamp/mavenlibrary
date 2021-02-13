package me.kingtux.mavenlibrary;

import me.kingtux.mavenlibrary.metadata.ArtifactMetadata;
import me.kingtux.mavenlibrary.releases.ArtifactRelease;
import me.kingtux.mavenlibrary.releases.impl.ArtifactReleaseImpl;
import me.kingtux.mavenlibrary.releases.impl.SnapshotReleaseImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The core class to this library handling artifact resolving.
 *
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
    public List<ArtifactRelease> resolveAllReleases(Artifact artifact) {
        List<ArtifactRelease> releases = new ArrayList<>();
        for (Repository repository : repositories) {
            releases.addAll(resolveAllReleases(artifact, repository));
        }
        return releases;
    }

    /**
     * Resolves all versions for a specific artifact
     *
     * @param artifact   the artifact to resolve
     * @param repository the specified repository
     * @return all found artifact versions
     */
    public List<ArtifactRelease> resolveAllReleases(Artifact artifact, Repository repository) {
        List<ArtifactRelease> releases = new ArrayList<>();
        String s = repository.formatURL(artifact);
        if (repository instanceof LocalRepository) {
            //TODO implement local repository
        } else {
            s += "/maven-metadata.xml";
            if (!WebHelper.doesFileExist(s)){
                return releases;
            }
            try {
                Document document = WebHelper.toDocument(s);
                ArtifactMetadata metadata = new ArtifactMetadata(document, artifact);
                for (String version : metadata.getVersions()) {
                    releases.add(resolveRelease(artifact, version, repository));
                }
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }


        return releases;
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
//TODO implement local
            return null;
        } else {
            String s = repository.formatURL(artifact, version);
            if (version.endsWith("-SNAPSHOT")) {
                if (!WebHelper.doesFileExist(s+="/maven-metadata.xml")) {
                    return null;
                }
                return new SnapshotReleaseImpl(artifact, version, repository);
            } else {
                if (!WebHelper.doesFileExist(s)) {
                    return null;
                }
                return new ArtifactReleaseImpl(artifact, version, repository);
            }
        }
    }
}
