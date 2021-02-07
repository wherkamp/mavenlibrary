package me.kingtux.mavenlibrary.releases;

import me.kingtux.mavenlibrary.Artifact;
import me.kingtux.mavenlibrary.Repository;

public interface ArtifactRelease {


    String getVersion();


    ArtifactFile getArtifactFile(String classifier);

    Artifact getArtifact();

    Repository getRepository();
}
