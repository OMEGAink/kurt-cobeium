package net.albertomega.kurtcobeium.render.world;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4d;
import org.joml.Matrix4dStack;

public class WorldRender {

    public static void renderElCUbe() {

        WorldRenderEvents.END_MAIN.register(WorldRender::endMain);
    }


    private static void endMain(WorldRenderContext context) {

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        final BufferAllocator customAllocator = new BufferAllocator(1024);
        MatrixStack matrixStack = context.matrices();
        Vec3d camera = context.worldState().cameraRenderState.pos;

        matrixStack.push();
        matrixStack.translate(-camera.x, -camera.y, -camera.z);

        int y = -40;

        buffer.vertex(matrixStack.peek(), 2, y, 2).color(255, 255, 255, 255);
        buffer.vertex(matrixStack.peek(), 0, y, 2).color(255, 255, 255, 255);
        buffer.vertex(matrixStack.peek(), 2, y, 0).color(255, 255, 255, 255);
        buffer.vertex(matrixStack.peek(), 0, y, 0).color(255, 255, 255, 255);

        BuiltBuffer builtBuffer = buffer.end();
        matrixStack.pop();

    }
}
