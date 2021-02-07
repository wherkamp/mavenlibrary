package me.kingtux.mavenlibrary;

/**
 * A Object representing a repository
 *
 * @since 1.0
 */
public class Repository {
    private String url;
    private String repoID;

    public Repository(String url, String repoID) {
        this.url = url;
        if (!this.url.endsWith("/")) this.url += "/";
        this.repoID = repoID;
    }

    public String getUrl() {
        return url;
    }

    public String getRepoID() {
        return repoID;
    }

    /**
     * Format the URL with a the artifact details
     *
     * @param artifact the artifact
     * @return the formatted URL
     */
    public String formatURL(Artifact artifact) {
        StringBuilder builder = new StringBuilder(url);
        builder.append(artifact.getGroupID().replace(".", "/"));
        builder.append("/");
        builder.append(artifact.getArtifactID());
        return builder.toString();
    }

    /**
     * Format the URL with a the artifact details
     *
     * @param artifact the artifact
     * @param version  the specified version
     * @return the formatted URL
     */
    public String formatURL(Artifact artifact, String version) {
        StringBuilder builder = new StringBuilder(url);
        builder.append(artifact.getGroupID().replace(".", "/"));
        builder.append("/");
        builder.append(artifact.getArtifactID());
        builder.append("/");
        builder.append(version);
        return builder.toString();
    }
    public static class Repositories{
        public static final Repository MAVEN_CENTRAL = new Repository("https://repo1.maven.org/maven2/","central");
    }
}
