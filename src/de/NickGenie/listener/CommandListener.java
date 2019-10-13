package de.NickGenie.listener;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		String message = event.getMessage().getContentDisplay();
		System.out.println("MessageEvent");
		
		if(event.isFromType(ChannelType.TEXT)) {
			TextChannel channel = event.getTextChannel();
			
			if(message.startsWith("/Welcome ")) {
				String[] args = message.substring(5).split("  ");
				
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("hi")) {
						channel.sendMessage("Hallo" + event.getMember().getAsMention() + "!").queue();
						
					}
				}
				
			}
		}
		
	}
	
}
