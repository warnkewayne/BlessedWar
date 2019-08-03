package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.aFaction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsDisband;
import com.massivecraft.massivecore.Engine;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;

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
        aFaction afaction = aFaction.get(event.getFaction());

        // CHECK IF FACTION WASNT ALIGNED
        if(afaction.getAlignment() == null)
        {
            // detach afaction from db
            afaction.detach();

            return;
        }

        List<MPlayer> members = event.getFaction().getMPlayers();

        for(MPlayer member : members)
        {
            // set each faction member's faction FK to null
            member.setFactionId(null);
        }

        // remove faction from alignment
        Alignment.get(afaction.getAlignmentId())
                .removeFaction(event.getFactionId());

        // detach afaction from db
        afaction.detach();

        return;
    }
}
