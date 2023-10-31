package com.nacrt.demo.jdknew.jdk8.g_enum;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mars
 * @description BaseEnum
 * @date 2023/10/30 14:19
 */
public interface IBaseEnum<T> {

    @SuppressWarnings("unchecked")
    default T getCode() {
        return (T) EnumContainer.get(this).code;
    }

    default String getMsg() {
        return EnumContainer.get(this).msg;
    }

    @SuppressWarnings("unchecked")
    default <K extends IBaseEnum<VC>, VC> K valueOf(VC code) {
        return (K) Arrays.stream(this.getClass().getEnumConstants())
                .filter(e -> e.getCode().equals(code))
                .findFirst().orElse(null);
    }

    default void init(T code, String msg) {
        EnumContainer.put(this, code, msg);
    }

    class EnumContainer {
        private EnumContainer() {
        }

        // 缓存枚举数据
        private static final Map<IBaseEnum<?>, EnumBean<?>> CODE_MSG_MAP = new ConcurrentHashMap<>();

        private static void put(IBaseEnum<?> key, Object code, String msg) {
            CODE_MSG_MAP.put(key, new EnumBean<>(code, msg));
        }

        private static <K extends IBaseEnum<?>> EnumBean<?> get(K k) {
            return CODE_MSG_MAP.get(k);
        }

    }

    class EnumBean<T> {
        private final T code;
        private final String msg;

        private EnumBean(T code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

}
