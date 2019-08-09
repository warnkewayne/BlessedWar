package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.AlignmentColl;
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
        // Send Report
        if(MConf.get().blessedWarActive) { msender.msg("<i>[ Blessed War Progress Report ]"); }
        else { msender.msg("<i>[ Blessed War <pink>FINAL <i>Report ]"); }
        
        for (Alignment alignment : AlignmentColl.get().getAll()) {
            alignment.printStats(msender);
        }

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarReport; }
}
