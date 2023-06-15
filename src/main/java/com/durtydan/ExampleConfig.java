package com.durtydan;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Options")
public interface ExampleConfig extends Config
{
    @ConfigItem(
            keyName = "sellmode",
            name = "Sell Mode",
            description = "Enable the ability to sell on the Grand Exchange"
    )
    default boolean sellmode()
    {
        return false;
    }
}