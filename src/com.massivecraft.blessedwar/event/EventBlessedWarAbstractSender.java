package com.massivecraft.blessedwar.event;

import com.massivecraft.blessedwar.entity.MPlayer;
import com.massivecraft.massivecore.event.EventMassiveCore;
import org.bukkit.command.CommandSender;

public abstract class EventBlessedWarAbstractSender extends EventMassiveCore
{
    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    private final CommandSender sender;
    public CommandSender getSender() { return this.sender; }
    public MPlayer getMPlayer() { return this.sender == null ? null : MPlayer.get(this.sender); }

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public EventBlessedWarAbstractSender(CommandSender sender)
    {
        this.sender = sender;
    }

    public EventBlessedWarAbstractSender(boolean async, CommandSender sender)
    {
        super(async);
        this.sender = sender;
    }


}
