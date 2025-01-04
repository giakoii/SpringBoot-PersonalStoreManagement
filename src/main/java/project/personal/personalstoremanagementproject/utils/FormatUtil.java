package project.personal.personalstoremanagementproject.utils;

/**
 * Utility class for formatting
 */
public class FormatUtil {

    /**
     * Format money to VND
     * @param money the money to format
     * @return the formatted money
     */
    public static String moneyFormat(long money) {
        return String.format("%,d VND", money);
    }

    /**
     * Format money to VND
     * @param money the money to format
     * @return the formatted money
     */
    public static String moneyFormat(int money) {
        return String.format("%,d VND", money);
    }

    /**
     * Format money to VND
     * @param money the money to format
     * @return the formatted money
     */
    public static String moneyFormat(double money) {
        return String.format("%,.2f VND", money);
    }

    /**
     * Format money to VND
     * @param money the money to format
     * @return the formatted money
     */
    public static String moneyFormat(float money) {
        return String.format("%,.2f VND", money);
    }
}
