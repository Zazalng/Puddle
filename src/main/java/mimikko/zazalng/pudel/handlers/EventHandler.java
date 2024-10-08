package mimikko.zazalng.pudel.handlers;

import mimikko.zazalng.pudel.PudelWorld;
import mimikko.zazalng.pudel.entities.SessionEntity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventHandler extends ListenerAdapter{
    protected final PudelWorld pudelWorld;

    public EventHandler(PudelWorld pudelWorld) {
        this.pudelWorld = pudelWorld;
    }

    @Override
    public void onReady(@NotNull ReadyEvent e) {
        this.pudelWorld.getPudelManager().setPudelEntity(pudelWorld.getUserManager().getUserEntity(e.getJDA().getSelfUser()));
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        e.getChannel().getType();

        if(!e.getAuthor().isBot()){
            //From {guildName} in {channelName} by {userName} said: {contentRaw}
            String fullRespond = "From "+e.getGuild().getName()+" in "+e.getGuildChannel().getName()+" by "+e.getAuthor().getName()+" said: \n"+e.getMessage().getContentRaw();
            System.out.println(fullRespond);

            /*
                Still Possible to implement command for direct message interacting
                - A possible thing that can happen (from what I have discovery)
                1. Message from Guild asTextChannel
             */

            //e.getChannel().sendTyping().queue();

            SessionEntity session = this.pudelWorld.getSessionManager().getSession(e);
            this.pudelWorld.getCommandManager().handleCommand(session, e);
        }
    }
}