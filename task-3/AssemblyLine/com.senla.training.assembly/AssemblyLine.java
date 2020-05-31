package task4;

public class AssemblyLine implements IAssemblyLine{
   private ILineStep line1;
   private ILineStep line2;
   private ILineStep line3;


    public AssemblyLine(ILineStep line1, ILineStep line2, ILineStep line3) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }

    @Override
    public IProduct assembleProduct(IProduct iProduct) {

        IProductPart part1 = line1.buildProductPart();
        iProduct.installFirstPart(part1);

        IProductPart part2 = line2.buildProductPart();
        iProduct.installSecondPart(part2);

        IProductPart part3 = line3.buildProductPart();
        iProduct.installThirdPart(part3);

        System.out.println("The tank is complete");

        return iProduct;
    }
}