package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.BWFaction;
import com.massivecraft.blessedwar.entity.BWPlayer;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsDisband;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.massivecore.Engine;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;

public class EngineFactions extends Engine
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static EngineFactions i = new EngineFactions();
    public  static EngineFactions get() { return i; }
    
    // -------------------------------------------- //
    // FACTION JOINED
    // -------------------------------------------- //
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onFactionJoin(final EventFactionsMembershipChange event)
    {
        BWPlayer bwPlayer = BWPlayer.get(event.getMPlayer());
        
        // Check if the membership change is a join.
        if(event.getReason() == EventFactionsMembershipChange.MembershipChangeReason.JOIN)
        {
            BWFaction bwFaction = BWFaction.get(event.getNewFaction());
            String afacAlignID = bwFaction.getAlignmentId();
            
            // if faction does not have alignment, we can return.
            if(afacAlignID == null) return;
            
            // set player's alignment to faction's alignment
            bwPlayer.setAlignmentId(afacAlignID);
            Alignment.get(afacAlignID).addPlayer(bwPlayer.getId());
        }
    }

    // -------------------------------------------- //
    // FACTION DISBAND
    // -------------------------------------------- //

    @EventHandler(priority = EventPriority.HIGH)
    public void onFactionDisband(final EventFactionsDisband event)
    {
        BWFaction bwFaction = BWFaction.get(event.getFaction());
        Alignment bwFactionAlignment = bwFaction.getAlignment();

        // CHECK IF FACTION WASNT ALIGNED
        if(bwFactionAlignment == null)
        {
            // detach bwFaction from db
            bwFaction.detach();

            return;
        }

        // remove faction from alignment
        Alignment.get(bwFaction.getAlignmentId())
                .removeFaction(event.getFactionId());

        // Get all members of the Faction
        List<MPlayer> members = bwFaction.getFaction().getMPlayers();

        for(MPlayer member : members)
        {
            BWPlayer bwPlayer =
                    BWPlayer.get(member.getId());

            bwPlayer.leaveAlignment(); //Player entity leaves Alignment
            bwFactionAlignment.removePlayer(bwPlayer.getId()); // Remove player from Alignment's list
        }

        // detach afaction from db
        bwFaction.detach();

    }
}
