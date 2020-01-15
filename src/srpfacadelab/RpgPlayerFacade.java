package srpfacadelab;

public class RpgPlayerFacade {
    public static Action action = new Action();
    public static InventoryHandler inventoryHandler = new InventoryHandler();
    RpgPlayer player;
    IGameEngine engine;

    public RpgPlayerFacade(RpgPlayer player, IGameEngine gameEngine) {
        this.player = player;
        engine = gameEngine;
    }

    public void useItem(Item item) {
        action.use(item, player, engine);
    }

    public void pickupItem(Item item) {
        inventoryHandler.addItem(item, player, engine);
    }

    public void calculateStats() {
        action.calculateStats(player);
    }

    public void checkIfItemExistsInInventory(Item item) {
        inventoryHandler.checkIfItemExistsInInventory(item, player);
    }

    public void calculateInventoryWeight() {
        InventoryHandler.calculateInventoryWeight(player);
    }

    public void takeDamage(int damage) {
        action.takeDamage(damage, player, engine);
    }
}
