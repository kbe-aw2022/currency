package kbe.aw.currency.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Currency
{
   EUR(1),
   USD(1.10),
   JPY(2),
   GBP(0.92),
   RUB(88);

   private final double currency;

   Currency(final double currency)
   {
      this.currency = currency;
   }
}
