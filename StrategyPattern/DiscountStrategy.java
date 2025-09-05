package StrategyPattern;
import DataModels.*;

/**
 * Interface สำหรับกลยุธ์ส่วนลด
 */

public interface DiscountStrategy {
    double applyDiscount(Order order);
} 
