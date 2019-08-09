package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.MPlayer;
import com.massivecraft.blessedwar.entity.MPlayerColl;
import com.massivecraft.massivecore.Engine;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerKickEvent;

public class EnginePlayerData extends Engine
{

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static EnginePlayerData i = new EnginePlayerData();
    public static EnginePlayerData get() { return i; }

    // -------------------------------------------- //
    // REMOVE PLAYER DATA WHEN BANNED
    // -------------------------------------------- //

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerKick(PlayerKickEvent event)
    {
        // If we remove player data when banned ...
        if ( ! MConf.get().removePlayerWhenBanned ) return;
        // ...and a player was kicked from the server ...
        Player player = event.getPlayer();

        // ... and if the if player was banned (not just kicked) ...
        //if (!event.getReason().equals("Banned by admin.")) return;
        if ( ! player.isBanned() ) return;

        // ... get rid of their stored info.
        MPlayer mplayer = MPlayerColl.get().get(player, false);
        if ( mplayer == null ) return;

        String alignmentId = mplayer.getAlignmentId();

        // ... if banned player is apart of the blessed war
        if ( alignmentId != null )
        {
            // remove their data from the alignment
            Alignment.get(alignmentId).removePlayer(mplayer.getId());
            mplayer.leaveAlignment();
        }

        mplayer.detach();
    }
}
