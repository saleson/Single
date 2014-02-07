package com.single.commons.util;

import java.util.Map;

/**
 * 源于{@link java.util.HashMap.Entry}
 */
public class Entry<K,V> implements Map.Entry<K, V>{
    final private K key;
    private V value;
    //static final Object NULL_KEY = new Object();

    /**
     * Create new entry.
     */
    public Entry(K k, V v) {
        value = v;
        key = k;
    }
    
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V newValue) {
	    V oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Entry other = (Entry) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((key == null) ? 0 : key.hashCode());
		result = PRIME * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


    @Override
	public String toString() {
        return "\"" + getKey() + "\""+ "=" + "\""+getValue()+"\"";
    }
}
