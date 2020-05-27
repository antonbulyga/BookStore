package task4;

public class CreatorOfSecondPart implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Engine created");
        return new ProductEngine();
    }
}