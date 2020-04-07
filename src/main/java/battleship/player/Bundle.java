package battleship.player;

import java.util.HashMap;
import java.util.Map;

public class Bundle {
    private Map<Integer, Object> data = new HashMap<>();

    public Bundle putInteger(Integer key, Integer value) {
        data.put(key, value);
        return this;
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
