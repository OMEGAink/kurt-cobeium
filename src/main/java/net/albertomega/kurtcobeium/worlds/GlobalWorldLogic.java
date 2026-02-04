package net.albertomega.kurtcobeium.worlds;

import net.albertomega.kurtcobeium.KurtCobeium;
import net.albertomega.kurtcobeium.worlds.all.SlowZone;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.albertomega.kurtcobeium.KurtCobeium.JOIN_TAG;

public class GlobalWorldLogic {

    public static void begin() {

        //Every single tick event
        //0===========================================================================================================================0
        ServerTickEvents.END_WORLD_TICK.register(world -> {

            //Server logic
            if (!world.isClient()){
                assert world.getServer() != null;

                //Slow_Zone
                SlowZone.begin(world);

            }

            //Client logic
            if (world.isClient()){

            }

        });
        //0===========================================================================================================================0






        //Player join world
        //0===========================================================================================================================0
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.player;

            //Give join tag if its first time he joined to the world
            //0==================================================================================0
            if (!player.getCommandTags().contains(JOIN_TAG)){
                player.addCommandTag(JOIN_TAG);

                player.sendMessage(Text.literal(" > Welcome to the world, what was sold by the man. < "));
            }
            else{

                player.sendMessage(Text.literal(" > Welcome back. " + player.getName().getString() + " < "));

            }
            //0==================================================================================0



        });




    }
}
