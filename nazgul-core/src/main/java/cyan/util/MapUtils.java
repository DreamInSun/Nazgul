package cyan.util;

import java.io.*;
import java.util.Map;

/**
 * Created by DreamInSun on 2016/7/21.
 */
public class MapUtils {
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj) {
        T clonedObj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clonedObj;
    }

    public static <T extends Map<Object, Object>> T merge(T destMap, T srcMap) {

         /*===== Format Return Map =====*/
        for (Map.Entry<Object, Object> entry : srcMap.entrySet()) {
            Object key = entry.getKey();
            Object srcVal = entry.getValue();
            Object destVal = destMap.get(key);

        }
        return destMap;
    }
}
