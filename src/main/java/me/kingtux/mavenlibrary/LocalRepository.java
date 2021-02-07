package me.kingtux.mavenlibrary;

import java.io.File;

public class LocalRepository extends Repository {
    public LocalRepository() {
        super(System.getProperty("user.dir") + ".m2" + File.separator + "repository", "local");
    }

    @Override
    public String formatURL(Artifact artifact) {
        return "";
    }
}
