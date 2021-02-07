package me.kingtux.mavenlibrary.releases;

import me.kingtux.mavenlibrary.Artifact;
import me.kingtux.mavenlibrary.WebHelper;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class ArtifactFile {
    private String downloadLocation;
    private ArtifactRelease release;

    public ArtifactFile(String downloadLocation, ArtifactRelease release) {
        this.downloadLocation = downloadLocation;
        this.release = release;
    }

    /**
     * Will download the file to a specific location
     *
     * @param file where to put the file
     * @return the final location if a directory has been passed
     * @throws IOException when the download has failed
     */
    public File download(File file) throws IOException {
        File finalFile = file;
        if (file.isDirectory()) {
            Artifact artifact = release.getArtifact();
            String[] split = downloadLocation.split("/");
            finalFile = new File(file, split[split.length]);
        }
        WebHelper.downloadFile(downloadLocation, finalFile);
        return finalFile;
    }

    /**
     * Appends the Jar file to the class path using  instrumentation
     *
     * @param instrumentation the instance of the instrumentation
     * @throws Exception If file fails to be found or fails to add jar
     */
    public void addToClassPath(Instrumentation instrumentation) throws Exception {
        addToClassPath(instrumentation, getLibrariesFolder());
    }

    /**
     * Appends the Jar file to the class path using  instrumentation
     *
     * @param instrumentation the instance of the instrumentation
     * @param file            where to put the file
     * @throws Exception If file fails to be found or fails to add jar
     */
    public void addToClassPath(Instrumentation instrumentation, File file) throws Exception {
        instrumentation.appendToSystemClassLoaderSearch(new JarFile(download(file)));
    }

    private File getLibrariesFolder() {
        File file = new File("libraries");
        if (!file.exists()) file.mkdir();
        return file;
    }

    public void addToClassPath(URLClassLoader classLoader) {

    }

    public String getDownloadLocation() {
        return downloadLocation;
    }

    public ArtifactRelease getRelease() {
        return release;
    }
}
