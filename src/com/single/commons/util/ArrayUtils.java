package com.single.commons.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils{

	/**
	 * 添加不相同的对象.
	 * @param array1
	 * @param array2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] addAllNotSame(T[] array1, T... array2){
		List<T> list = new ArrayList<T>();
		for(T t : array2){
			if(!contains(array1, t) && !list.contains(t)){
				list.add(t);
			}
		}
		final Class<?> type1 = array1.getClass().getComponentType();
        T[] joinedArray = (T[]) Array.newInstance(type1, list.size());
		return addAll(array1, (T[]) list.toArray(joinedArray));
	}
	
	/**
	 * 一次从<code>array1</code>中删除指定的多个对象<code>array2</code>.
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static <T> T[] removeAll(T[] array1, T... array2){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<array2.length;i++){
			int idx = ArrayUtils.indexOf(array1, array2[i]);
			if(idx!=-1){
				list.add(idx);
			}
		}
		int[] idxs = new int[list.size()];
		for(int i=0;i<list.size();i++){
			idxs[i] = list.get(i);
		}
		return ArrayUtils.removeAll(array1, idxs);
	}
}
