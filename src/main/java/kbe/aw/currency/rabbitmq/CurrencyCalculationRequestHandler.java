package kbe.aw.currency.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kbe.aw.currency.configuration.RabbitConfiguration;
import kbe.aw.currency.request.CurrencyCalculationRequest;
import kbe.aw.currency.service.CurrencyCalculation;
import kbe.aw.currency.service.CurrencyNotSupporterException;

@Component
public class CurrencyCalculationRequestHandler
{
   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private CurrencyCalculation currencyCalculation;

   @RabbitListener(queues = RabbitConfiguration.REQUEST_CURRENCY_CALCULATION_QUE)
   public String handlePriceCalculationRequest(CurrencyCalculationRequest request)
   {
      double priceWithoutCurrency = request.getPrice();
      String currency = request.getCurrency();

      try
      {
         return objectMapper.writeValueAsString(currencyCalculation.calculatePriceForCurrency(priceWithoutCurrency, currency));
      }
      catch (JsonProcessingException | CurrencyNotSupporterException e)
      {
         throw new RuntimeException(e);
      }
   }

}
