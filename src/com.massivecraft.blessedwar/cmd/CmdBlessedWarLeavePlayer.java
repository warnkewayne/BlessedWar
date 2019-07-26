package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import org.bukkit.Bukkit;

import java.util.List;

public class CmdBlessedWarLeavePlayer extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarLeavePlayer()
    {

        this.addRequirements(RequirementHasPerm.get(Perm.LEAVE));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        //Check if player has an alignment
        if(msender.getAlignment() == null)
        {
            msender.msg("<b>You do not have any alignments");
            return;
        }

        Alignment align = msender.getAlignment();

        // Leave that alignment and clear Player's data
        align.removePlayer(msender.getId());
        msender.leaveAlignment();

        // Send message
        msender.msg("<b>You have left the Blessed War and %s.", align.getName());

        // Player stops the Religion's quest
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "q p f " + msender.getName() + " " + align.getStartingNode() + ".stop");

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarLeave; }
}
