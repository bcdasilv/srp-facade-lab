package srpfacadelab;

public class InventoryHandler {
    public boolean addItem(Item item, RpgPlayer player, IGameEngine engine) {
        int weight = calculateInventoryWeight(player);
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item, player))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.setHealth(player.getHealth() + item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500) {
                engine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare()) {
            if (item.isUnique()) {
                engine.playSpecialEffect("blue_swirly");
            }
            else {
                engine.playSpecialEffect("cool_swirly_particles");
            }
        }

        player.inventory.add(item);

        Action.calculateStats(player);

        return true;
    }

    static int calculateInventoryWeight(RpgPlayer player) {
        int sum=0;
        for (Item i: player.inventory) {
            sum += i.getWeight();
        }
        return sum;
    }

    boolean checkIfItemExistsInInventory(Item item, RpgPlayer player) {
        for (Item i: player.inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }
}
