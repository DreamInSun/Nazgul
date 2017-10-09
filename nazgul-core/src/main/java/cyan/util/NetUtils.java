package cyan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DreamInSun on 2017/9/12.
 */

public class NetUtils {
    private static final Logger g_Logger = LoggerFactory.getLogger(NetUtils.class);

    public static String getLocalAddress() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            g_Logger.error(e.getMessage());
        }
        return ip;
    }

    public static String getPubIpv4() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    g_Logger.error(e.getMessage());
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }

    public static void main(String[] args) throws Exception {
        String localIp = NetUtils.getLocalAddress();
        String pubIp = NetUtils.getPubIpv4();
        System.out.println("Local IP:" + localIp);
        System.out.println("Public IP:" + pubIp);
    }
}
