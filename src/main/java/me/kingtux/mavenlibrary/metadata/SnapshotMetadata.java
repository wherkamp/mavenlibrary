package me.kingtux.mavenlibrary.metadata;

import me.kingtux.mavenlibrary.Artifact;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * @since 1.0
 */
public class SnapshotMetadata {
    private String version;
    private List<SnapshotVersion> versions;
    private String timestamp;
    private String lastUpdated;
    private String buildNumber;
    private Artifact artifact;

    public SnapshotMetadata(Document document, Artifact artifact) {
        this.artifact = artifact;
        versions = new ArrayList<>();
        Element root = document.getRootElement();
        Element version = root.element("version");
        if (version != null) this.version = version.getStringValue();
        Element versioning = root.element("versioning");
        if (versioning == null) return;
        Element snapshot = versioning.element("snapshot");
        if (snapshot != null) {
            Element timestamp = snapshot.element("timestamp");
            if (timestamp != null) this.timestamp = timestamp.getStringValue();
            Element buildnumber = snapshot.element("buildNumber");
            if (buildnumber != null) this.buildNumber = buildnumber.getStringValue();
        }
        Element lastUpdated = versioning.element("lastUpdated");
        if (lastUpdated != null) this.lastUpdated = lastUpdated.getStringValue();
        Element snapshotVersions = versioning.element("snapshotVersions");
        for (Element element : snapshotVersions.elements()) {
            SnapshotVersion snapshotVersion = new SnapshotVersion(element);
            versions.add(snapshotVersion);
        }
    }

    public String getVersion() {
        return version;
    }

    public List<SnapshotVersion> getVersions() {
        return versions;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnapshotMetadata that = (SnapshotMetadata) o;
        return Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getVersions(), that.getVersions()) && Objects.equals(getTimestamp(), that.getTimestamp()) && Objects.equals(getLastUpdated(), that.getLastUpdated()) && Objects.equals(getBuildNumber(), that.getBuildNumber()) && Objects.equals(getArtifact(), that.getArtifact());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVersion(), getVersions(), getTimestamp(), getLastUpdated(), getBuildNumber(), getArtifact());
    }

    @Override
    public String toString() {
        return "SnapshotMetadata{" +
                "version='" + version + '\'' +
                ", versions=" + versions +
                ", timestamp='" + timestamp + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", buildNumber='" + buildNumber + '\'' +
                ", artifact=" + artifact +
                '}';
    }

    public static class SnapshotVersion {
        private String extension;
        private String value;
        private String updated;
        private String classifier;

        public SnapshotVersion(Element snapshotVersion) {
            Element classifier = snapshotVersion.element("classifier");
            if (classifier != null) this.classifier = classifier.getStringValue();
            Element extension = snapshotVersion.element("extension");
            if (extension != null) this.extension = extension.getStringValue();
            Element value = snapshotVersion.element("value");
            if (value != null) this.value = value.getStringValue();
            Element updated = snapshotVersion.element("updated");
            if (updated != null) this.updated = updated.getStringValue();

        }

        public String getExtension() {
            return extension;
        }

        public String getValue() {
            return value;
        }

        public String getUpdated() {
            return updated;
        }

        public String getClassifier() {
            return classifier;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SnapshotVersion that = (SnapshotVersion) o;
            return Objects.equals(getExtension(), that.getExtension()) && Objects.equals(getValue(), that.getValue()) && Objects.equals(getUpdated(), that.getUpdated()) && Objects.equals(getClassifier(), that.getClassifier());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getExtension(), getValue(), getUpdated(), getClassifier());
        }

        @Override
        public String toString() {
            return "SnapshotVersion{" +
                    "extension='" + extension + '\'' +
                    ", value='" + value + '\'' +
                    ", updated='" + updated + '\'' +
                    ", classifier='" + classifier + '\'' +
                    '}';
        }
    }
}
