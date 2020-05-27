package task4;

public class CreatureThirdPart implements ILineStep{
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Tower created");
        return new ProductTower() ;
    }
}