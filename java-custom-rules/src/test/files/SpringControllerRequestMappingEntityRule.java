import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.persistence.Entity;

@Controller
public class HelloController {

  @RequestMapping("/updateOrder") 
  public String updateOrder(Order order) { // Noncompliant [[sc=29;ec=34]] {{Don't use Order here because it's an @Entity}}
    return null;
  }

  @Entity
  public class Order {
    String ordered;
  }
}
