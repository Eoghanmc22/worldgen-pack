package com.mcecraft.worldgen.layers.impl.mountains;

import com.mcecraft.worldgen.biomes.impl.mountains.*;
import net.minestom.worldgen.ChunkRandom;
import net.minestom.worldgen.layers.Layer;
import net.minestom.worldgen.layers.MultiSamplingLayer;

public class MountainsVeryTallLayer extends MultiSamplingLayer {

    private final int chance;

    public MountainsVeryTallLayer(long baseSeed, int chance) {
        super(baseSeed);
        this.chance = chance;
    }

    @Override
    protected int genBiomes(int x, int z, int u, int d, int r, int l, int c, ChunkRandom rng) {
        rng.initChunkSeed(x, z);
        int climateM = TallMountains.getInstance().getClimateId();
        int biomeM = TallMountains.getInstance().getBiomeId();
        if (!Layer.cmpBiomeClimate(c, climateM, biomeM)) {
            return c;
        }
        if (rng.nextInt(chance) == 0) {
            return Layer.setIsLand(0, true) | Layer.setClimate(0, VeryTallMountains.getInstance().getClimateId()) | Layer.setBiomeId(0, VeryTallMountains.getInstance().getBiomeId());
        }
        return c;
    }

}
