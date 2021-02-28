package com.mcecraft.worldgen.test;

import com.mcecraft.worldgen.Generator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.utils.Position;

import java.util.concurrent.CountDownLatch;

public class Minestom {

	public static void main(String[] args) {
		// Initialization
		MinecraftServer minecraftServer = MinecraftServer.init();

		InstanceManager instanceManager = MinecraftServer.getInstanceManager();
		// Create the instance
		InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

		// Worldgen-pack setup
		Generator wg = new Generator(instanceContainer);

		// Set the ChunkGenerator
		instanceContainer.setChunkGenerator(wg.getChunkGenerator());
		// Enable the auto chunk loading (when players come close)
		instanceContainer.enableAutoChunkLoad(true);

		// Add an event callback to specify the spawning instance (and the spawn position)
		GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
		globalEventHandler.addEventCallback(PlayerLoginEvent.class, event -> {
			final Player player = event.getPlayer();
			System.out.println(player.getUsername() + " joined the game");
			event.setSpawningInstance(instanceContainer);
			player.setRespawnPoint(new Position(0, 42, 0));
		});

		globalEventHandler.addEventCallback(PlayerDisconnectEvent.class, event -> {
			System.out.println(event.getPlayer().getUsername() + " left the game");
		});
		globalEventHandler.addEventCallback(PlayerSpawnEvent.class, event -> {
			event.getPlayer().setGameMode(GameMode.SPECTATOR);
		});

		MinecraftServer.setChunkViewDistance(20);

		// Start the server on port 25565
		minecraftServer.start("localhost", 25566);

		System.out.println(System.currentTimeMillis());
		int width = 20;
		CountDownLatch latch = new CountDownLatch(width*width*4);
		for (int x = -width; x < width; x++) {
			for (int y = -width; y < width; y++) {
				instanceContainer.loadChunk(x,y, (c) -> latch.countDown());
			}
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
	}
}
