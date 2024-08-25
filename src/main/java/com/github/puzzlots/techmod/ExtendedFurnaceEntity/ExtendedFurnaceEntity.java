package com.github.puzzlots.techmod.ExtendedFurnaceEntity;

import com.github.puzzle.annotations.Internal;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntityFurnace;
import finalforeach.cosmicreach.io.CRBinDeserializer;
import finalforeach.cosmicreach.io.CRBinSerializer;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;

public class ExtendedFurnaceEntity extends BlockEntityFurnace {
    public Zone zone;
    public int x;
    public int y;
    public int z;

    public ExtendedFurnaceEntity(Zone zone, int globalX, int globalY, int globalZ) {
        super(zone, globalX, globalY, globalZ);
        this.x = globalX;
        this.y = globalY;
        this.z = globalZ;
    }

    public String getBlockEntityId() {
        return "puzzle:extendedBlockEntity";
    }

    public void read(CRBinDeserializer deserial) {
        this.x = deserial.readInt("x", this.x);
        this.y = deserial.readInt("y", this.y);
        this.z = deserial.readInt("z", this.z);
    }

    public void write(CRBinSerializer serial) {
        serial.writeString("stringId", this.getBlockEntityId());
        serial.writeInt("x", this.x);
        serial.writeInt("y", this.y);
        serial.writeInt("z", this.z);
    }

    public int getGlobalX() {
        return this.x;
    }

    public int getGlobalY() {
        return this.y;
    }

    public int getGlobalZ() {
        return this.z;
    }

    @Internal
    public void initialize(Chunk chunk, int localX, int localY, int localZ) {
        this.zone = chunk.getZone();
        this.x = chunk.blockX + localX;
        this.y = chunk.blockY + localY;
        this.z = chunk.blockZ + localZ;
    }
}