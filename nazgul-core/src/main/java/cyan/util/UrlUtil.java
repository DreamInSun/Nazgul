package cyan.util;

import java.util.Map;

/**
 * Created by DreamInSun on 2018/2/16.
 */
public class UrlUtil {

    public static String formatQueryParams(Map<String, String> params) {
        return params.entrySet().stream()
                .map(p -> p.getKey() + "=" + p.getValue())
                .reduce((p1, p2) -> p1 + "&" + p2)
                .map(s -> "?" + s)
                .orElse("");
    }
}
