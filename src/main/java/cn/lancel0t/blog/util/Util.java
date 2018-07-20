package cn.lancel0t.blog.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * po集合转vo集合
 * 
 * @author Work
 *
 */
public class Util {

	@SuppressWarnings("unchecked")
	public static <E> List<E> copyList(List<? extends Object> poList, Class<?> voClass) {

		List<E> voList = new ArrayList<E>();

		E voObj = null;
		for (Object poObj : poList) {
			try {
				voObj = (E) voClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(poObj, voObj);
			voList.add(voObj);
		}
		return voList;

	}

}
