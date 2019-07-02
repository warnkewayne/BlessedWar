package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.MPlayer;
import com.massivecraft.blessedwar.entity.aFaction;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.massivecore.Engine;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class EngineFactionJoin extends Engine {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static EngineFactionJoin i = new EngineFactionJoin();
    public static EngineFactionJoin get() { return i; }

    // -------------------------------------------- //
    // FACTION JOINED
    // -------------------------------------------- //

    @EventHandler(priority = EventPriority.HIGH)
    public void onFactionJoin(final EventFactionsMembershipChange event)
    {
        // Check if the membershipchange is a join.
        if(event.getReason() != EventFactionsMembershipChange.MembershipChangeReason.JOIN) return;

        MPlayer aMPlayer = MPlayer.get(event.getMPlayer());
        aFaction afaction = aFaction.get(event.getNewFaction());

        // check if the player joining is apart of a religion
        if(aMPlayer.getAlignment() != null)
        {
            aMPlayer.setAlignment(afaction.getAlignment());
        }
    }
}
