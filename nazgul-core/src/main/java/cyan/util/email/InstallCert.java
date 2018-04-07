package cyan.util.email;

/**
 * Created by DreamInSun on 2017/9/14.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


public class InstallCert {
    public static final Logger g_logger = LoggerFactory.getLogger(InstallCert.class);

    public static void main(String[] args) throws Exception {
        String host = "smtp.163.com";
        int port = 465;
        String passphraseStr = "chageit";

//        if ((args.length == 1) || (args.length == 2)) {
//            String[] c = args[0].split(":");
//            host = c[0];
//            port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
//            passphraseStr = (args.length == 1) ? "changeit" : args[1];
//        } else {
//            System.out
//                    .println("Usage: java InstallCert <host>[:port] [passphrase]");
//            return;
//        }
//        InstallCert.run(host, port, passphraseStr);
        InstallCert.run("smtp.163.com", 465, "changeit");
    }

    public static String run(String host, int port, String passphraseStr) throws Exception {
        char[] passphrase = passphraseStr.toCharArray();
        String filePath = null;
        File file = new File("/jssecacerts");
        if (file.isFile() == false) {
            char SEP = File.separatorChar;
            File dir = new File(System.getProperty("java.home") + SEP + "lib"
                    + SEP + "security");
            file = new File(dir, "jssecacerts");
            if (file.isFile() == false) {
                file = new File(dir, "cacerts");
            }
        }
        g_logger.debug("Loading KeyStore " + file + "...");
        filePath = file.getAbsolutePath();

        KeyStore ks = null;

        /*===== Load KS =====*/
        InputStream in = new FileInputStream(file);
        ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(in, passphrase);
        in.close();


        SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf
                .getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory factory = context.getSocketFactory();

        g_logger.debug("Opening connection to " + host + ":" + port + "...");
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.setSoTimeout(10000);
        try {
            g_logger.debug("Starting SSL handshake...");
            socket.startHandshake();
            socket.close();
            g_logger.debug("No errors, certificate is already trusted");
        } catch (SSLException e) {
            g_logger.error(e.getMessage());
            e.printStackTrace(System.out);
        }


        X509Certificate[] chain = tm.chain;
        if (chain == null) {
            g_logger.debug("Could not obtain server certificate chain");
            return null;
        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));


        g_logger.debug("Server sent " + chain.length + " certificate(s):");
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        for (int i = 0; i < chain.length; i++) {
            X509Certificate cert = chain[i];
            g_logger.debug(" " + (i + 1) + " Subject "
                    + cert.getSubjectDN());
            g_logger.debug("   Issuer  " + cert.getIssuerDN());
            sha1.update(cert.getEncoded());
            g_logger.debug("   sha1    " + toHexString(sha1.digest()));
            md5.update(cert.getEncoded());
            g_logger.debug("   md5     " + toHexString(md5.digest()));
        }


        System.out
                .println("Enter certificate to add to trusted keystore or 'q' to quit: [1]");
        //String line = reader.readLine().trim();
        String line = "1";
        int k;
        try {
            k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
        } catch (NumberFormatException e) {
            g_logger.debug("KeyStore not changed");
            return null;
        }

        X509Certificate cert = chain[k];
        String alias = host + "-" + (k + 1);
        ks.setCertificateEntry(alias, cert);


        OutputStream out = new FileOutputStream(filePath);
        ks.store(out, passphrase);
        out.close();
        g_logger.debug(cert.toString());
        System.out
                .println("Added certificate to keystore 'jssecacerts' using alias '"
                        + alias + "'");

        return filePath;
    }

    /*==========  ==========*/
    public static void downloadCert(String host, int port, String passphraseStr, String filePath) {


    }

    /*========== Assistant Function ===========*/
    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();


    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (int b : bytes) {
            b &= 0xff;
            sb.append(HEXDIGITS[b >> 4]);
            sb.append(HEXDIGITS[b & 15]);
            sb.append(' ');
        }
        return sb.toString();
    }


    private static class SavingTrustManager implements X509TrustManager {


        private final X509TrustManager tm;
        private X509Certificate[] chain;


        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }


        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
            // throw new UnsupportedOperationException();
        }


        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            throw new UnsupportedOperationException();
        }


        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            this.chain = chain;
            tm.checkServerTrusted(chain, authType);
        }
    }


}
