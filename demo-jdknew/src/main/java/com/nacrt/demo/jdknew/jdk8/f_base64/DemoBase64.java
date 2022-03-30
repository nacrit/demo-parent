package com.nacrt.demo.jdknew.jdk8.f_base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/**
 * DemoBase64
 *
 * @author zhenghao
 * @date 2022/3/30 10:27
 */
public class DemoBase64 {
    /**
     * 在Java 8中，Base64编码已经成为Java类库的标准。<br />
     * Java 8 内置了 Base64 编码的编码器和解码器。<br /><br />
     * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
     * <ul>
     *     <li>基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。</li>
     *     <li>URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。</li>
     *     <li>MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。</li>
     * </u>
     */
    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo2() {
        String str = "在Java 8中，Base64编码已经成为Java类库的标准，❤❥웃유♋☮✌☏☢☠✔☑♚。";
//        String str = IntStream.rangeClosed(0, 200).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.joining());

        String encode = Base64Util.encode(str);
        System.out.println("encode = " + encode);
        System.out.println("decode = " + Base64Util.decode(encode));
        ;

        String urlEncode = Base64Util.urlEncode(str);
        System.out.println("urlEncode = " + urlEncode);
        System.out.println("urlDecode = " + Base64Util.urlDecode(urlEncode));
        ;

        String mimeEncode = Base64Util.mimeEncode(str);
        System.out.println("mimeEncode = " + mimeEncode);
        System.out.println("mimeDecode = " + Base64Util.mimeDecode(mimeEncode));
        ;

    }

    private static void demo1() {
        // 使用基本编码
        String base64encodedString = Base64.getEncoder()
                .encodeToString("runoob?java8".getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
        // 解码
        byte[] base64decodedBytes = Base64.getDecoder()
                .decode(base64encodedString);
        System.out.println("原始字符串: " + new String(base64decodedBytes, StandardCharsets.UTF_8));

        // url编码
        base64encodedString = Base64.getUrlEncoder()
                .encodeToString("runoob?java8".getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);
        // 解码
        byte[] urlDecodeByte = Base64.getUrlDecoder().decode(base64encodedString);
        System.out.println("原始字符串 (URL) :" + new String(urlDecodeByte, StandardCharsets.UTF_8));

        // MIME编码
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID());
        }
        System.out.println("stringBuilder = " + stringBuilder);
        byte[] mimeBytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
        byte[] mineDecodeBytes = Base64.getMimeDecoder().decode(mimeEncodedString);
        System.out.println("原始字符串(MIME)：" + new String(mineDecodeBytes, StandardCharsets.UTF_8));
    }
}
