package task4;

public class Main {
    public static void main(String[] args) {
        CreatorOfFirstPart creatureFirstPart = new CreatorOfFirstPart();
        CreatorOfSecondPart creatureSecondPart = new CreatorOfSecondPart();
        CreatorOfThirdPart creatureThirdPart = new CreatorOfThirdPart();
        Tank tank = new Tank();
        AssemblyLine installationProducts = new AssemblyLine(creatureFirstPart,creatureSecondPart,creatureThirdPart);
        installationProducts.assembleProduct(tank);
        System.out.println();
    }
}