package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.MPlayer;
import com.massivecraft.massivecore.Engine;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;

public class EngineEntityDeath extends Engine {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static EngineEntityDeath i = new EngineEntityDeath();
    public  static EngineEntityDeath get() { return i; }

    // -------------------------------------------- //
    // ENTITY DEATH
    // -------------------------------------------- //

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDeath(final EntityDeathEvent event)
    {
        // We aren't gonna count anything if the War isn't active.
        if(!MConf.get().blessedWarActive) return;

        // get the player that killed.
        LivingEntity entity = event.getEntity();
        MPlayer mPlayer = MPlayer.get(event.getEntity().getKiller());
        String alignId = mPlayer.getAlignmentId();

        if(alignId == null) return;


        // Check what type of entity died ;-;
        // is the entity a player?
        if(entity instanceof Player)
        {
            // Same IP
            if(mPlayer.getIp().equals(MPlayer.get(entity).getIp())) return;

            // Check alignment if same
            if(mPlayer.getAlignmentId().equals(MPlayer.get(entity).getAlignmentId())) return;

            Alignment.get(alignId).addToAlignPlayerKillCount(1);
            return;
        }

        // is this a hostile mob?
        if(entity instanceof Monster)
        {
            Alignment.get(alignId).addToAlignMobKillCount(1);
            return;
        }

        return;

    }
}
