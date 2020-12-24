package hh.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import hh.ws.model.MessageModel;
import hh.ws.storage.UserStorage;

//Implémentez un contrôleur qui gérera les demandes des utilisateurs.
// Il diffusera le message reçu à tous les utilisateurs abonnés à un sujet donné.

@RestController
public class MessageController {

	//SimpMessagingTemplatece pour se connecter automatiquement à l'intérieur de contrôleur.
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to, MessageModel message) {
		System.out.println("handling send message: " + message + " to: " + to);
		boolean isExists = UserStorage.getInstance().getUsers().contains(to);
		if (isExists) {
			simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
		}
	}
}
