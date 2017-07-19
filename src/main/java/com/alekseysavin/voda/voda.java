package com.alekseysavin.voda;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Created by User on 11.07.2017.
 * Updated by User on 19.07.2017.
 */
public class voda extends TelegramLongPollingBot {
    private long ChatId;
    private String[] inString;
    private HashMap<String, Integer> vodaList;

    public void onUpdateReceived(Update update) {

        ArrayList<String> messages = new ArrayList<String>();
        if (update.hasMessage() && update.getMessage().hasText()) {
            messages.add(update.getMessage().getText());
        }

        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                ChatId = update.getMessage().getChatId(); //ChatId
                String CurrentInMessage = update.getMessage().getText(); // CurrentInMessage
                SendMessage message = new SendMessage();
                message.setChatId(ChatId);
  
                if (CurrentInMessage.equals("/status")) {
                    message.setChatId(ChatId);
                    message.setText("Ok, is is status;");
                    //need update
                    for (Entry<String, Integer> voda : vodaList.entrySet()) {
						message.setText(voda.getKey() + " " + voda.getValue());
					}                    
                    sendMessage(message);
                } else if (CurrentInMessage.startsWith("+")) {
                	//need update
                	inString = update.getMessage().getText().split(" ");
                		for (int i = 0; i < inString.length; i++) {
                			vodaList.put(inString[1], Integer.parseInt(inString[2]));
                		}
                }
            }
        } catch (Exception e) {
        	SendMessage message = new SendMessage();
        	message.setChatId(ChatId);
            message.setText(e.toString());
            e.printStackTrace();
        }
    }
    
    
	public voda() {
        super();
    }

    public String getBotToken() {
        return "380086304:AAGS86JUVGuHNlY43IIYiitGC6ascrHhoyg";
    }

    public String getBotUsername() {
        return "voda_bot";
    }

    public voda(DefaultBotOptions options) {
        super(options);
    }
}
