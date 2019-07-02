package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.event.EventBlessedWarEnd;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.mson.Mson;

import java.util.List;

public class CmdBlessedWarEnd extends BlessedWarCommand
{
    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarEnd() { this.addRequirements(RequirementHasPerm.get(Perm.START_END)); }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        if(!MConf.get().blessedWarActive) { msender.msg("<i>The Blessed War has already ended."); return; }

        // End the War...
        MConf.get().blessedWarActive = false;

        // BlessedWarStartEvent
        EventBlessedWarEnd event = new EventBlessedWarEnd(sender);
        event.run();
        if(event.isCancelled()) return;

        // Announce to the server the blessed war has ended
        MixinMessage.get().messageAll(Mson.parse("<pink>[ THE BLESSED WAR HAS ENDED ]"));
    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarEnd; }

}
