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
	name = "Pre-GE"
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

    @Inject
    private ExampleConfig config;

    @Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private ClientThread clientThread;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Pre-GE started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Pre-GE stopped!");
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		String target = Text.removeTags(event.getMenuTarget());
		//log.info(event.getMenuEntry().getOption());
		if (event.getMenuEntry().getOption().equals("Create <col=ff9040>Buy</col> offer")==true)
		{
			event.consume();
            if (config.sellmode() == true) {
                sendChatMessage("Pre-GE sell mode prevents you from buying at the Grand Exchange.");
            }else {
                sendChatMessage("Pre-GE prevents you from using the Grand Exchange.");
            }
			return;
		}
        if (event.getMenuEntry().getOption().equals("Create <col=ff9040>Sell</col> offer")==true
                && config.sellmode() == false)
        {
            event.consume();
            sendChatMessage("Pre-GE prevents you from using the Grand Exchange.");
            return;
        }else {
            return;
        }
    }

	private void sendChatMessage(String message)
	{
		chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.CONSOLE)
				.runeLiteFormattedMessage(message)
				.build());
	}

    @Provides
    ExampleConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ExampleConfig.class);
    }
}
