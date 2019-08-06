package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBlessedWarReport extends BlessedWarCommand
{
    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarReport() { this.addRequirements(RequirementHasPerm.get(Perm.REPORT)); }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform()
    {
        // If BlessedWar is still going on...
        // Send Progress Report
        if(MConf.get().blessedWarActive)
        {
            msender.msg("<i>[ Blessed War Progress Report ]");
            Alignment.get(Alignment.ID_UNIONISM).printStats(msender);
            Alignment.get(Alignment.ID_DRAGON).printStats(msender);
            Alignment.get(Alignment.ID_ESTEL).printStats(msender);
            Alignment.get(Alignment.ID_VOID).printStats(msender);

            return;
        }

        // If BlessedWar has ended
        // Send final report
        msender.msg("<i>[ Blessed War <pink>FINAL <i>Report ]");
        Alignment.get(Alignment.ID_UNIONISM).printStats(msender);
        Alignment.get(Alignment.ID_DRAGON).printStats(msender);
        Alignment.get(Alignment.ID_ESTEL).printStats(msender);
        Alignment.get(Alignment.ID_VOID).printStats(msender);

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarReport; }
}
