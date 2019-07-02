package com.massivecraft.blessedwar.cmd.type;

import com.massivecraft.blessedwar.Modify;

public class TypeModifier extends TypeModify
{


    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //
    private static TypeModifier i = new TypeModifier();
    public static TypeModifier get() { return i; }

    public TypeModifier()
    {
        this.setAll(Modify.ADD, Modify.SUBTRACT, Modify.SET);
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //
}
