package com.example.invisibleblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class InvisibleBlocksMod implements ClientModInitializer {
    public static volatile boolean enabled = true;

    /** True if this block should stay visible. */
    public static boolean isVisible(BlockState state) {
        Block b = state.getBlock();
        return b instanceof ChestBlock          // chests + trapped chests
            || b instanceof EnderChestBlock
            || b instanceof BarrelBlock
            || b instanceof ShulkerBoxBlock     // all colors
            || b == Blocks.SPAWNER;
    }

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(ClientCommandManager.literal("invisibleblocks").executes(ctx -> {
                enabled = !enabled;
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.worldRenderer != null) mc.worldRenderer.reload(); // rebuild chunks
                ctx.getSource().sendFeedback(Text.literal("Invisible blocks: " + (enabled ? "ON" : "OFF")));
                return 1;
            })));
    }
}
