package net.albertomega.kurtcobeium.item.itemcustom;

import net.albertomega.kurtcobeium.KurtCobeium;
import net.albertomega.kurtcobeium.component.ModDataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class PushWand extends Item{
    public PushWand(Item.Settings settings) {super(settings);}

    @Override
    public ActionResult use(World world, PlayerEntity playerEntity, Hand hand) {

        ItemStack stack = playerEntity.getStackInHand(hand);

        if (!world.isClient()){

            int pushLevel = stack.getOrDefault(ModDataComponentTypes.PUSH_AMOUNT,0);
            int maxLevel = 10;

            if (playerEntity.isSneaking()){


                float peach = (float) pushLevel+1.0F /(float) maxLevel;
                if (peach > 1.5F){peach = 1.5F;}

                //If not max level
                if (pushLevel < maxLevel) {
                    //Add push level
                    stack.set(ModDataComponentTypes.PUSH_AMOUNT, pushLevel + 1);
                    playerEntity.sendMessage(Text.literal("Push level: " + (pushLevel + 1)), true);
                    world.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            SoundEvents.BLOCK_NOTE_BLOCK_BASS,
                            SoundCategory.PLAYERS,
                            1.0F,
                            peach);
                }
                //MAx level
                else{
                    playerEntity.sendMessage(Text.literal("Push level: Max"), true);
                    world.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            SoundEvents.BLOCK_NOTE_BLOCK_BANJO,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.5F);
                }
            }
            else{
                if (pushLevel > 0){
                     //Push
                     Vec3d vel = playerEntity.getRotationVec(1.0F).multiply(pushLevel);
                     playerEntity.setVelocity(vel.x,vel.y,vel.z);
                     if (playerEntity instanceof ServerPlayerEntity serverPlayer) {
                        // Manually send the velocity packet to the client
                        serverPlayer.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(playerEntity));

                     }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

                    KurtCobeium.LOGGER.info("Velocity: {}, {}, {}", vel.x, vel.y, vel.z);

                     playerEntity.velocityDirty = true;

                     world.playSound(
                             null,
                             playerEntity.getX(),
                             playerEntity.getY(),
                             playerEntity.getZ(),
                             SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST,
                             SoundCategory.PLAYERS,
                             1.0F,
                             1.0F);

                     stack.set(ModDataComponentTypes.PUSH_AMOUNT,0);
                }
                else {

                    return ActionResult.PASS;

                }



            }
        }



        return ActionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerWorld serverWorld, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        super.inventoryTick(itemStack, serverWorld, entity, equipmentSlot);
    }
}
