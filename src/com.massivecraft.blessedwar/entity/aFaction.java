package com.massivecraft.blessedwar.entity;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

import java.util.List;

public class aFaction extends Entity<aFaction> {

    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    public static aFaction get(Faction faction) { return aFactionColl.get().get(faction.getId(), true); }

    //----------------------------------------------//
    // OVERRIDE
    //----------------------------------------------//

    @Override
    public aFaction load(aFaction that)
    {
        this.alignmentId = that.alignmentId;
        this.lastAlignChange = that.lastAlignChange;
        this.playersClaimed = that.playersClaimed;

        return this;
    }

    // -------------------------------------------- //
    // CORE METHODS
    // -------------------------------------------- //

    public Alignment getAlignment()
    {
        return Alignment.get(this.alignmentId);
    }

    // -------------------------------------------- //
    // FIELDS: RAW
    // -------------------------------------------- //

    // This is the field that we will use to "extend"
    // the Faction.
    // Default null

    private Faction faction = null;

    // This is the alignment the faction has chosen.
    // It is a foreign key.
    // Default: null

    private String alignmentId = null;

    // This is the cooldown timer.
    // Alignments are only allowed to switch
    // once a week.
    // Default: currentTime

    private double lastAlignChange = (double) System.currentTimeMillis();

    //This is a list of players in the faction that
    // have claimed their reward.
    // Claim it only once!
    private List<String> playersClaimed = MUtil.list();


    // -------------------------------------------- //
    // FIELDS: Faction
    // -------------------------------------------- //

    public void setFaction(Faction faction)
    {
        this.faction = faction; this.changed();
    }

    public Faction getFaction()
    {
        if(this.faction != null) return this.faction;

        return null;
    }

    // -------------------------------------------- //
    // FIELDS: alignmentId
    // -------------------------------------------- //

    public void setAlignmentId(String id)
    {
        if(this.alignmentId.equals(id)) return;

        this.alignmentId = id;
        setLastAlignChange((double) System.currentTimeMillis());
        this.changed();
    }

    public String getAlignmentId()
    {
        return this.alignmentId;
    }


    // -------------------------------------------- //
    // FIELDS: alignCooldown
    // -------------------------------------------- //

    public void setLastAlignChange(double lastAlignChange)
    {
        this.lastAlignChange = lastAlignChange;
        this.changed();
    }

    public double getLastAlignChange()
    {
        return this.lastAlignChange;
    }

    public boolean allowAlignChange()
    {
        double timeDiff = System.currentTimeMillis() - this.lastAlignChange;

        // if they changed alignment within a month do not allow.
        return !(timeDiff < MConf.alignChangeCooldown);
    }

    // -------------------------------------------- //
    // FIELDS: playersClaimed
    // -------------------------------------------- //

    public void setPlayersClaimed(List<String> newList)
    {
        this.playersClaimed = newList;
        this.changed();
    }

    public List<String> getPlayersClaimed() { return this.playersClaimed; }

    public void emptyPlayersClaimed()
    {
        this.playersClaimed.clear();
        this.changed();
    }

    public void addToPlayersClaimed(String playerId)
    {
        if(this.playersClaimed.contains(playerId)) return;

        this.playersClaimed.add(playerId);
        this.changed();
    }

    public void removeFromPlayersClaimed(String playerId)
    {
        if(!this.playersClaimed.contains(playerId)) return;

        this.playersClaimed.remove(playerId);
        this.changed();
    }

    public boolean isInPlayersClaimed(String playerId)
    {
        return this.playersClaimed.contains(playerId);
    }
}
