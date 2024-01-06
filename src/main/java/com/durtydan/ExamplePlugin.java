package com.durtydan;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

@Slf4j
@PluginDescriptor(
	name = "Runeman Mode"
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

    @Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private ClientThread clientThread;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Runeman Mode started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Runeman Mode stopped!");
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		String target = Text.removeTags(event.getMenuTarget());
		//log.info(event.getMenuEntry().getOption());
		if (event.getMenuEntry().getOption().equals("Create <col=ff9040>Buy</col> offer")==true)
		{
			event.consume();
			sendChatMessage("You cannot use the Grand Exchange in Runeman Mode");
			return;
		}
        if (event.getMenuEntry().getOption().equals("Create <col=ff9040>Sell</col> offer")==true)
        {
            event.consume();
            sendChatMessage("You cannot use the Grand Exchange in Runeman Mode");
            return;
        }else {
            return;
        }
    }

	private void sendChatMessage(String message) {
		chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.CONSOLE)
				.runeLiteFormattedMessage(message)
				.build());
	}
}
