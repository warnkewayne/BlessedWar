package com.massivecraft.blessedwar.cmd.type;

import com.massivecraft.blessedwar.Stat;

public class TypeStatstic extends TypeStat
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //
    private static TypeStatstic i = new TypeStatstic();
    public static TypeStatstic get() { return i; }

    public TypeStatstic()
    {
        this.setAll(Stat.PLAYERKILLS, Stat.MOBKILLS, Stat.POPULATION, Stat.TOKENS, Stat.REGALS);
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //
}
