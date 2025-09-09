package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.PaymentCardDto;
import wanderer2357.redwire.model.ClientEntity;
import wanderer2357.redwire.model.PaymentCardEntity;

@Component
public class PaymentCardEntityMapper implements Function<PaymentCardDto, PaymentCardEntity>{

	@Override
	public PaymentCardEntity apply(PaymentCardDto paymentCardDto) {
		return new PaymentCardEntity(new ClientEntity(paymentCardDto.getClientId()),
				paymentCardDto.getPaymentCardType(),
				paymentCardDto.getLastFourDigits(),
				paymentCardDto.getExpirationMonth(),
				paymentCardDto.getExpirationYear(),
				paymentCardDto.getCardHolderName(),
				null);
	}

}
