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
}
