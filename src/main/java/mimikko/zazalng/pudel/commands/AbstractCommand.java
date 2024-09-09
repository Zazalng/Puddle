package mimikko.zazalng.pudel.commands;

import mimikko.zazalng.pudel.entities.SessionEntity;

public abstract class AbstractCommand implements Command {
    public void execute(SessionEntity session, String args) {}
}
