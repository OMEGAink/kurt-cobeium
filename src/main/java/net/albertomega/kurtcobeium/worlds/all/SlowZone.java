package net.albertomega.kurtcobeium.worlds.all;

import net.albertomega.kurtcobeium.KurtCobeium;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class SlowZone {

    public static void begin(ServerWorld world) {
        //For every player
        for (ServerPlayerEntity player : PlayerLookup.all(world.getServer())) {

            double distance = Math.sqrt(Math.pow(player.getX(), 2) + Math.pow(player.getZ(), 2));


            if (distance >= (int) (KurtCobeium.GRAVITI_DRAG_MULTIPLIER / 2)) {

                //Effects
                player.removeStatusEffect(StatusEffects.SLOWNESS);
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SLOWNESS,
                        -1, // duration
                        (int) (distance / KurtCobeium.GRAVITI_DRAG_MULTIPLIER),
                        false, // ambient
                        false, // show particles
                        false   // show icon
                ));
            } else {
                player.removeStatusEffect(StatusEffects.SLOWNESS);
            }


        }
    }


}
