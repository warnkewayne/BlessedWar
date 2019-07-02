package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.BlessedWar;
import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.command.MassiveCommandVersion;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBlessedWarVersion extends MassiveCommandVersion {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarVersion()
    {
        super(BlessedWar.get());
        this.addRequirements(RequirementHasPerm.get(Perm.VERSION));
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public List<String> getAliases()
    {
        return MConf.get().aliasesBlessedWarVersion;
    }

}
