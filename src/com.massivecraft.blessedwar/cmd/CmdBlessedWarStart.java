package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.AlignmentColl;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.event.EventBlessedWarStart;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.mson.Mson;

import java.util.List;

public class CmdBlessedWarStart extends BlessedWarCommand
{

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarStart() { this.addRequirements(RequirementHasPerm.get(Perm.START)); }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform()
    {

        if(MConf.get().blessedWarActive) { msender.msg("<i>The Blessed War has already started."); return; }

        // Commence the War...
        MConf.get().blessedWarActive = true;

        // Clear all alignments' stats
        for (Alignment alignment : AlignmentColl.get().getAll()) {
            alignment.resetAlignmentStats();
        }

        // BlessedWarStartEvent
        EventBlessedWarStart event = new EventBlessedWarStart(sender);
        event.run();
        if(event.isCancelled()) return;

        //Announce to the server the war has begun
        MixinMessage.get().messageAll(Mson.parse("<pink>[ THE BLESSED WAR HAS BEGUN ]"));
    }



    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarStart; }
}
