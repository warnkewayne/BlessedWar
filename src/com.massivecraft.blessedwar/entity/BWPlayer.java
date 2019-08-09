package com.massivecraft.blessedwar.entity;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.store.SenderEntity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.TimeUnit;

public class BWPlayer extends SenderEntity<BWPlayer> {

    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    public static BWPlayer get(Object oid) { return BWPlayerColl.get().get(oid); }

    //----------------------------------------------//
    // OVERRIDE
    //----------------------------------------------//

    @Override
    public BWPlayer load(BWPlayer that)
    {
        this.lastActivityMillis = that.lastActivityMillis;
        this.alignmentId = that.alignmentId;
        this.unclaimedReward = that.unclaimedReward;
        this.lastAlignChange = that.lastAlignChange;

        return this;
    }

    @Override
    public void preClean()
    {
        if(alignmentId != null)
        {
            Alignment.get(alignmentId).removePlayer(id);
            this.leaveAlignment();
        }
    }

    // -------------------------------------------- //
    // FIELDS: RAW
    // -------------------------------------------- //

    // The last known time of player activity (login OR logout)
    // This is for removing cleanable players.
    // Default is set to the current time.
    private long lastActivityMillis = System.currentTimeMillis();

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

    private void clearData()
    {
        this.alignmentId = null;
        this.unclaimedReward = false;
        this.changed();
    }

    public BWFaction getBWFaction()
    {
        Faction faction = com.massivecraft.factions.entity.MPlayer.get(id).getFaction();

        if(faction == null) return null;
        return BWFaction.get(faction);
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
    // FIELD: alignment
    // -------------------------------------------- //

    public void setAlignmentId(String alignmentId)
    {

        if(MUtil.equals(alignmentId, this.alignmentId)) return;

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

        long timeDiff = (System.currentTimeMillis() - this.lastAlignChange) / TimeUnit.MILLIS_PER_DAY;

        // if they changed alignment within a month do not allow.
        return timeDiff >= MConf.get().alignChangeCooldown;
    }
}
