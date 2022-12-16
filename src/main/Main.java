package main;

public class Main {

	public static void main(String[] args) {
		
		Factura file = new Factura();
		
		file.setUrl("https://servicioscf.afip.gob.ar/Facturacion/facturasApocrifas/DownloadFile.aspx");
		
		file.setName("FacturasApocrifas.zip");
		
		try {
			
			file.downloadFile();
			
			file.decompress();
			
			file.deleteFile();
			
			file.setName("FacturasApocrifas.txt");
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
