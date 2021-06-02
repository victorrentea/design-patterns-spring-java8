package victor.training.patterns.behavioral.observer.invoice;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import victor.training.patterns.behavioral.observer.events.OrderPlacedEvent;

@Service
public class InvoiceService {
   @EventListener
   @Order(20)

   public void generateInvoice(OrderPlacedEvent orderPlacedEvent) {
      System.out.println("Generating invoice for order id: " + orderPlacedEvent.getOrderId());
      // TODO what if...
      // throw new RuntimeException("thrown from generate invoice");
   }
}
