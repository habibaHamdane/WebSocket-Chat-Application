package hh.ws.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//configurer Spring pour activer la messagerie WebSocket et STOMP
@Configuration
//Activer une messagerie soutenue par un courtier sur WebSocket à l'aide de STOMP
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	//"setAllowedOrigins()"si le client et le côté serveur utilisent des domaines différents,
	// cette méthode doit être appelée pour permettre la communication entre eux

	//"withSockJS()" active les options de secours SockJS.
	//permettra à nos WebSockets de fonctionner même si le protocole WebSocket n'est pas pris en charge par un navigateur Internet
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
	}

	//Crée le courtier de messages en mémoire avec une ou plusieurs destinations pour l'envoi et la réception de messages
	//Définit le préfixe "app" utilisé pour filtrer les destinations gérées par des méthodes annotées avec @MessageMapping implémentées dans le contrôleur
	//Le préfixe de destination est définis par "topic"
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
	}
}
