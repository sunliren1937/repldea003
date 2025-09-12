import java.util.HashMap;
import java.util.Map;

public class FibonaccCalulator1 {
    // 计算斐波那契扩展水平（用于上涨趋势）
    public static Map<Double, Double> calculateFibonacciExtensionsUp(double low, double high) {
        Map<Double, Double> extensions = new HashMap<>();
        double range = high - low;

        // 常用斐波那契扩展水平

        extensions.put(0.236, high - (1-0.236) * range); // 23.6% 扩展
        extensions.put(0.382, high - (1-0.382) * range); // 38.2% 扩展
        extensions.put(0.500, high - (1-0.500) * range); // 50.0% 扩展
        extensions.put(0.618, high - (1-0.618) * range); // 61.8% 扩展 (关键水平)
        extensions.put(0.786, high - (1-0.786) * range); // 78.6% 扩展
        extensions.put(1.000, high - (1-1.000) * range); // 100% 扩展


        return extensions;
    }
    // 根据两个已知回调水平反推高点和低点
    public static double[] calculateHighLowFromTwoLevels(double ratio1, double price1,
                                                         double ratio2, double price2) {
        // 计算价格差和比率差
        double priceDiff = price1 - price2;
        double ratioDiff = -ratio1 + ratio2;

        // 计算价格范围
        double range = priceDiff / ratioDiff;

        double high=price2+ratio2*range;
        double low=high-range;



        return new double[]{high, low};
    }
    public static void main(String[] args) {
        // 示例1: 根据已知高点和低点计算反弹水平
        double high = 113082.561;
        double low = 107373.239;

        System.out.println("已知高点和低点计算反弹水平:");
        System.out.println("高点: " + high);
        System.out.println("低点: " + low);

        Map<Double, Double> levels = calculateFibonacciExtensionsUp(high, low);
        for (Map.Entry<Double, Double> entry : levels.entrySet()) {
            System.out.printf("%.3f: %.1f%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n----------------------------\n");

        // 示例2: 根据两个已知反弹水平反推高点和低点
        double ratio1 = 0.5;
        double price1 = 110277.9;
        double ratio2 = 0.618;
        double price2 = 109554.2;

        System.out.println("根据两个反弹水平反推高点和低点:");
        System.out.printf("水平 %.3f: %.1f%n", ratio1, price1);
        System.out.printf("水平 %.3f: %.1f%n", ratio2, price2);

        double[] highLow = calculateHighLowFromTwoLevels(ratio1, price1, ratio2, price2);
        System.out.printf("计算得到的高点: %.1f%n", highLow[0]);
        System.out.printf("计算得到的低点: %.1f%n", highLow[1]);

        // 验证计算结果
        System.out.println("\n验证计算结果:");
        Map<Double, Double> verifiedLevels = calculateFibonacciExtensionsUp(highLow[0], highLow[1]);
        for (Map.Entry<Double, Double> entry : verifiedLevels.entrySet()) {
            System.out.printf("%.3f: %.1f%n", entry.getKey(), entry.getValue());
        }
    }
}
