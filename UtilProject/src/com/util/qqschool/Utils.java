package com.util.qqschool;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * 工具类
 * @author   yangjian1004
 * @Date     Jun 18, 2014    
 */
public class Utils {
    /**
     * 
     * 较方便的创建一个map
     * <p>
     * 注，这里的 Map，是 HashMap 的实例
     * 
     * @param <K> key的类型
     * @param <V> value的类型
     * @return map对象
     */
    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }
 
    /**
     * 向map的列表中添加对象
     * <p>
     * 如果map不存在
     *
     * @param <K> key类型
     * @param <V> value类型
     * @param map 要添加的map
     * @param key 键
     * @param value 值
     * @throws DataException map为null，则提示DataException
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, List<V>> addMapList(final Map<K, List<V>> map, final K key, final V value) {
        if (map == null) {
            throw new RuntimeException("Null");
        }
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            map.put(key, list(value));
        }
        return map;
    }
 
    /**
     * 较方便的创建一个列表，比如：
     * 
     * <pre>
     * List&lt;Pet&gt; pets = Lang.list(pet1, pet2, pet3);
     * </pre>
     * 
     * 注，这里的 List，是 ArrayList 的实例
     * 根据传递的参数 参数List泛型数组
     * 
     * @param eles
     *            可变参数，需要转换成的数据
     * @return 转换后的List泛型数组
     */
    public static <T> ArrayList<T> list(T... eles) {
        ArrayList<T> list = new ArrayList<T>(eles.length);
        for (T ele : eles)
            list.add(ele);
        return list;
    }
 
    /**
     * 判断一个对象是否为空。它支持如下对象类型：
     * <ul>
     * <li>null : 一定为空
     * <li>字符串     : ""为空,多个空格也为空
     * <li>数组
     * <li>集合
     * <li>Map
     * <li>其他对象 : 一定不为空
     * </ul>
     * 
     * @param obj
     *            任意对象
     * @return 是否为空
     */
    public final static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return "".equals(String.valueOf(obj).trim());
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection<?>) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map<?, ?>) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        return false;
    }
}