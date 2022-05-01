// 
// Decompiled by Procyon v0.5.30
// 

package es.salassoft.fitodowload.httptools;

import java.io.InputStream;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import es.salassoft.fitodowload.guitools.MainLogger;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class FileDown
{
    public static void download(final String address, final String localFileName) {
        OutputStream out = null;
        URLConnection conn = null;
        InputStream in = null;
        boolean error = false;
        final int maxTrys = 3;
        
     // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }

        int trys = 0;
        while (error = (3 > trys)) {
            error = false;
            ++trys;
            try {
                final URL url = new URL(address);
                out = new BufferedOutputStream(new FileOutputStream(localFileName));
                conn = url.openConnection();
                in = conn.getInputStream();
                final byte[] buffer = new byte[1024];
                long numWritten = 0L;
                int numRead;
                while ((numRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, numRead);
                    numWritten += numRead;
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
                error = true;
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out == null) {
                        continue;
                    }
                    out.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                    error = true;
                }
                continue;
            }
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                    error = true;
                }
            }
            try {
                if (in != null) {
                    in.close();
                }
                if (out == null) {
                    continue;
                }
                out.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                error = true;
            }
        }
        if (error) {
            MainLogger.logError("+++++++ Error No se ha podido Descargar el fichero " + localFileName);
        }
    }
    
    public static void download(final String address) {
        final int lastSlashIndex = address.lastIndexOf(47);
        if (lastSlashIndex >= 0 && lastSlashIndex < address.length() - 1) {
            download(address, address.substring(lastSlashIndex + 1));
        }
        else {
            MainLogger.logError("Could not figure out local file name for " + address);
        }
    }
    
    public static void main(final String[] args) {
        for (int i = 0; i < args.length; ++i) {
            download(args[i]);
        }
    }
}
