/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mimikko.zazalng.puddle;

/**
 *
 * @author User
 */

import mimikko.zazalng.puddle.listenerEvent.CommandParser;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Puddle{

    public static void main(String[] args) throws Exception {
        String botToken = "";

        JDA jda = JDABuilder.createLight(botToken, 
                GatewayIntent.DIRECT_MESSAGES, 
                GatewayIntent.GUILD_MESSAGES, 
                GatewayIntent.MESSAGE_CONTENT, 
                GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new CommandParser())
                .build();
    }
}
