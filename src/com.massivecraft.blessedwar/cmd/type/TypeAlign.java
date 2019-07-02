package com.massivecraft.blessedwar.cmd.type;

import com.massivecraft.blessedwar.Align;
import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;

import java.util.Set;

public class TypeAlign extends TypeEnum<Align>
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static TypeAlign i = new TypeAlign();
    public static TypeAlign get() { return i; }
    public TypeAlign() { super(Align.class); }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public String getName() { return "alignment"; }

    @Override
    public String getNameInner(Align value) { return value.getName(); }

    @Override
    public Set<String> getNamesInner(Align value) { return value.getNames(); }
}
