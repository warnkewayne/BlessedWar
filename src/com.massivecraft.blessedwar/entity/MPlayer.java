package com.massivecraft.blessedwar.entity;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.store.SenderEntity;

import java.util.concurrent.TimeUnit;

public class MPlayer extends SenderEntity<MPlayer> {

    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    public static MPlayer get(Object oid) { return MPlayerColl.get().get(oid); }

    //----------------------------------------------//
    // OVERRIDE
    //----------------------------------------------//

    @Override
    public MPlayer load(MPlayer that)
    {
        this.lastActivityMillis = that.lastActivityMillis;
        this.alignmentId = that.alignmentId;
        this.factionId = that.factionId;
        this.unclaimedReward = that.unclaimedReward;
        this.lastAlignChange = that.lastAlignChange;

        return this;
    }

    // -------------------------------------------- //
    // FIELDS: RAW
    // -------------------------------------------- //

    // The last known time of player activity (login OR logout)
    // This is for removing cleanable players.
    // Default is set to the current time.
    private long lastActivityMillis = System.currentTimeMillis();

    // The player's faction id.
    // If the player is apart of a faction, the id will be stored here.
    // Foreign Key
    // Default: null
    private String factionId = null;

    // The alignment that the player is
    // associated with.
    // Default: null
    private String alignmentId = null;

    // If player has a reward to claim.
    // This will tell us.
    // Default: false
    private boolean unclaimedReward = false;

    // This is the cooldown timer.
    // Alignments are only allowed to switch
    // once a week.
    // Default: currentTime

    private long lastAlignChange = -1;

    // -------------------------------------------- //
    // CORE UTILITIES
    // -------------------------------------------- //

    public void clearData()
    {
        this.alignmentId = null;
        this.unclaimedReward = false;
        this.changed();
    }

    public void changedFaction(String fId)
    {
        this.factionId = fId;
        this.alignmentId = aFaction.get(Faction.get(fId)).getAlignmentId();
        this.changed();
    }

    // -------------------------------------------- //
    // FIELD: lastActivityMillis
    // -------------------------------------------- //

    public long getLastActivityMillis() { return this.lastActivityMillis; }

    public void setLastActivityMillis( long lastActivityMillis )
    {
        this.lastActivityMillis = convertSet(lastActivityMillis, this.lastActivityMillis, null);
    }

    public void setLastActivityMillis() { this.setLastActivityMillis(System.currentTimeMillis()); }

    @Override
    public boolean shouldBeCleaned(long now) { return this.shouldBeCleaned(now, this.lastActivityMillis);}

    // -------------------------------------------- //
    // FIELD: factionId
    // -------------------------------------------- //

    public void setFactionId(String id)
    {
        this.factionId = id;
        this.changed();
    }

    public String getFactionId()
    {
        return factionId;
    }

    public aFaction getaFaction()
    {
        if(factionId == null) { return null; }
        return aFaction.get(Faction.get(factionId));
    }


    // -------------------------------------------- //
    // FIELD: alignment
    // -------------------------------------------- //

    public void setAlignmentId(String alignmentId)
    {

        if(alignmentId == this.alignmentId) return;

        this.alignmentId = alignmentId;
        setLastAlignChange(System.currentTimeMillis());
        this.changed();
    }

    public String getAlignmentId()
    {
        return this.alignmentId;
    }

    public void leaveAlignment()
    {
        // Clear data
        clearData();
    }

    // -------------------------------------------- //
    // FIELD: unclaimedReward
    // -------------------------------------------- //

    public void setUnclaimedReward(boolean unclaimedReward)
    {
        this.unclaimedReward = unclaimedReward;
        this.changed();
    }

    public boolean getUnclaimedReward()
    {
        return this.unclaimedReward;
    }

    // -------------------------------------------- //
    // FIELDS: alignCooldown
    // -------------------------------------------- //

    public void setLastAlignChange(long lastAlignChange)
    {
        this.lastAlignChange = lastAlignChange;
        this.changed();
    }

    public long getLastAlignChange()
    {
        return this.lastAlignChange;
    }

    public boolean allowAlignChange()
    {
        if(this.lastAlignChange == -1) return true;

        long timeDiff = TimeUnit.MILLISECONDS.toDays(this.lastAlignChange - System.currentTimeMillis());

        System.out.println(this.lastAlignChange);
        System.out.println(timeDiff);

        // if they changed alignment within a week do not allow.
        return (timeDiff >= MConf.get().alignChangeCooldown);
    }
}
