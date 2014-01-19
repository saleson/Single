package com.single.commons.util;

import java.lang.reflect.Array;
import java.util.Collection;

public class ListUtils {

	public static <T> boolean addAll(final Collection<T> collection0, final Collection<? extends T> collection1){
		if(collection0==null || collection1==null){
			return false;
		}
		return collection0.addAll(collection1);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(final Collection<T> collection, Class<T> clazz){
		if(collection==null){
			return null;
		}
        T[] joinedArray = (T[]) Array.newInstance(clazz, collection.size());
		return collection.toArray(joinedArray);
	}
}
