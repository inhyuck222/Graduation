package zebra.manager;

/**
 * Created by multimedia on 2016-05-21.
 */
public class BarcodeManager {
    private static BarcodeManager instance;

    public static BarcodeManager getInstance(){
        if(instance == null){
            instance = new BarcodeManager();
        }
        return instance;
    }

    private String barcode;

    private BarcodeManager(){
        barcode = null;
    }

    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    public String getBarcode(){
        return barcode;
    }
}
