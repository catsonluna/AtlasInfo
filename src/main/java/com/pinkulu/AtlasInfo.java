package com.pinkulu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


@Mod(modid = AtlasInfo.MODID, version = AtlasInfo.VERSION, name = AtlasInfo.NAME)
public class AtlasInfo {

    static final String MODID = "AtlasInfo";
    public static final String VERSION = "1.0";
    public static final String NAME = "Atlas Info";
    private final OnChat onChat = new OnChat();

    @Mod.EventHandler
    public void onInitialization(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(onChat);
 
        ClientCommandHandler.instance.registerCommand(new AtlasInfoCommand());
        loadConfig();
    }

    public static void saveConfig() {
        try {
            File file = new File("AtlasInfo", "save.json");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            JsonWriter writer = new JsonWriter(new FileWriter(file, false));
            writeJson(writer);
            writer.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            File file = new File("AtlasInfo", "save.json");
            if (file.exists())
                readJson(file);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void readJson(File file) throws Throwable {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(new FileReader(file)).getAsJsonObject();
        json = json.getAsJsonObject("Atlas Info");

        OnChat.reportsSuccessful = json.get("reportsSuccessful").getAsInt();
        OnChat.reportsDone = json.get("reportsDone").getAsInt();
    }

    public static void writeJson(JsonWriter writer) throws IOException {
        writer.setIndent(" "); // this enabled pretty print
        writer.beginObject();
        writer.name("Atlas Info");
        writer.beginObject();

        writer.name("reportsSuccessful").value(OnChat.reportsSuccessful);
        writer.name("reportsDone").value(OnChat.reportsDone);

        writer.endObject();
        writer.endObject();
    }
}