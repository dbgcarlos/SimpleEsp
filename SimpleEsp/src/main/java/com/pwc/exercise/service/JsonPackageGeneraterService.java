package com.pwc.exercise.service;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.exception.SimpleEspException;

public class JsonPackageGeneraterService {
	@Autowired
	private AmqpTemplate amqpTemplate;
	private static final Log log = LogFactory.getLog(ParserService.class);
	private ObjectMapper mapper = new ObjectMapper();

	// private ParserService ps;

	/**
	 * cover the File object into json package and sent
	 * @throws JsonGenerationException 
	 * 
	 * @throws IOException
	 * */
	public void sendJsonPackage(FileObject send_fo) throws SimpleEspException {
		if(null == send_fo){
			amqpTemplate.convertAndSend("error","no json package generated.");
		}else{
			try {
				log.info("the generated Json package like this: ");
				log.info(mapper.writeValueAsString(send_fo));
				log.info("send json package:\n" + mapper.writeValueAsString(send_fo));
				amqpTemplate.convertAndSend("successful",mapper.writeValueAsString(send_fo));
			} catch (JsonGenerationException e) {
				throw new SimpleEspException(e);
			} catch (JsonMappingException e) {
				throw new SimpleEspException(e);
			} catch (AmqpException e) {
				throw new SimpleEspException(e);
			} catch (IOException e) {
				throw new SimpleEspException(e);
			}
			//amqpTemplate.convertAndSend("ddd", mapper.writeValueAsString(send_fo));
			//amqpTemplate.convertAndSend("pwc", mapper.writeValueAsString(send_fo));
		}
		
	
	}

	public AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

}
