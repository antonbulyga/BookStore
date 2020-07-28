package main.java.com.senla.model.utils.generators;

public class OrderIdGenerator {
        private static int orderId = 0;

        public static int getOrderId(){
            orderId++;
            return orderId;
        }

}
