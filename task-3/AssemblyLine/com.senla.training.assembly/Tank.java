package task4;

public class Tank implements IProduct{
    protected IProductPart body;
    protected IProductPart tower;
    protected IProductPart engine;


    @Override
    public void installFirstPart(IProductPart productPart) {
        body = productPart;
        System.out.println("The tank body is installed");
        System.out.println();
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        tower = productPart;
        System.out.println("The engine is installed");
        System.out.println();
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        engine = productPart;
        System.out.println("The tower is installed");
        System.out.println();
    }

}