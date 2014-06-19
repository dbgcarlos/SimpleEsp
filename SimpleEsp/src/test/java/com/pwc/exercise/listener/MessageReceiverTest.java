package com.pwc.exercise.listener;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.amqp.core.Message;

import com.google.common.io.Files;
import com.pwc.exercise.listener.MessageReceiver;

public class MessageReceiverTest {

	@Test
	public void testOnMessage() throws IOException {
		MessageReceiver ms = new MessageReceiver();
		File f = new File("./src/test/resources/example_test.txt");
		Message message = new Message(Files.toByteArray(f), null);
		try{
			ms.onMessage(message);
			fail("no exception captured.");
		}catch(Exception e){
			assertTrue(true);
		}
	}

}
