package net.albertomega.kurtcobeium;

import net.albertomega.kurtcobeium.datageneration.ModItemTagGenerator;
import net.albertomega.kurtcobeium.datageneration.ModModelGenerator;
import net.albertomega.kurtcobeium.datageneration.ModItemTagGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.data.ModelProvider;

public class KurtCobeiumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelGenerator::new);
		pack.addProvider(ModItemTagGenerator::new);

	}
}
