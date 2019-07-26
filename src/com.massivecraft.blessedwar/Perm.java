package com.massivecraft.blessedwar;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Perm implements Identified {

    // -------------------------------------------- //
    // ENUM
    // -------------------------------------------- //

    BASECOMMAND,

    CONFIG,

    VERSION,

    START,

    END,

    AWARD,

    FAC_JOIN,

    FAC_LEAVE,

    JOIN,

    LEAVE,

    CLAIM,

    REPORT,

    MODIFY,

    ;


    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    private final String id;
    @Override public String getId() { return this.id; }

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    Perm()
    {
        this.id = PermissionUtil.createPermissionId(BlessedWar.get(), this);
    }

    // -------------------------------------------- //
    // HAS
    // -------------------------------------------- //

    public boolean has(Permissible permissible, boolean verboose)
    {
        return PermissionUtil.hasPermission(permissible, this, verboose);
    }

    public boolean has(Permissible permissible)
    {
        return PermissionUtil.hasPermission(permissible, this);
    }
}
