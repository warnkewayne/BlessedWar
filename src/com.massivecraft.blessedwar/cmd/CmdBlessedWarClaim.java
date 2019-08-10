package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mixin.MixinCommand;
import com.massivecraft.massivecore.util.IdUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class CmdBlessedWarClaim extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarClaim()
    {
        this.addRequirements(RequirementHasPerm.get(Perm.CLAIM));
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform()
    {
        if (msender == null) return;

        // Check if Player has reward available to claim
        if(!msender.getUnclaimedReward()) { msender.msg("<b>You do not have a reward to claim."); return; }

        // Run the reward commands
        // Remove reward from player's data

        Alignment alignment = Alignment.get(msender.getAlignmentId());
    
        for (String commandLine : alignment.getCmdRewards())
        {
            // Parse
            commandLine = StringUtils.replace(commandLine, "{p}", msender.getName());
            commandLine = commandLine.replaceAll("\\{/(/*[p])}", "{$1}");
            MixinCommand.get().dispatchCommand(msender, IdUtil.CONSOLE_ID, commandLine);
        }
        
        msender.setUnclaimedReward(false);
    }

    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarClaim; }
}
