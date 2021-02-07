package me.kingtux.mavenlibrary;

public class Repository {
    private String url;
    private String repoID;

    public Repository(String url, String repoID) {
        this.url = url;
        this.repoID = repoID;
    }

    public String getUrl() {
        return url;
    }

    public String getRepoID() {
        return repoID;
    }

    public String formatURL(Artifact artifact) {
        StringBuilder builder = new StringBuilder(url);
        builder.append(artifact.getGroupID().replace(".", "/"));
        builder.append("/");
        builder.append(artifact.getArtifactID());
        return builder.toString();
    }

    public String formatURL(Artifact artifact, String version) {
        StringBuilder builder = new StringBuilder(url);
        builder.append(artifact.getGroupID().replace(".", "/"));
        builder.append("/");
        builder.append(artifact.getArtifactID());
        builder.append("/");
        builder.append(version);
        return builder.toString();
    }
}
