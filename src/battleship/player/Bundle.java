package battleship.player;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

public class Bundle {
    private Map<Integer, Object> data = new HashMap<>();

    public void putInteger(Integer key, Integer value) {
        data.put(key, value);
    }

    public Integer getInteger(Integer key) {
        Object value = data.get(key);
        if (value == null) {
            throw new RuntimeException("specified key \"" + key + "\" not presented");
        }
        return (Integer) value;
    }

    public String toJSON() {
        throw new UnsupportedOperationException("not implemented");
    }
}
