package StrategyPattern;
import DataModels.Order;

/**
 * กลยุทธ์ส่วนลดตามจำนวนเงินคงที่
 */
public class FixedDiscount implements DiscountStrategy {
    private final double amout;

    public FixedDiscount(double amout){
        this.amout = amout;
    }

    @Override
    public double applyDiscount(Order order) {
        return Math.max(0, order.getTotalPrice() - amout);
    }
    
}
