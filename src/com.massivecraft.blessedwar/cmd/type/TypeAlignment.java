package com.massivecraft.blessedwar.cmd.type;

import com.massivecraft.blessedwar.Align;

public class TypeAlignment extends TypeAlign
{

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //
    private static TypeAlignment i = new TypeAlignment();
    public static TypeAlignment get() { return i; }

    public TypeAlignment()
    {
        this.setAll(Align.UNIONISM, Align.DRAGON, Align.VOID, Align.ESTEL);
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public String getName() { return "alignment"; }
}
