package classes;

import java.util.HashMap;

public class NamedMap<V> extends HashMap<String , V> {
    public V get(String key) {
        return super.get(key.toUpperCase().replace(" ", ""));
    }

    public V put(String key, V value) {
        return super.put(key.toUpperCase().replace(" ", ""), value);
    }

    public V remove(String key) {
        return super.remove(key.toUpperCase().replace(" ", ""));
    }

    public boolean containsKey(String key) {
        return super.containsKey(key.toUpperCase().replace(" ", ""));
    }


}