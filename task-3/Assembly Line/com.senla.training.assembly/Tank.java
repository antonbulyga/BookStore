package task4;

public class Tank implements IProduct{

    @Override
    public void installFirstPart(IProductPart productPart) {
        System.out.println("The tank body is installed");
        System.out.println();
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        System.out.println("The engine is installed");
        System.out.println();
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        System.out.println("The tower is installed");
        System.out.println();
    }

}