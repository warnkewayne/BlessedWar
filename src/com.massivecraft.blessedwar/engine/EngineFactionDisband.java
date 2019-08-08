package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.AFaction;
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
        AFaction afaction = AFaction.get(event.getFaction());
        Alignment afactionAlignment = afaction.getAlignment();

        // CHECK IF FACTION WASNT ALIGNED
        if(afactionAlignment == null)
        {
            // detach afaction from db
            afaction.detach();

            return;
        }

        // remove faction from alignment
        Alignment.get(afaction.getAlignmentId())
                .removeFaction(event.getFactionId());

        // Get all members of the Faction
        List<MPlayer> members = afaction.getFaction().getMPlayers();

        for(MPlayer member : members)
        {
            com.massivecraft.blessedwar.entity.MPlayer bwPlayer =
                    com.massivecraft.blessedwar.entity.MPlayer.get(member.getId());

            bwPlayer.leaveAlignment(); //Player entity leaves Alignment
            afactionAlignment.removePlayer(bwPlayer.getId()); // Remove player from Alignment's list
        }

        // detach afaction from db
        afaction.detach();

    }
}
