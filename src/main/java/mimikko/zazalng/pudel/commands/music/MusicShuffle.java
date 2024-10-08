package mimikko.zazalng.pudel.commands.music;

import mimikko.zazalng.pudel.commands.AbstractCommand;
import mimikko.zazalng.pudel.entities.SessionEntity;

import java.util.HashMap;
import java.util.Map;

import static mimikko.zazalng.pudel.utility.BooleanUtility.*;

public class MusicShuffle extends AbstractCommand {
    @Override
    public void execute(SessionEntity session, String args) {
        super.execute(session, args);
    }

    @Override
    protected void initialState(SessionEntity session, String args) {
        Map<String, String> localizationArgs = new HashMap<>();
        localizationArgs.put("username", session.getUser().getNickname(session.getGuild()));
        localizationArgs.put("args", args);

        if(triggerTrue(args)){
            session.getGuild().getMusicPlayer().setShuffle(true);
            localizationArgs.put("player.shuffle", session.getPudelWorld().getLocalizationManager().getBooleanText(session.getGuild(),session.getGuild().getMusicPlayer().isShuffle()));
            args = localize(session,"music.shuffle.init.set",localizationArgs);
        } else if(triggerFalse(args)){
            session.getGuild().getMusicPlayer().setShuffle(false);
            localizationArgs.put("player.shuffle", session.getPudelWorld().getLocalizationManager().getBooleanText(session.getGuild(),session.getGuild().getMusicPlayer().isShuffle()));
            args = localize(session,"music.shuffle.init.set",localizationArgs);
        } else if(args.isEmpty()){
            localizationArgs.put("player.shuffle", session.getPudelWorld().getLocalizationManager().getBooleanText(session.getGuild(),session.getGuild().getMusicPlayer().isShuffle()));
            args = localize(session,"music.shuffle.init.display",localizationArgs);
        } else{
            session.getGuild().getMusicPlayer().shufflePlaylist();
            args = localize(session,"music.shuffle.init.seed",localizationArgs);
        }
        session.getChannel().sendMessage(args).queue();

        session.setState("END");
    }

    @Override
    public void reload() {

    }

    @Override
    public String getDescription(SessionEntity session) {
        return localize(session, "music.shuffle.help");
    }

    @Override
    public String getDetailedHelp(SessionEntity session) {
        return localize(session, "music.shuffle.details");
    }
}
