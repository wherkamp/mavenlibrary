package me.kingtux.mavenlibrary;

import java.io.File;

/**
 * For local file repositories
 *
 * @since 1.0
 * @deprecated until implemented
 */
public class LocalRepository extends Repository {
    public LocalRepository() {
        super(System.getProperty("user.dir") + ".m2" + File.separator + "repository", "local");
    }

    @Override
    public String formatURL(Artifact artifact) {
        StringBuilder builder = new StringBuilder(getUrl());
        builder.append(artifact.getGroupID().replace(".", File.separator));
        builder.append(File.separator);
        builder.append(artifact.getArtifactID());
        return builder.toString();

    }

    @Override
    public String formatURL(Artifact artifact, String version) {
        StringBuilder builder = new StringBuilder(getUrl());
        builder.append(artifact.getGroupID().replace(".", File.separator));
        builder.append(File.separator);
        builder.append(artifact.getArtifactID());
        builder.append(File.separator);
        builder.append(version);
        return builder.toString();
    }
}
