package com.massivecraft.blessedwar.cmd.type;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.AlignmentColl;
import com.massivecraft.massivecore.command.type.store.TypeEntity;

public class TypeAlignment extends TypeEntity<Alignment>
{

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //
    private static TypeAlignment i = new TypeAlignment();
    public static TypeAlignment get() { return i; }

    public TypeAlignment() { super(AlignmentColl.get()); }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public String getName() { return "alignment"; }

}
