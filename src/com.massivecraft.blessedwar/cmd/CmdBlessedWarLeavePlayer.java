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
        if(msender.getAlignmentId() == null)
        {
            msender.msg("<b>You do not have any alignments");
            return;
        }

        //Check if player's faction has alignment
        if(msenderFaction != null && msenderFaction.getAlignment() != null)
        {
            msender.msg("<b>You cannot abandon yourself from your faction's alignment.");
            return;
        }

        String align = msender.getAlignmentId();

        // Leave that alignment and clear Player's data
        Alignment.get(align).removePlayer(msender.getId());
        msender.leaveAlignment();

        // Send message
        msender.msg("<b>You have left the Blessed War and %s.", Alignment.get(align).getName());

        // Player stops the Religion's quest
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "q p f " + msender.getName() + " " + Alignment.get(align).getStartingNode() + ".stop");

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarLeave; }
}
