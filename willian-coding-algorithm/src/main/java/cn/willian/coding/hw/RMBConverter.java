package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-30 10:10:18
 */
// 1、中文大写金额数字前应标明“人民币”字样。中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整等字样填写。
// 2、中文大写金额数字到“元”为止的，在“元”之后，应写“整字，如532.00应写成“人民币伍佰叁拾贰元整”。在”角“和”分“后面不写”整字。
// 3、阿拉伯数字中间有“0”时，中文大写要写“零”字，阿拉伯数字中间连续有几个“0”时，中文大写金额中间只写一个“零”字，如6007.14，应写成“人民币陆仟零柒元壹角肆分”。
// 4、10应写作“拾”，100应写作“壹佰”。例如，1010.00应写作“人民币壹仟零拾元整”，110.00应写作“人民币壹佰拾元整”
// 5、十万以上的数字接千不用加“零”，例如，30105000.00应写作“人民币叁仟零拾万伍仟元整”
// 输入描述： 输入一个double数
// 输出描述： 输出人民币格式
public class RMBConverter {

    public static String[] TEN = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    public static String[] BILLION = {"万", "亿"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 将数据分隔成整数部分和小数部分
            String[] s = sc.nextLine().split("\\.");
            String number = s[0];
            String decimal = s[1];
            // 小数位为0
            if (decimal.equals("00")) {
                System.out.println("人民币" + convertInteger(Double.parseDouble(number)) + "元整");
                return;
            }
            // 整数位为0
            if (number.equals("0")) {
                System.out.println("人民币" + convertDecimal(decimal));
                return;
            }
            // 既有整数又有小数
            System.out.println("人民币" + convertInteger(Double.parseDouble(number)) + "元" + convertDecimal(decimal));
        }
    }

    private static String convertDecimal(String decimal) {

        StringBuilder sb = new StringBuilder();
        int jiao = Integer.parseInt(decimal.substring(0, 1));
        int fen = Integer.parseInt(decimal.substring(1, 2));
        if (jiao != 0) {
            sb.append(TEN[jiao]);
            sb.append("角");
        }
        if (fen != 0) {
            sb.append(TEN[fen]);
            sb.append("分");
        }
        return sb.toString();
    }

    private static String convertInteger(double number) {

        StringBuilder sb = new StringBuilder();
        int pow = 0;
        while ((int)number != 0) {
            if (pow != 0) {
                sb.append(BILLION[pow - 1]);
            }
            int bit = (int)(number % 10000);
            int ge = bit % 10;
            if (ge != 0) {
                sb.append(TEN[ge]);
            }
            int shi = (bit / 10) % 10;
            if (shi != 0) {
                sb.append("拾");
                if (shi != 1) {
                    sb.append(TEN[shi]);
                }
            } else {
                // 补零
                if (ge != 0 && (bit > 99 || (int)number > 10000)) {
                    sb.append(TEN[0]);
                }
            }
            int bai = (bit / 100) % 10;
            if (bai != 0) {
                sb.append("佰");
                sb.append(TEN[bai]);
            } else {
                // 补零
                if (shi != 0 && (bit > 999 || (int)number > 10000)) {
                    sb.append(TEN[0]);
                }
            }
            int qian = (bit / 1000) % 10;
            if (qian != 0) {
                sb.append("仟");
                sb.append(TEN[qian]);
            } else {
                // 补零
                if (bai != 0 && (int)number > 10000) {
                    sb.append(TEN[0]);
                }
            }
            number /= 10000;
            pow++;
            if (pow > 2) {
                pow = 1;
            }
        }
        return sb.reverse().toString();
    }
}
