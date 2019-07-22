package com.massivecraft.blessedwar;

import com.massivecraft.massivecore.Named;
import com.massivecraft.massivecore.collections.MassiveSet;

import java.util.Collections;
import java.util.Set;

public enum Align implements Named
{
    // -------------------------------------------- //
    // ENUM
    // -------------------------------------------- //

    UNIONISM("Unionism"),
    DRAGON("Dragon"),
    ESTEL("Estel"),
    VOID("Void")

    // END OF LIST
    ;

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    private final Set<String> names;
    public Set<String> getNames() { return this.names; }
    @Override public String getName() { return this.getNames().iterator().next(); }

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //
    Align(String... names)
    {
        this.names = Collections.unmodifiableSet(new MassiveSet<>(names));
    }

}
