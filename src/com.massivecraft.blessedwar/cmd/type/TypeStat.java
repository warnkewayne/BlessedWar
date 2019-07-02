package com.massivecraft.blessedwar.cmd.type;
import com.massivecraft.blessedwar.Stat;
import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;

public class TypeStat extends TypeEnum<Stat>
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static TypeStat i = new TypeStat();
    public static TypeStat get() { return i; }
    public TypeStat() { super(Stat.class); }
}
