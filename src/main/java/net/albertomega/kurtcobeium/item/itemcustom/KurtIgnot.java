package net.albertomega.kurtcobeium.item.itemcustom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;

public class KurtIgnot extends Item {
    public KurtIgnot(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);

        if (!world.isClient()) {
            //Exposion
            world.createExplosion(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 3.0f, false, ExplosionSourceType.TNT);
            //playerEntity.sendMessage(Text.literal("Your Text Here"), true);

            if (!playerEntity.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
        }

        return ActionResult.SUCCESS;
    }
}
