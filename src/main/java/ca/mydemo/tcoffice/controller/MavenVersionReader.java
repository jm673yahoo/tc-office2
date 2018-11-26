package ca.mydemo.tcoffice.controller;

import org.apache.logging.log4j.LogManager;

import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MavenVersionReader {

    private MavenVersionReader() {
    }

    static String readVersions() {
        try {
            Enumeration<URL> _urls = MavenVersionReader.class.getClassLoader()
                    .getResources("META-INF/maven/ca.mydemo/tcoffice");
            while (_urls.hasMoreElements()) {
                URL _url = _urls.nextElement();
                LogManager.getLogger(MavenVersionReader.class).info(" _url " + _url);
                JarURLConnection _urlConnection = (JarURLConnection) _url.openConnection();
                JarFile jar = null;
                try {
                    jar = _urlConnection.getJarFile();

                    Enumeration<JarEntry> _entries = jar.entries();
                    while (_entries.hasMoreElements()) {
                        final String _entry = _entries.nextElement().getName();
                        if (Pattern.matches("META-INF/maven/com.ctpayment/[^\\s]+/pom.properties", _entry)) {
                            final Pattern _pattern = Pattern
                                    .compile("META-INF/maven/com.ctpayment/(.*?)/pom.properties");
                            final Matcher _matcher = _pattern.matcher(_entry);
                            if (_matcher.find()) {
                                return readVersion(_matcher.group(1), _entry);
                            }
                        }
                    }
                } finally {
                    jar.close();
                }
            }
        } catch (Exception _exception) {
            LogManager.getLogger(MavenVersionReader.class)
                    .error("An exception was thrown while reading the maven versions", _exception);
        }

        return getToday();
    }


    private static String readVersion(final String applicationName_, final String versionFile_) {
        if (versionFile_ != null && versionFile_.trim().length() != 0) {


            InputStream _inputStream = null;
            try {
                _inputStream = MavenVersionReader.class.getClassLoader().getResourceAsStream(versionFile_);

                if (_inputStream != null) {
                    Properties _properties = new Properties();
                    _properties.load(_inputStream);
                    if (_properties.get("version") != null) {
                        String _version = _properties.getProperty("version");
                        LogManager.getLogger(MavenVersionReader.class)
                                .info("\t > " + applicationName_ + ": " + _version);
                        return _version;
                    }
                } else {
                    System.out.println("\t > " + applicationName_ + ": no dependency.");
                }
            } catch (Exception _exception) {
                LogManager.getLogger(MavenVersionReader.class)
                        .error("An exception was thrown while reading the version for " + versionFile_, _exception);
            } finally {
                if (_inputStream != null) {
                    try {
                        _inputStream.close();
                    } catch (Exception _exception) {
                        LogManager.getLogger(MavenVersionReader.class)
                                .error("An exception was thrown while closing the input stream for " + versionFile_,
                                        _exception);
                    }
                }
            }
        } else {
            LogManager.getLogger(MavenVersionReader.class)
                    .warn("The version could not be read because the provided file name is either null or empty");
        }

        return getToday();
    }


    private static String getToday() {
        SimpleDateFormat _formate = new SimpleDateFormat("yyMMddHHmmssZ");

        return _formate.format(new Date());
    }
}
