package me.kingtux.mavenlibrary.metadata;

import me.kingtux.mavenlibrary.Artifact;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
/**
 * @since 1.0
 */
public class ArtifactMetadata {
    private Artifact artifact;
    private List<String> versions;
    private String lastUpdated;
    private String latest;
    private String release;

    public ArtifactMetadata(Document document, Artifact artifact) {
        this.artifact = artifact;
        versions = new ArrayList<>();
        Element root = document.getRootElement();
        Element versioning = root.element("versioning");
        Element latest = versioning.element("latest");
        if (latest != null) {
            this.latest = latest.getStringValue();
        }
        Element release = versioning.element("release");
        if (release != null) {
            this.release = release.getStringValue();
        }
        Element lastUpdate = versioning.element("lastUpdated");
        if (lastUpdate != null) {
            this.lastUpdated = lastUpdate.getStringValue();
        }

        Element versionsElement = versioning.element("versions");
        for (Element element : versionsElement.elements()) {
            versions.add(element.getStringValue());
        }
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public List<String> getVersions() {
        return versions;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getLatest() {
        return latest;
    }

    public String getRelease() {
        return release;
    }
}
