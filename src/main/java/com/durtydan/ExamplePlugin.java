package com.durtydan;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
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

		if (entryMatches(event, "Exchange", "Grand Exchange Clerk")
				|| entryMatches(event, "History", "Grand Exchange Clerk")
				|| entryMatches(event, "Sets", "Grand Exchange Clerk")
				|| entryMatches(event, "Talk-to", "Grand Exchange Clerk")
				|| entryMatches(event, "Exchange", "Grand Exchange booth")
		{
			event.consume();
			sendChatMessage("You don't seem interested.");
			return;

		}
	}

	private boolean entryMatches(MenuEntry entry, String option)
	{
		return entry.getOption().equals(option);
	}

	private boolean entryMatches(MenuOptionClicked event, String option)
	{
		return event.getMenuOption().equals(option);
	}

	private boolean entryMatches(MenuEntry entry, String option, String target)
	{
		return entryMatches(entry, option) && Text.removeTags(entry.getTarget()).equals(target);
	}

	private boolean entryMatches(MenuOptionClicked event, String option, String target)
	{
		return entryMatches(event, option) && Text.removeTags(event.getMenuTarget()).equals(target);
	}

	private void sendChatMessage(String message)
	{
		chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.CONSOLE)
				.runeLiteFormattedMessage(message)
				.build());
	}


}
