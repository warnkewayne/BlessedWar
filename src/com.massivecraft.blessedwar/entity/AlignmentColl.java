package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.Coll;

public class AlignmentColl extends Coll<Alignment> {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static AlignmentColl i = new AlignmentColl();
    public static AlignmentColl get() { return i; }
    public AlignmentColl()
    {
        this.setCleanTaskEnabled(false);
    }

    // -------------------------------------------- //
    // STACK TRACEABILITY
    // -------------------------------------------- //

    @Override
    public void onTick()
    {
        super.onTick();
    }

    // -------------------------------------------- //
    // OVERRIDE: COLL
    // -------------------------------------------- //

    @Override
    public void setActive(boolean active)
    {
        super.setActive(active);

        if (!active) return;
    
        if (this.getAll().isEmpty())
        {
            this.createAlignments();
            this.syncAll();
        }
    }

    // -------------------------------------------- //
    // ALIGNMENT CREATION
    // -------------------------------------------- //

    private void createAlignments()
    {
        getUnionism();
        getFaithOfEstel();
        getDragonWorship();
        getVoidWorship();
    }

    private Alignment getUnionism()
    {
        String id = "unionism";
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setName("Unionism");
        alignment.setSymbol("⚜");
        alignment.setStartingNode("essalonia.blessedwar.unionism");
        alignment.setCmdRewards(new MassiveList<>("/crate key {p} unionismkey 1"));

        return alignment;
    }

    private Alignment getFaithOfEstel()
    {
        String id = "faithofestel";
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setName("Faith of Estel");
        alignment.setSymbol("⚘");
        alignment.setStartingNode("essalonia.blessedwar.estel");
        alignment.setCmdRewards(new MassiveList<>("/crate key {p} estelkey 1"));

        return alignment;
    }

    private Alignment getDragonWorship()
    {
        String id = "dragonworship";
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setName("Dragon Worship");
        alignment.setSymbol("༗");
        alignment.setStartingNode("essalonia.blessedwar.dragon");
        alignment.setCmdRewards(new MassiveList<>("/crate key {p} dragonkey 1"));

        return alignment;
    }

    private Alignment getVoidWorship()
    {
        String id = "voidworship";
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setName("Void Worship");
        alignment.setSymbol("∵");
        alignment.setStartingNode("essalonia.blessedwar.void");
        alignment.setCmdRewards(new MassiveList<>("/crate key {p} voidkey 1"));

        return alignment;
    }

}
