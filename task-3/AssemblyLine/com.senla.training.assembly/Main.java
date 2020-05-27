package task4;

public class Main {
    public static void main(String[] args) {
        CreatureFirstPart creatureFirstPart = new CreatureFirstPart();
        CreatureSecondPart creatureSecondPart = new CreatureSecondPart();
        CreatureThirdPart creatureThirdPart = new CreatureThirdPart();
        Tank tank = new Tank();
        InstalationProducts instalationProducts = new InstalationProducts(creatureFirstPart,creatureSecondPart,creatureThirdPart);
        instalationProducts.assembleProduct(tank);
        System.out.println();
    }
}