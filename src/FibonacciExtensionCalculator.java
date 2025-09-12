import java.util.HashMap;
import java.util.Map;

public class FibonacciExtensionCalculator {

    // 计算斐波那契扩展水平（用于上涨趋势）
    public static Map<Double, Double> calculateFibonacciExtensionsUp(double low, double high, double retracementLow) {
        Map<Double, Double> extensions = new HashMap<>();
        double range = high - low;

        // 常用斐波那契扩展水平
        extensions.put(0.0, retracementLow);                  // 起点
        extensions.put(0.236, retracementLow + 0.236 * range); // 23.6% 扩展
        extensions.put(0.382, retracementLow + 0.382 * range); // 38.2% 扩展
        extensions.put(0.500, retracementLow + 0.500 * range); // 50.0% 扩展
        extensions.put(0.618, retracementLow + 0.618 * range); // 61.8% 扩展 (关键水平)
        extensions.put(0.786, retracementLow + 0.786 * range); // 78.6% 扩展
        extensions.put(1.000, retracementLow + 1.000 * range); // 100% 扩展
        extensions.put(1.272, retracementLow + 1.272 * range); // 127.2% 扩展
        extensions.put(1.414, retracementLow + 1.414 * range); // 141.4% 扩展
        extensions.put(1.618, retracementLow + 1.618 * range); // 161.8% 扩展 (黄金比例)
        extensions.put(2.000, retracementLow + 2.000 * range); // 200% 扩展
        extensions.put(2.618, retracementLow + 2.618 * range); // 261.8% 扩展

        return extensions;
    }

    // 计算斐波那契扩展水平（用于下跌趋势）
    public static Map<Double, Double> calculateFibonacciExtensionsDown(double high, double low, double retracementHigh) {
        Map<Double, Double> extensions = new HashMap<>();
        double range = high - low;

        // 常用斐波那契扩展水平（下跌方向）
        extensions.put(0.0, retracementHigh);                  // 起点
        extensions.put(0.236, retracementHigh - 0.236 * range); // 23.6% 扩展
        extensions.put(0.382, retracementHigh - 0.382 * range); // 38.2% 扩展
        extensions.put(0.500, retracementHigh - 0.500 * range); // 50.0% 扩展
        extensions.put(0.618, retracementHigh - 0.618 * range); // 61.8% 扩展 (关键水平)
        extensions.put(0.786, retracementHigh - 0.786 * range); // 78.6% 扩展
        extensions.put(1.000, retracementHigh - 1.000 * range); // 100% 扩展
        extensions.put(1.272, retracementHigh - 1.272 * range); // 127.2% 扩展
        extensions.put(1.414, retracementHigh - 1.414 * range); // 141.4% 扩展
        extensions.put(1.618, retracementHigh - 1.618 * range); // 161.8% 扩展 (黄金比例)
        extensions.put(2.000, retracementHigh - 2.000 * range); // 200% 扩展
        extensions.put(2.618, retracementHigh - 2.618 * range); // 261.8% 扩展

        return extensions;
    }

    // 根据两个已知扩展水平反推原始高点和低点
    public static double[] calculateHighLowFromTwoExtensions(double ratio1, double price1,
                                                             double ratio2, double price2,
                                                             double retracementPoint) {
        // 计算价格差和比率差
        double priceDiff = price1 - price2;
        double ratioDiff = ratio1 - ratio2;

        // 计算价格范围
        double range = priceDiff / ratioDiff;

        // 计算原始低点（对于上涨扩展）
        double low = retracementPoint - range;

        // 计算原始高点
        double high = low + range;

        return new double[]{high, low};
    }

    public static void main(String[] args) {
        // 示例1: 计算上涨趋势的斐波那契扩展水平
        double low = 107211.4;        // 原始低点
        double high = 117397.4;       // 原始高点
        double retracementLow = 110000.0; // 回调低点

        System.out.println("上涨趋势的斐波那契扩展水平:");
        System.out.println("原始低点: " + low);
        System.out.println("原始高点: " + high);
        System.out.println("回调低点: " + retracementLow);

        Map<Double, Double> extensionsUp = calculateFibonacciExtensionsUp(low, high, retracementLow);
        for (Map.Entry<Double, Double> entry : extensionsUp.entrySet()) {
            System.out.printf("%.3f: %.1f%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n----------------------------\n");

        // 示例2: 计算下跌趋势的斐波那契扩展水平
        double high2 = 117397.4;        // 原始高点
        double low2 = 107211.4;         // 原始低点
        double retracementHigh = 115000.0; // 回调高点

        System.out.println("下跌趋势的斐波那契扩展水平:");
        System.out.println("原始高点: " + high2);
        System.out.println("原始低点: " + low2);
        System.out.println("回调高点: " + retracementHigh);

        Map<Double, Double> extensionsDown = calculateFibonacciExtensionsDown(high2, low2, retracementHigh);
        for (Map.Entry<Double, Double> entry : extensionsDown.entrySet()) {
            System.out.printf("%.3f: %.1f%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n----------------------------\n");

        // 示例3: 根据两个已知扩展水平反推原始高点和低点
        double ratio1 = 1.618;
        double price1 = 125000.0;
        double ratio2 = 1.000;
        double price2 = 120000.0;
        double retracementPoint = 110000.0;

        System.out.println("根据两个扩展水平反推原始高点和低点:");
        System.out.printf("水平 %.3f: %.1f%n", ratio1, price1);
        System.out.printf("水平 %.3f: %.1f%n", ratio2, price2);
        System.out.println("回调点: " + retracementPoint);

        double[] highLow = calculateHighLowFromTwoExtensions(ratio1, price1, ratio2, price2, retracementPoint);
        System.out.printf("计算得到的原始高点: %.1f%n", highLow[0]);
        System.out.printf("计算得到的原始低点: %.1f%n", highLow[1]);

        // 验证计算结果
        System.out.println("\n验证计算结果:");
        Map<Double, Double> verifiedExtensions = calculateFibonacciExtensionsUp(highLow[1], highLow[0], retracementPoint);
        for (Map.Entry<Double, Double> entry : verifiedExtensions.entrySet()) {
            System.out.printf("%.3f: %.1f%n", entry.getKey(), entry.getValue());
        }
    }
}
