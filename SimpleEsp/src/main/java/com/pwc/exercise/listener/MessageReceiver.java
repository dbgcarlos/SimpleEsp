package com.pwc.exercise.listener;

import java.io.ByteArrayInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.exception.SimpleEspException;
import com.pwc.exercise.service.JsonPackageGeneraterService;
import com.pwc.exercise.service.MongoService;
import com.pwc.exercise.service.ParserService;

public class MessageReceiver implements MessageListener {
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Autowired
	private ParserService parserService;
	@Autowired
	private MongoService mongoService;
	@Autowired
	private JsonPackageGeneraterService jsonService;

	private static final Log log = LogFactory.getLog(MessageReceiver.class);

	/**
	 * show the message received then send a new message to RabbitMQ
	 * */
	public void onMessage(Message message) {
		
		log.info("receive a message:\n" + new String(message.getBody()));
		String receivedMessage = new String(message.getBody());
		
		// once received message,parse it
		FileObject received_fo = null;
		
		try {
			received_fo = this.parserService.parserXmlFile(new ByteArrayInputStream(
					message.getBody()));
		} catch (SimpleEspException e1) {
			amqpTemplate.convertAndSend("error",receivedMessage);
			log.error(e1);
			return;
		}
		
		try {
			
			// save in mongoDB
			this.mongoService.dataInMongo(received_fo);

			// retrieving data from mongoDB and reassemble the message as a json
			// package
			this.jsonService.sendJsonPackage(this.mongoService.dataOutofMongo(received_fo.getPackage_id()));
		} catch (SimpleEspException e) {
			log.error(e);
		}
		
	}

}
