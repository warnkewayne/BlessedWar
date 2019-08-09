package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.BWPlayer;
import com.massivecraft.massivecore.Button;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;

import java.util.List;

public class CmdBlessedWarAward extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarAward()
    {
        this.addParameter(TypeAlignment.get(), "alignment");

        this.addRequirements(RequirementHasPerm.get(Perm.AWARD));
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        // The Alignment won! Staff can award them now with keys!
        // A whole ton of keyssss!
        // Read in the alignment, find that alignment...
        // And get the players/factions that fight for that religion ...
        // give every player keys with a message.
        // Factions get monetary value if activated.
        //
        // If these players are offline, award them on next login.


        //Args
        Alignment alignment = this.readArg();

        if(alignment == null)
        {
            msender.msg("<b>Invalid Alignment.");
            return;
        }


        // Get all players from that alignment

        List<String> members = alignment.getPlayerList();

        for (String member : members)
        {
            BWPlayer bwPlayer = BWPlayer.get(member);

            bwPlayer.setUnclaimedReward(true);

            Button btnClaim = new Button()
                    .setName("Claim")
                    .setSender(bwPlayer.getSender())
                    .setCommand(CmdBlessedWar.get().cmdBlessedWarClaim);

            bwPlayer.message(
                    Mson.parse("<pink>[BLESSED WAR]: <i>Congrats! You have a reward to claim.")
                            .add(btnClaim.render()));
        }

    }

    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarAward; }
}
