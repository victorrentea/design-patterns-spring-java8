package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.entity.Email;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;
import victor.training.patterns.structural.facade.infra.EmailClient;
import victor.training.patterns.structural.facade.repo.CustomerRepo;
import victor.training.patterns.structural.facade.repo.EmailRepo;
import victor.training.patterns.structural.facade.repo.SiteRepo;
import victor.training.patterns.structural.facade.service.RegisterCustomerService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepo customerRepo;
	private final EmailClient emailClient;
	private final EmailRepo emailRepo;
	private final SiteRepo siteRepo;
	private final CustomerMapper mapper;
	private final RegisterCustomerService registerCustomerService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return new CustomerDto(customer);
	}

	public void register(@Validated CustomerDto dto) {
		Customer customer = mapper.convert(dto);

		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}

		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
		registerCustomerService.registerCustomer(customer);

		sendRegistrationEmail(customer.getEmail());
	}


	private void sendRegistrationEmail(String emailAddress) {
		System.out.println("Sending activation link via email to " + emailAddress);
		Email email = new Email();
		email.setFrom("noreply");
		email.setTo(emailAddress);
		email.setSubject("Welcome!");
		email.setBody("You'll like it! Sincerely, Team");

		if (!emailRepo.emailWasSentBefore(email.hashCode())) {
			emailClient.sendEmail(email.getFrom(), email.getTo(), email.getSubject(), email.getBody());
			emailRepo.saveSentEmail(email);
		}
	}
}
