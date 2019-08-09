package com.massivecraft.blessedwar.engine;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.BWPlayer;
import com.massivecraft.massivecore.Engine;

import org.bukkit.entity.Entity;
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
        Entity killer = event.getEntity().getKiller();

        // Killer must be Player
        if(killer == null) return;

        BWPlayer bwPlayer = BWPlayer.get(killer);
        String alignId = bwPlayer.getAlignmentId();

        if(alignId == null) return;


        // Check what type of entity died ;-;
        // is the entity a player?
        if(entity instanceof Player)
        {
            BWPlayer eBWPlayer = BWPlayer.get(entity);

            // Same IP
            if(bwPlayer.getIp().equals(eBWPlayer.getIp())) return;

            // Check alignment if same
            if(bwPlayer.getAlignmentId().equals(eBWPlayer.getAlignmentId())) return;

            Alignment.get(alignId).addToAlignPlayerKillCount(1);
            return;
        }

        // is this a hostile mob?
        if(entity instanceof Monster)
        {
            Alignment.get(alignId).addToAlignMobKillCount(1);
        }

    }
}
