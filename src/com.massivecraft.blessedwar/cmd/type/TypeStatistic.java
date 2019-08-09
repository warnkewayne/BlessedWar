package com.massivecraft.blessedwar.cmd.type;

import com.massivecraft.blessedwar.Stat;

public class TypeStatistic extends TypeStat
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //
    private static TypeStatistic i = new TypeStatistic();
    public static TypeStatistic get() { return i; }

    public TypeStatistic()
    {
        this.setAll(Stat.PLAYERKILLS, Stat.MOBKILLS, Stat.POPULATION, Stat.TOKENS, Stat.REGALS);
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //
}
