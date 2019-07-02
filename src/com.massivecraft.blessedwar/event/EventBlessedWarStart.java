package com.massivecraft.blessedwar.event;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class EventBlessedWarStart extends EventBlessedWarAbstractSender
{
    // -------------------------------------------- //
    // REQUIRED EVENT CODE
    // -------------------------------------------- //

    private static final HandlerList handlers = new HandlerList();
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //


    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public EventBlessedWarStart(CommandSender sender)
    {
        super(sender);
    }

}
