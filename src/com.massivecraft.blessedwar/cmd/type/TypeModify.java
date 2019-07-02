package com.massivecraft.blessedwar.cmd.type;

import com.massivecraft.blessedwar.Modify;
import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;

public class TypeModify extends TypeEnum<Modify>
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static TypeModify i = new TypeModify();
    public static TypeModify get() { return i; }
    public TypeModify() { super(Modify.class); }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

}

