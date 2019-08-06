package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.AFaction;
import com.massivecraft.factions.event.EventFactionsDisband;
import com.massivecraft.massivecore.Engine;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class EngineFactionDisband extends Engine
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static EngineFactionDisband i = new EngineFactionDisband();
    public  static EngineFactionDisband get() { return i; }

    // -------------------------------------------- //
    // FACTION DISBAND
    // -------------------------------------------- //

    @EventHandler(priority = EventPriority.HIGH)
    public void onFactionDisband(final EventFactionsDisband event)
    {
        AFaction afaction = AFaction.get(event.getFaction());

        // CHECK IF FACTION WASNT ALIGNED
        if(afaction.getAlignment() == null)
        {
            // detach afaction from db
            afaction.detach();

            return;
        }

        // remove faction from alignment
        Alignment.get(afaction.getAlignmentId())
                .removeFaction(event.getFactionId());

        // detach afaction from db
        afaction.detach();

    }
}
