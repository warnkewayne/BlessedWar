package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.collections.MassiveListDef;
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
        new Alignment("unionism", "Unionism", "⚜", "essalonia.blessedwar.unionism", new MassiveListDef<>("/crate key {p} unionismkey 1"));
        new Alignment("faithofestel", "Faith of Estel", "⚘", "essalonia.blessedwar.estel", new MassiveListDef<>("/crate key {p} estelkey 1"));
        new Alignment("dragonworship", "Dragon Worship", "༗", "essalonia.blessedwar.dragon", new MassiveListDef<>("/crate key {p} dragonkey 1"));
        new Alignment("voidworship", "Void Worship", "∵", "essalonia.blessedwar.void", new MassiveListDef<>("/crate key {p} voidkey 1"));
    }
}
