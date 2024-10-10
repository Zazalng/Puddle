package mimikko.zazalng.pudel.entities;

import mimikko.zazalng.pudel.PudelWorld;
import mimikko.zazalng.pudel.commands.Command;
import mimikko.zazalng.pudel.manager.SessionManager;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.util.HashMap;
import java.util.Map;

public class SessionEntity {
    protected final SessionManager sessionManager;
    private final UserEntity user;
    private final GuildEntity guild;
    private final MessageChannelUnion channel;
    private Command command;
    private final Map<String, Object> promptCollection = new HashMap<>();
    private String sessionState;

    public SessionEntity(SessionManager sessionManager, UserEntity user, GuildEntity guild, MessageChannelUnion channelIssue) {
        this.sessionManager = sessionManager;
        this.user = user;
        this.guild = guild;
        this.channel = channelIssue;
        this.sessionState = "INIT";
    }

    public SessionEntity setState(String sessionState) {
        this.sessionState = sessionState;
        return this;
    }

    public String getState() {
        return this.sessionState;
    }

    public void addData(String key, Object value) {
        promptCollection.put(key, value);
    }

    public Object getData(String key, boolean delObject) {
        return delObject ? promptCollection.remove(key) : promptCollection.get(key);
    }

    public void setCommand(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
    // Getters for entities
    public PudelWorld getPudelWorld(){
        return sessionManager.getPudelWorld();
    }

    public UserEntity getUser() {
        return user;
    }

    public GuildEntity getGuild() {
        return guild;
    }

    public MessageChannelUnion getChannel() {
        return channel;
    }

    public SessionEntity execute(String args){
        command.execute(this,args);
        return this;
    }
}