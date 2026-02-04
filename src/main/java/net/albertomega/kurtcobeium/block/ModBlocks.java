package net.albertomega.kurtcobeium.block;

import net.albertomega.kurtcobeium.KurtCobeium;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {


    //Blocks
        public static final Block BLOCK_TEST = registerBlock("test_block",
                setting -> new Block(setting.
                            strength(12.0F).requiresTool()
                        ),
                true);





    //Helper
    //0===============================================================================================================================0
        private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory, boolean isHaveItem) {

            RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(KurtCobeium.MOD_ID, name));

            AbstractBlock.Settings settings = AbstractBlock.Settings.create().registryKey(key);
            Block block = factory.apply(settings);

            Block regblock = Registry.register(Registries.BLOCK, key , block);

            if (isHaveItem){
                registerBlockItem(name, block);
            }

            return regblock;




        }


        private static void registerBlockItem(String name, Block block) {

            // Create the RegistryKey for the Item
            RegistryKey<Item> itemKey = RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(KurtCobeium.MOD_ID, name));

            // Create settings with the item key
            Item.Settings settings = new Item.Settings().registryKey(itemKey);
            BlockItem blockItem = new BlockItem(block, settings);

            Registry.register(Registries.ITEM, itemKey, blockItem);
        }
    //0===============================================================================================================================0











    public static void registerModBlocks() {
        KurtCobeium.LOGGER.info("Registering ModBlocks for " + KurtCobeium.MOD_ID);
    }

}
