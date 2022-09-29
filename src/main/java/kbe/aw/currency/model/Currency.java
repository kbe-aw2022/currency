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
   RUB(88),
   BTC(0.00005113438750089178),
   COP(4341.998665557854),
   HUF(417.89312858993947),
   ILS(3.434834741911153),
   INR(79.15562689771211),
   ISK(140.0845146691937),
   JMD(144.97176397779796),
   MNT(3177),
   PLN(4.820430502639875),
   SCR(13.173303421200224),
   SEK(10.873479412845455),
   SGD(1.3895217281992767),
   THB(36.93350029976598),
   TRY(17.90695650492196),
   CRC(605.0794766666022),
   VND(22996.112712011913),
   ZAR(17.417661051695127);

   private final double currency;

   Currency(final double currency)
   {
      this.currency = currency;
   }
}
