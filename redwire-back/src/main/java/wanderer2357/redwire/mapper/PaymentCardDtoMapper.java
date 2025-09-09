package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.PaymentCardDto;
import wanderer2357.redwire.model.PaymentCardEntity;

@Component
public class PaymentCardDtoMapper implements Function<PaymentCardEntity, PaymentCardDto>{

	@Override
	public PaymentCardDto apply(PaymentCardEntity paymentCardEntity) {
		return new PaymentCardDto(paymentCardEntity.getId(),
				paymentCardEntity.getClientEntity().getId(),
				paymentCardEntity.getPaymentCardType(),
				paymentCardEntity.getLastFourDigits(),
				paymentCardEntity.getExpirationMonth(),
				paymentCardEntity.getExpirationYear(),
				paymentCardEntity.getCardHolderName(),
				null,
				paymentCardEntity.getStatus(),
				paymentCardEntity.getCreatedAt(),
				paymentCardEntity.getUpdatedAt());
	}

}
