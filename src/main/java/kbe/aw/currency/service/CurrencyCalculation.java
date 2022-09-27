package kbe.aw.currency.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import kbe.aw.currency.model.Currency;

@Service
public class CurrencyCalculation
{
   public double calculatePriceForCurrency(double price, String currency) throws CurrencyNotSupporterException
   {
      double priceWithCurrency;

      if(Arrays.stream(Currency.values()).noneMatch(currency1 -> currency1.name().contains(currency)))
      {
         throw new CurrencyNotSupporterException(currency);
      }
      else
      {
         priceWithCurrency = price * Currency.valueOf(currency).getCurrency();
      }

      return priceWithCurrency;
   }

}
