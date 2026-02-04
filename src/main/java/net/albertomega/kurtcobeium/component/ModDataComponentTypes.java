package net.albertomega.kurtcobeium.component;

import com.mojang.serialization.Codec;
import net.albertomega.kurtcobeium.KurtCobeium;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {



    //Push amount
    //0==============================================================================0
    public static final ComponentType<Integer> PUSH_AMOUNT =
            register("push_forse", builder -> builder.codec(Codec.INT));
    //0==============================================================================0

    //Timer
    //0==============================================================================0
    public static final ComponentType<Integer> TIMER =
            register("timer", builder -> builder.codec(Codec.INT));
    //0==============================================================================0




    //Registering new data component
    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(KurtCobeium.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }


    //Init
    public static void registerDataComponentTypes() {
        KurtCobeium.LOGGER.info("Registering ModDataComponentTypes");
    }

}
