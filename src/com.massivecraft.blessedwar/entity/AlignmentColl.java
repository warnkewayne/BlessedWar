package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.store.Coll;

public class AlignmentColl extends Coll<Alignment> {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static AlignmentColl i = new AlignmentColl();
    public static AlignmentColl get() { return i; }
    public AlignmentColl()
    {
        this.setCleanTaskEnabled(true);
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

        this.createAlignments();
    }

    // -------------------------------------------- //
    // ALIGNMENT CREATION
    // -------------------------------------------- //

    public void createAlignments()
    {
        getUnionism();
        getFaithOfEstel();
        getDragonWorship();
        getVoidWorship();
    }

    public Alignment getUnionism()
    {
        String id = Alignment.ID_UNIONISM;
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setAlignmentName("Unionism");
        alignment.setAlignmentSymbol("⚜");

        return alignment;
    }

    public Alignment getFaithOfEstel()
    {
        String id = Alignment.ID_ESTEL;
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setAlignmentName("Faith of Estel");
        alignment.setAlignmentSymbol("⚘");

        return alignment;
    }

    public Alignment getDragonWorship()
    {
        String id = Alignment.ID_DRAGON;
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setAlignmentName("Dragon Worship");
        alignment.setAlignmentSymbol("༗");

        return alignment;
    }

    public Alignment getVoidWorship()
    {
        String id = Alignment.ID_VOID;
        Alignment alignment = this.get(id);

        if(alignment != null) return alignment;

        alignment = this.create(id);

        alignment.setAlignmentName("Void Worship");
        alignment.setAlignmentSymbol("∵");

        return alignment;
    }

}
