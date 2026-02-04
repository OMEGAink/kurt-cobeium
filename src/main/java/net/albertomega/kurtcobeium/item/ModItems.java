package net.albertomega.kurtcobeium.item;

import net.albertomega.kurtcobeium.KurtCobeium;
import net.albertomega.kurtcobeium.item.itemcustom.KurtIgnot;
import net.albertomega.kurtcobeium.item.itemcustom.PushWand;
import net.minecraft.registry.Registry; //Custom Data Loader
import net.minecraft.registry.Registries; //Custom data types

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {

    public static List<Item> items_list = new ArrayList<>();

    //Register new item
    private static Item registerItem_Default(String name, Function<Item.Settings, Item> factory) {

        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(KurtCobeium.MOD_ID, name));
        Item.Settings settings = new Item.Settings().registryKey(key);
        Item item = factory.apply(settings);

        Item registered_item = Registry.register(Registries.ITEM, key , item);

        items_list.add(registered_item); //Add item to the list
        return registered_item;

    }

    private static Item registerItem_Setting(String name, int stackSize ,Rarity rarity,boolean ifFireProof , Function<Item.Settings, Item> factory) {

        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(KurtCobeium.MOD_ID, name));

        //Settings
        Item.Settings settings = new Item.Settings().registryKey(key);
        if (ifFireProof) { settings.fireproof();}
        settings.rarity(rarity);
        settings.maxCount(stackSize);

        Item item = factory.apply(settings);

        Item registered_item = Registry.register(Registries.ITEM, key , item);

        items_list.add(registered_item); //Add item to the list
        return registered_item;

    }


    public static void registerItemsPack_Default(String name, String... items) {
        for (String item : items) {
            RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(KurtCobeium.MOD_ID, name + "." + item));
            items_list.add(Registry.register(Registries.ITEM, key, new Item(new Item.Settings())));
        }
    }





    //New Items
    //0==============================================0
        //Kurt cobain ignot
        public static final Item KURT_IGNOT = registerItem_Default("kurt_ignot", KurtIgnot::new);


        //Push Wand
        public static final Item PUSH_WAND = registerItem_Default("push_wand", PushWand::new);

    //0==============================================0








    //Init
    public static void registerModItems() {
        KurtCobeium.LOGGER.info("Registering ModItems for " + KurtCobeium.MOD_ID);
    }

}
