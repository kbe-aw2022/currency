package kbe.aw.currency.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration
{
   //Currency
   public static final String REQUEST_CURRENCY_CALCULATION_QUE = "request_currency_calculation_que";
   public static final String REQUEST_CURRENCY_CALCULATION_EXCHANGE = "request_currency_calculation_exchange";

   public static final String MESSAGE_ROUTING_KEY = "message_routingKey";

   @Bean
   public MessageConverter messageConverter()
   {
      return new Jackson2JsonMessageConverter();
   }

   @Bean
   public AmqpTemplate template(ConnectionFactory connectionFactory)
   {
      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(messageConverter());
      return rabbitTemplate;
   }

   private static class RequestCurrencyCalculationConfiguration
   {
      @Bean
      @Qualifier(REQUEST_CURRENCY_CALCULATION_QUE)
      public Queue request_currency_calculation_que()
      {
         return new Queue(REQUEST_CURRENCY_CALCULATION_QUE);
      }


      @Bean
      @Qualifier(REQUEST_CURRENCY_CALCULATION_EXCHANGE)
      public TopicExchange request_currency_calculation_exchange()
      {
         return new TopicExchange(REQUEST_CURRENCY_CALCULATION_EXCHANGE);
      }

      @Bean
      public Binding binding_request_currency_calculation_with_exchange(@Qualifier(REQUEST_CURRENCY_CALCULATION_QUE) Queue queue,
            @Qualifier(REQUEST_CURRENCY_CALCULATION_EXCHANGE) TopicExchange exchange)
      {
         return BindingBuilder
               .bind(queue)
               .to(exchange)
               .with(MESSAGE_ROUTING_KEY);
      }
   }
}
