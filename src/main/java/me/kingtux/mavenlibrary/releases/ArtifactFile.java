package me.kingtux.mavenlibrary.releases;

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
     * Will download the artifact if not found in the local repository.
     *
     * @return the File once downloaded if required.
     * @throws IOException if the download fails.
     */
    public File getFile() throws IOException {
        //TODO implement download
        return null;
    }

    public void download(File file) {
        //TODO implement download
    }

    /**
     * Appends the Jar file to the class path using  instrumentation
     *
     * @param instrumentation the instance of the instrumentation
     * @throws Exception If file fails to be found or fails to add jar
     */
    public void addToClassPath(Instrumentation instrumentation) throws Exception {
        instrumentation.appendToSystemClassLoaderSearch(new JarFile(getFile()));
    }

    public void addToClassPath(URLClassLoader classLoader) {

    }

}
