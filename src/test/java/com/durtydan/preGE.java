package com.durtydan;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class preGE
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ExamplePlugin.class);
		RuneLite.main(args);
	}
}