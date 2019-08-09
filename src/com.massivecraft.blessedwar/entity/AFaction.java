package com.massivecraft.blessedwar.entity;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.TimeUnit;

public class AFaction extends Entity<AFaction> {

    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    public static AFaction get(Faction faction) { return AFactionColl.get().get(faction.getId(), true); }

    //----------------------------------------------//
    // OVERRIDE
    //----------------------------------------------//

    @Override
    public AFaction load(AFaction that)
    {
        this.alignmentId = that.alignmentId;
        this.lastAlignChange = that.lastAlignChange;

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

    // This is the alignment the faction has chosen.
    // It is a foreign key.
    // Default: null

    private String alignmentId = null;

    // This is the cooldown timer.
    // Alignments are only allowed to switch
    // once a week.
    // Default: -1

    private long lastAlignChange = -1;

    // -------------------------------------------- //
    // UTIL METHODS
    // -------------------------------------------- //

    public Faction getFaction() { return Faction.get(id); }

    // -------------------------------------------- //
    // FIELDS: alignmentId
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


    // -------------------------------------------- //
    // FIELDS: alignCooldown
    // -------------------------------------------- //

    public void setLastAlignChange(long lastAlignChange)
    {
        if(this.lastAlignChange == lastAlignChange) return;

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
