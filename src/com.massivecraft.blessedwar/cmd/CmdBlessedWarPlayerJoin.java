package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Align;
import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.Button;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import org.bukkit.Bukkit;

import java.util.List;

public class CmdBlessedWarPlayerJoin extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarPlayerJoin()
    {
        this.addParameter(TypeAlignment.get(),"alignment");

        this.addRequirements(RequirementHasPerm.get(Perm.JOIN_LEAVE));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        if(!msender.allowAlignChange()) { msender.msg("<b>You cannot change your alignment so soon!"); return; }

        //Args
        Align align = readArg();
        Alignment alignment;
        String startNode;

        switch(align)
        {
            case UNIONISM:
                alignment = Alignment.get(Alignment.ID_UNIONISM);
                break;

            case DRAGON:
                alignment = Alignment.get(Alignment.ID_DRAGON);
                break;

            case VOID:
                alignment = Alignment.get(Alignment.ID_VOID);
                break;

            case ESTEL:
                alignment = Alignment.get(Alignment.ID_ESTEL);
                break;

            default:
                msender.msg("<b> Not valid Alignment name."); return;
        }

        Alignment msenderAlignment = msender.getAlignment();

        // Check if player's alignment is the same
        if(msenderAlignment == alignment)
        {
            msender.msg("<i>You are already aligned with %s", alignment.getName());
            return;
        }

        // Check if player has an alignment
        if(msenderAlignment != null)
        {

            Button btnLeave = new Button().setName("Leave").setSender(sender).setCommand(CmdBlessedWar.get().cmdBlessedWarPlayerLeave);
            msender.message(Mson.parse("<b>You are already aligned with %s", msenderAlignment.getName()).add(btnLeave.render()));

            return;
        }

        // Check if player's faction is aligned
        if(msenderFaction != null && msenderFaction.getAlignment() != null)
        {
            msender.msg("<b>Your faction is already aligned with %s", msenderFaction.getAlignment().getName());
            return;
        }

        // Add player to list
        msender.setAlignment(alignment);
        alignment.addPlayer(msender.getId());

        // Sender message
        msender.msg("<i>You have successfully aligned with %s", alignment.getName());

        // Player gets Religion's starting Quest :D
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "q p f " + msender.getName() + alignment.getStartingNode());
    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarJoin; }
}

