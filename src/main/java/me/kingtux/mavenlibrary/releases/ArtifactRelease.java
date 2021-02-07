package me.kingtux.mavenlibrary.releases;

import me.kingtux.mavenlibrary.Artifact;

public interface ArtifactRelease {


    String getVersion();


    ArtifactFile getArtifactFile(String classifier);

    Artifact getArtifact();
}
