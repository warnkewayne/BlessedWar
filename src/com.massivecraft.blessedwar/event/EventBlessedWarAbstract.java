package com.massivecraft.blessedwar.event;

import com.massivecraft.massivecore.event.EventMassiveCore;

public abstract class EventBlessedWarAbstract extends EventMassiveCore
{
    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public EventBlessedWarAbstract() {}

    public EventBlessedWarAbstract(boolean isAsync) {super(isAsync); }
}
