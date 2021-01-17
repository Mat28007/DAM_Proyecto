package gui.Documento;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import tablas.SupplierInvoiceLineItems;
import tablas.SupplierInvoices;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.test.annotations.WrapToTest;
import controller.Controller;



@WrapToTest
public class CreacionPdf {
	public static final String DEST = "results/chapter01/factura.pdf";
	private Controller controller=new Controller();
	private int numeroFac,idSupplierInvoices,cantidadComprada;
	double precioC;
	private Date date;
	
    public void createPdf(SupplierInvoices  invoice) throws IOException {
    	System.out.println(DEST);
    	String dest = String.format(DEST,invoice.getIdSupplierInvoices());
    	System.out.println("invoice.getIdSupplierInvoices() "+invoice.getIdSupplierInvoices());
        idSupplierInvoices=invoice.getIdSupplierInvoices();

    	try {
    		SupplierInvoices s=	controller.getProduct(idSupplierInvoices);
    	    numeroFac=	s.getNumeroFactura();
    	    date=s.getFecha();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
    		 SupplierInvoiceLineItems ss=controller.getProductDetail(idSupplierInvoices);
    		
			 precioC=ss.getPrecioCompra();
			 cantidadComprada= ss.getCantidadComprada();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
      
        
    	PdfWriter writer = new PdfWriter(dest);
    	PdfDocument pdf = new PdfDocument(writer);
    	Document document = new Document(pdf);
    	//document.open();
    	
        Paragraph titulo = new Paragraph();
        titulo.setHorizontalAlignment(null);
        titulo.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN));
        titulo.add("***FACTURA DE PROVEEDOR***");
    	
    	
    	Paragraph p1 = new Paragraph();
    	p1.setFont(bold);
    	p1.add("INVOICE ID:");
    	p1.add(new Text(String.format(" %s\n",idSupplierInvoices)));
    	
    	Paragraph p2 = new Paragraph();
    	p2.setFont(font);
    	p2.setTextAlignment(TextAlignment.LEFT);
    	p2.setMultipliedLeading(1);
    	p2.setFontSize(14);
    	p2.add("FACTURA NUMERO : ");
    	p2.add(new Text(String.format(" %s\n",numeroFac)));
    	
    	Paragraph p3 = new Paragraph();
    	p3.setFont(font);
    	p3.add("FECHA FACTURA : ");
    	p3.add(new Text(String.format(" %s\n",date)));
    	
    	document.add(titulo);	
    	document.add(p1);
    	document.add(p2);
    	document.add(p3);
    	
    	document.setMargins(20, 20, 20, 20);

    	Paragraph p4 = new Paragraph();
    	Table table = new Table(3);     
    	table.addHeaderCell("Precio Compra");
    	table.addHeaderCell("Cantidad Comprada");
    	table.addHeaderCell("Iva");
    	table.setWidth(UnitValue.createPercentValue(100));
    	String stringPrecioC=String.valueOf(precioC);
    	
    	String stringCantidadComprada=String.valueOf(cantidadComprada);
    	table.addCell(stringPrecioC);
    	table.addCell(stringCantidadComprada);

    	p4.add(table);
    	document.add(p4);

    	document.close();
/*
		// Add the line items
		document.add(getLineItemTable(invoice, bold));
		
		// Add the grand totals
		document.add(getTotalsTable(

				basic.getTaxBasisTotalAmount(), basic.getTaxTotalAmount(), basic.getGrandTotalAmount(),
				basic.getGrandTotalAmountCurrencyID(),

				basic.getTaxTypeCode(), basic.getTaxApplicablePercent(),

				basic.getTaxBasisAmount(), basic.getTaxCalculatedAmount(), basic.getTaxCalculatedAmountCurrencyID(),
				bold));

		// Add the payment info

		document.add(getPaymentInfo(basic.getPaymentReference(), basic.getPaymentMeansPayeeFinancialInstitutionBIC(),
				basic.getPaymentMeansPayeeAccountIBAN()));
		*/
    }
 

    }


