package task4;

public class CreatorOfFirstPart implements ILineStep{
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Tank hull created");
        return new ProductBody();
    }
}