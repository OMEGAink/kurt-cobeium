package net.albertomega.kurtcobeium;

import net.albertomega.kurtcobeium.block.ModBlocks;
import net.albertomega.kurtcobeium.component.ModDataComponentTypes;
import net.albertomega.kurtcobeium.item.ModItems;
import net.albertomega.kurtcobeium.worlds.GlobalWorldLogic;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KurtCobeium implements ModInitializer {

	//0=============================================================================0
	public static final String MOD_ID = "kurt_cobeium";
	public static final String JOIN_TAG = "join_tag";
	public static final int GRAVITI_DRAG_MULTIPLIER = 10;
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	//0=============================================================================0

	@Override
	public void onInitialize() {

		LOGGER.info("Come As You Are.");
			ModItems.registerModItems();
			ModDataComponentTypes.registerDataComponentTypes();
			ModBlocks.registerModBlocks();
		LOGGER.info("No, I don't have a gun");

		//Global All World Logic
		GlobalWorldLogic.begin();



	}
}