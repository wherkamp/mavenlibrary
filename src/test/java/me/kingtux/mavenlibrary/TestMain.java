package me.kingtux.mavenlibrary;

import me.kingtux.mavenlibrary.releases.ArtifactRelease;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.releaseTest();
        testMain.snapshotTest();
    }

    @Test
    public void releaseTest() {
        ArtifactResolver artifactResolver = new ArtifactResolver(List.of(Repository.Repositories.MAVEN_CENTRAL, new Repository("https://repo.potatocorp.dev/storages/maven/kingtux-repo/", "kingtux-repo")));

        Artifact artifact = new Artifact("me.kingtux", "tuxorm");
        List<ArtifactRelease> artifactReleases = artifact.resolveReleases(artifactResolver);
        assertTrue(artifactReleases.size() != 0);
        for (ArtifactRelease artifactRelease : artifactReleases) {
            if (artifactRelease.getVersion().equals("1.0")) {
                assertEquals(artifactRelease.getVersion(), "1.0");
            }
        }
        ArtifactRelease release = artifact.resolveRelease(artifactResolver, "1.0");
        assertNotNull(release);
        assertEquals(release.getVersion(), "1.0");
        File file = new File("test.jar");
        file.deleteOnExit();
        try {
            release.getArtifactFile("jar").download(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(file.exists());
    }

    @Test
    public void snapshotTest() {
        ArtifactResolver artifactResolver = new ArtifactResolver(List.of(new Repository("https://repo.potatocorp.dev/storages/maven/kingtux-repo/", "kingtux-repo")));

        Artifact artifact = new Artifact("me.kingtux", "ResourceWorlds");
        List<ArtifactRelease> artifactReleases = artifact.resolveReleases(artifactResolver);
        assertTrue(artifactReleases.size() != 0);
        for (ArtifactRelease artifactRelease : artifactReleases) {
            if (artifactRelease.getVersion().equals("1.0-SNAPSHOT")) {
                assertEquals(artifactRelease.getVersion(), "1.0-SNAPSHOT");
            }
        }
        ArtifactRelease release = artifact.resolveRelease(artifactResolver, "1.0-SNAPSHOT");
        assertNotNull(release);
        assertEquals(release.getVersion(), "1.0-SNAPSHOT");
        File file = new File("test.jar");
        file.deleteOnExit();
        try {
            release.getArtifactFile("jar").download(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(file.exists());
    }
}
