package maptile;

import gameentity.Direction;
import gameentity.GameEntity;
import window.GamePanel;

public class MapTileCollisionHandler {

    private GamePanel gamePanel;

    public MapTileCollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkCollision(GameEntity gameEntity) {

        // Figure out which tiles the character is facing based on the direction and then check for if the tile is solid
        int entityLeftWorldX = gameEntity.worldX + gameEntity.collisionArea.x;
        int entityRightWorldX = gameEntity.worldX + gameEntity.collisionArea.x + gameEntity.collisionArea.width;
        int entityTopWorldY = gameEntity.worldY + gameEntity.collisionArea.y;
        int entityBottomWorldY = gameEntity.worldY + gameEntity.collisionArea.y + gameEntity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX / GamePanel.TILE_SIZE;
        int entityRightCol = entityRightWorldX / GamePanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY / GamePanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / GamePanel.TILE_SIZE;

        int currentTile1, currentTile2;

        MapTileHandler mapTileHandler = gamePanel.mapTileHandler;

        switch (gameEntity.direction) {
            case Direction.UP:
                entityTopRow = (entityTopWorldY - gameEntity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityTopRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    gameEntity.isColliding = true;
                }
                break;
            case Direction.DOWN:
                entityBottomRow = (entityBottomWorldY + gameEntity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityBottomRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    gameEntity.isColliding = true;
                }
                break;
            case Direction.LEFT:
                entityLeftCol = (entityLeftWorldX - gameEntity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityLeftCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    gameEntity.isColliding = true;
                }
                break;
            case Direction.RIGHT:
                entityRightCol = (entityRightWorldX + gameEntity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityRightCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    gameEntity.isColliding = true;
                }
                break;
        }
    }
}
