package srpfacadelab;

import java.util.List;

public class Action {
    public void use(Item item, RpgPlayer player, IGameEngine engine){
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = engine.getEnemiesNear(player);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    static void calculateStats(RpgPlayer player) {
        for (Item i: player.inventory) {
            player.setArmour(i.getArmour());
        }
    }

    public void takeDamage(int damage, RpgPlayer player, IGameEngine engine) {
        if (damage < player.getArmour()) {
            engine.playSpecialEffect("parry");
        }

        int damageToDeal = damage - player.getArmour();

        if (InventoryHandler.calculateInventoryWeight(player) <= (player.getCarryingCapacity()/2)) {
            damageToDeal = ((damageToDeal * 3)/4);
        }

        player.setHealth(player.getHealth() - damageToDeal);

        engine.playSpecialEffect("lots_of_gore");
    }
}
