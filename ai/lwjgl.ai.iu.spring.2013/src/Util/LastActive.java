package Util;

import java.util.Hashtable;

public class LastActive {
	public Hashtable<Integer, Long>	last	= new Hashtable<Integer, Long>();

	public Long getCurrent(Integer key) {
		Long l = last.get(key);
		if (l == null)
			return System.currentTimeMillis();
		else
			return System.currentTimeMillis() - l;
	}

	public void putCurrent(int key) {
		last.put(key, System.currentTimeMillis());
	}

	public long getSet(int key) {
		long l = getCurrent(key);
		putCurrent(key);
		return l;
	}
}
