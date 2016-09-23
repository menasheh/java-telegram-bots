package com.peromsik.telegram.bots;

import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.impl.*;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.*;
import java.util.List;

public class SampleBot {

	public static void main(String[] args) {
		String token = "ACCESS_TOKEN_GOES_HERE";
		TelegramBot bot = TelegramBotAdapter.build(token);
		
		List<Update> firstUpdates = bot.execute(new GetUpdates()).updates();
		int msgId1 = 0;
		if(firstUpdates.size()>0) {
		msgId1 = firstUpdates.get(firstUpdates.size()-1).message().messageId();
		}
		
		while(true) {
		
			GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates());
			List<Update> updates = updatesResponse.updates();
			
			for(int i = 0; i<updates.size(); i++) {
										
				Update thisUpdate = updates.get(i);
				Message thisMessage = thisUpdate.message();
				if(thisMessage.messageId() <= msgId1){continue;}	
				msgId1 = thisMessage.messageId();
				String thisMessageText = thisMessage.text();
				User thisUser = thisMessage.from();
				long chatId = thisMessage.chat().id();
				String thisUserName = thisUser.firstName();
				
				String response = "Hey " + thisUserName + ", you said " + thisMessageText + "!";
				SendResponse sendResponse = bot.execute(
						new SendMessage(chatId, response)
						);
				
			}
			
		}	
		
	}
	
}

