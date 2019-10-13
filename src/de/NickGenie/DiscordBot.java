package de.NickGenie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import de.NickGenie.listener.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class DiscordBot {

	public ShardManager shardMan;
	
	public static void main(String[] args)  {
		try {
	new DiscordBot();
} catch (LoginException | IllegalArgumentException e) {
	e.printStackTrace();
}
	}

	public DiscordBot() throws LoginException, IllegalArgumentException {
			DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
			builder.setToken("NTkxMDU2NjUzOTE5NDUzMTg1.XQra9A.v7_DRXb9OBZdjz9sV531FbZmXYQ");
			
			builder.setActivity(Activity.playing("arbeitet"));
			builder.setStatus(OnlineStatus.ONLINE);
			
			builder.addEventListeners(new CommandListener()); 
			
			shardMan = builder.build();
			System.out.println("Bot online.");
			
			shutdown();
	}
	
	public void shutdown() {
		
		new Thread(() -> {
			
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while((line = reader.readLine()) != null) {
					
					if(line.equalsIgnoreCase("exit")) {
						if(shardMan != null) {
							shardMan.setStatus(OnlineStatus.OFFLINE);
							shardMan.shutdown();
							System.out.println("Bot offline.");
						}
						
						reader.close();
					}
					else {
						System.out.println("Use 'exit' to shutdown.");
					}
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		
	}).start();
		
	}	
}