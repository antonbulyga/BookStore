package task4;

public class CreatorOfThirdPart implements ILineStep{
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Tower created");
        return new ProductTower() ;
    }
}