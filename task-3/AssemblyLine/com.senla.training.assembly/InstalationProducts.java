package task4;

public class InstalationProducts implements IAssemblyLine{
    ILineStep line1;
    ILineStep line2;
    ILineStep line3;


    public InstalationProducts(ILineStep line1, ILineStep line2, ILineStep line3) {
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


        Tank instalation = new Tank();
        System.out.println("The tank is complete");

        return instalation;
    }
}