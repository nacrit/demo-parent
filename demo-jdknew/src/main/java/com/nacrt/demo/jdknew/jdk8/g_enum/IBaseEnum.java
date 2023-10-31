package com.nacrt.demo.jdknew.jdk8.g_enum;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mars
 * @description BaseEnum
 * @date 2023/10/30 14:19
 */
public interface IBaseEnum<T> {

    /**
     * 获取code
     */
    @SuppressWarnings("unchecked")
    default T getCode() {
        return (T) EnumContainer.get(this).code;
    }

    /**
     * 获取msg
     */
    default String getMsg() {
        return EnumContainer.get(this).msg;
    }

    /**
     * 根据code获取枚举值
     */
    static <R extends IBaseEnum<VC>, VC> R valueOf(Class<R> clazz, VC code) {
        return Optional.ofNullable(clazz)
                .filter(Class::isEnum)
                .map(Class::getEnumConstants)
                .map(Arrays::stream)
                .flatMap(e -> e.filter(x -> x.getCode().equals(code)).findFirst())
                .orElse(null);
    }

    /**
     * 初始化方法
     */
    default void init(T code, String msg) {
        if (!this.getClass().isEnum()) {
            throw new RuntimeException("只允许枚举实现此接口");
        }
        EnumContainer.put(this, code, msg);
    }

    /**
     * 存储枚举信息
     */
    class EnumContainer {
        // 只给接口使用
        private EnumContainer() {
        }

        // 缓存枚举数据, key是枚举值，value是枚举具体code、msg信息
        private static final Map<IBaseEnum<?>, EnumBean<?>> CODE_MSG_MAP = new ConcurrentHashMap<>();

        private static void put(IBaseEnum<?> key, Object code, String msg) {
            CODE_MSG_MAP.put(key, new EnumBean<>(code, msg));
        }

        private static <K extends IBaseEnum<?>> EnumBean<?> get(K k) {
            return CODE_MSG_MAP.get(k);
        }

    }

    /**
     * 通用枚举code、msg实体
     *
     * @param <T>
     */
    class EnumBean<T> {
        private final T code;
        private final String msg;

        private EnumBean(T code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
