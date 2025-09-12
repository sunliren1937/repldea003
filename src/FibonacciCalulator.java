import java.util.HashMap;
import java.util.Map;

public class FibonacciCalulator {
    // 计算斐波那契回调水平
    public static Map<Double, Double> calculateFibonacciLevels(double high, double low) {
        Map<Double, Double> levels = new HashMap<>();
        //计算高点和低点之间的价格差，这是斐波那契回调计算的基础。
        //回调价格 = 低点 + 回调比例 × 价格范围
        double range = high - low;
        // 常用斐波那契回调水平
        levels.put(0.236, low + 0.236 * range); //浅度回调水平，通常表示弱势回调
        levels.put(0.382, low + 0.382 * range);//0.382 (38.2%): 常见回调水平，表示中度回调
        levels.put(0.500, low + 0.500 * range);//0.500 (50.0%): 虽然不是严格的斐波那契比率，但在交易中广泛使用，表示价格回调一半
        levels.put(0.618, low + 0.618 * range);//0.618 (61.8%): 黄金分割比例，最重要的斐波那契回调水平
        levels.put(0.786, low + 0.786 * range);//0.786 (78.6%): 深度回调水平，接近0.786是平方根的倒数
        levels.put(0.886, low + 0.886 * range);//0.886 (88.6%): 极深度回调水平，接近趋势的起点
        return levels;
    }

    // 根据两个已知回调水平反推高点和低点
    public static double[] calculateHighLowFromTwoLevels(double ratio1, double price1,
                                                         double ratio2, double price2) {
        // 计算价格差和比率差
        double priceDiff = price1 - price2;
        double ratioDiff = ratio1 - ratio2;

        // 计算价格范围
        double range = priceDiff / ratioDiff;

        // 计算低点
        double low = price1 - ratio1 * range;

        // 计算高点
        double high = low + range;

        return new double[]{high, low};
    }

    public static void main(String[] args) {
        // 示例1: 根据已知高点和低点计算回调水平
        double high = 117397.4;
        double low = 107211.4;

        System.out.println("已知高点和低点计算回调水平:");
        System.out.println("高点: " + high);
        System.out.println("低点: " + low);

        Map<Double, Double> levels = calculateFibonacciLevels(high, low);
        for (Map.Entry<Double, Double> entry : levels.entrySet()) {
            System.out.printf("%.3f: %.1f%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n----------------------------\n");

        // 示例2: 根据两个已知回调水平反推高点和低点
        double ratio1 = 0.886;
        double price1 = 116236.2;
        double ratio2 = 0.786;
        double price2 = 115217.6;

        System.out.println("根据两个回调水平反推高点和低点:");
        System.out.printf("水平 %.3f: %.1f%n", ratio1, price1);
        System.out.printf("水平 %.3f: %.1f%n", ratio2, price2);

        double[] highLow = calculateHighLowFromTwoLevels(ratio1, price1, ratio2, price2);
        System.out.printf("计算得到的高点: %.1f%n", highLow[0]);
        System.out.printf("计算得到的低点: %.1f%n", highLow[1]);

        // 验证计算结果
        System.out.println("\n验证计算结果:");
        Map<Double, Double> verifiedLevels = calculateFibonacciLevels(highLow[0], highLow[1]);
        for (Map.Entry<Double, Double> entry : verifiedLevels.entrySet()) {
            System.out.printf("%.3f: %.1f%n", entry.getKey(), entry.getValue());
        }
    }
}

