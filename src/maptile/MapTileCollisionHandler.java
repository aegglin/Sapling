package maptile;

import gameentity.Direction;
import gameentity.GameEntity;
import gameentity.UserGameEntity;
import window.GamePanel;

public class MapTileCollisionHandler {

    private GamePanel gamePanel;
    private MapTileHandler mapTileHandler;

    public MapTileCollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapTileHandler = gamePanel.mapTileHandler;
    }

    public int[] checkEarshot(UserGameEntity user) {
        // Figure out which tiles the character is facing based on the direction and then check for if the tile is solid
        int entityLeftWorldX = user.worldX + user.hearingArea.x;
        int entityRightWorldX = user.worldX + user.hearingArea.x + user.hearingArea.width;
        int entityTopWorldY = user.worldY + user.hearingArea.y;
        int entityBottomWorldY = user.worldY + user.hearingArea.y + user.hearingArea.height;

        int entityLeftCol = entityLeftWorldX / GamePanel.TILE_SIZE;
        int entityRightCol = entityRightWorldX / GamePanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY / GamePanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / GamePanel.TILE_SIZE;

        int currentTile1 = 0;
        int currentTile2 = 0;

        switch (user.direction) {
            case Direction.UP:
                entityTopRow = (entityTopWorldY - user.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityTopRow];

                if (mapTileHandler.mapTiles[currentTile1].hasSound || mapTileHandler.mapTiles[currentTile2].hasSound) {
                    user.isInEarshot = true;
                }
                break;
            case Direction.DOWN:
                entityBottomRow = (entityBottomWorldY + user.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityBottomRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].hasSound || mapTileHandler.mapTiles[currentTile2].hasSound) {
                    user.isInEarshot = true;
                }
                break;
            case Direction.LEFT:
                entityLeftCol = (entityLeftWorldX - user.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityLeftCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].hasSound || mapTileHandler.mapTiles[currentTile2].hasSound) {
                    user.isInEarshot = true;
                }
                break;
            case Direction.RIGHT:
                entityRightCol = (entityRightWorldX + user.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityRightCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].hasSound || mapTileHandler.mapTiles[currentTile2].hasSound) {
                    user.isInEarshot = true;
                }
                break;
        }
        return new int[] {currentTile1, currentTile2};
    }

    public void checkCollision(GameEntity entity) {

        // Figure out which tiles the character is facing based on the direction and then check for if the tile is solid
        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX / GamePanel.TILE_SIZE;
        int entityRightCol = entityRightWorldX / GamePanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY / GamePanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / GamePanel.TILE_SIZE;

        int currentTile1, currentTile2;

        switch (entity.direction) {
            case Direction.UP:
                entityTopRow = (entityTopWorldY - entity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityTopRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    entity.isColliding = true;
                }
                break;
            case Direction.DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityBottomRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    entity.isColliding = true;
                }
                break;
            case Direction.LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityLeftCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityLeftCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    entity.isColliding = true;
                }
                break;
            case Direction.RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed) / GamePanel.TILE_SIZE;
                currentTile1 = mapTileHandler.mapTileNumbers[entityRightCol][entityTopRow];
                currentTile2 = mapTileHandler.mapTileNumbers[entityRightCol][entityBottomRow];

                if (mapTileHandler.mapTiles[currentTile1].isSolid || mapTileHandler.mapTiles[currentTile2].isSolid) {
                    entity.isColliding = true;
                }
                break;
        }
    }
}
