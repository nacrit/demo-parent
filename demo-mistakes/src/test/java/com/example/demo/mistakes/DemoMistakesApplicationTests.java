package com.example.demo.mistakes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoMistakesApplicationTests {


    public static void main(String[] args) {
//        md5File();
        int sum = add(0x7fffffff, 0);
        System.out.println(sum);
        int sum2 = add2(0x7fffffff, 1);
        System.out.println(sum2);
    }

    private static int add(int a, int b) {
        // a + b ==> a+b不计进位的值 + a+b进位的值 ==> a+b的进位为0时 a^b就是a+b的和
//        return (a & b) << 1 == 0 ? a ^ b : add(a ^ b, (a & b) << 1);
        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }

    private static int add2(int a, int b) {
        int sum;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return a;
    }

    public static void md5File() {
//        MD5 md5 = MD5.create();
//        byte[] digest = md5.digest(new File("D:\\workspace\\demo\\demo-error\\src\\test\\java\\com\\java\\error\\example\\demo\\error\\demo02\\InterestingTest.java"));
//        System.out.println(Arrays.toString(digest));
//        System.out.println(bytesToHex(digest));
//        byte[] digest2 = md5.digest(new File("D:\\workspace\\demo\\demo-error\\src\\test\\java\\com\\java\\error\\example\\demo\\error\\demo02\\InterestingTest2222.java"));
//        System.out.println(Arrays.toString(digest2));
//        System.out.println(bytesToHex(digest2));
    }

    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            System.out.println(aByte & 0xFF);
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex).append("|");
        }
        return sb.toString();
    }

    @Test
    void contextLoads() {
    }
}
