package victor.training.patterns.structural.facade.facade.dto;

import victor.training.patterns.structural.facade.entity.Customer;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class CustomerDto {
   public Long id;
   @Size(min = 6)
   public String name;
   public String email;
   public Long countryId;
   public String creationDateStr;
   public LocalDate creationDate;

   public CustomerDto() {
   }

   public CustomerDto(String name, String email) {
      this.name = name;
      this.email = email;
   }

   public CustomerDto(Customer customer) {
      name = customer.getName();
      email = customer.getEmail();
      creationDateStr = new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreationDate());
      id = customer.getId();
   }
}
