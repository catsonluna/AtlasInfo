package com.pinkulu;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.pinkulu.AtlasInfo.saveConfig;

public class OnChat {
    public static int reportsDone;
    public static int reportsSuccessful;
    @SubscribeEvent
    public void chatEvent(ClientChatReceivedEvent e) {
        String normalMsg = e.message.getUnformattedText();
            if(normalMsg.startsWith("+2,000 Hypixel Experience (Positive Atlas Verdict)")){
                System.out.println("Thing happened");
                reportsSuccessful++;
                saveConfig();
            }
            if(normalMsg.startsWith("Atlas verdict submitted! Thank you :)")){
                System.out.println("Thing happened");
                reportsDone++;
                saveConfig();

        }
    }
}
