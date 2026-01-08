import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {

    private final Queue<Order> orders = new LinkedList<>();

    // Producer method
    public synchronized void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order placed: Order ID " + order.getOrderId());
        notifyAll(); // notify waiting delivery agents
    }

    // Consumer method
    public synchronized Order getOrder() {
        while (orders.isEmpty()) {
            try {
                wait(); // wait until an order is available
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return orders.poll();
    }
}
