package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.MPlayer;
import com.massivecraft.blessedwar.entity.AFaction;
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
        MPlayer aMPlayer = MPlayer.get(event.getMPlayer());

        // Check if the membershipchange is a join.
        if(event.getReason() == EventFactionsMembershipChange.MembershipChangeReason.JOIN)
        {
            AFaction afaction = AFaction.get(event.getNewFaction());

            // check if the player joining is apart of a religion
            if (!aMPlayer.getAlignmentId().equals(null))
            {
                aMPlayer.setAlignmentId(afaction.getAlignmentId());
            }
        }
    }
}
