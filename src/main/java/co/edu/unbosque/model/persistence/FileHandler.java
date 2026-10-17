package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.edu.unbosque.model.DetalleVentaDTO;
import co.edu.unbosque.model.ProductoDTO;
import co.edu.unbosque.model.VentaDTO;
import co.edu.unbosque.util.enums.EstadoVenta;
import co.edu.unbosque.util.enums.TipoVenta;

/**
 * Handles file operations related to invoices and reports. This class can
 * generate XML and PDF invoices and manages the directories for storing these
 * files.
 * 
 * @author InnovaCore
 */
public class FileHandler {

	/** Directory for storing invoice XML/PDF files */
	private static final String DIR_FACTURAS = "facturas";

	/** Directory for storing report files */
	private static final String DIR_REPORTES = "reportes";

	/**
	 * Default constructor. Ensures that the required directories exist.
	 */
	public FileHandler() {
		createDirectoryIfNotExists();
	}

	/**
	 * Creates invoice and report directories if they do not exist.
	 */
	private void createDirectoryIfNotExists() {
		try {
			Path pathFacturas = Paths.get(DIR_FACTURAS);
			Path pathReportes = Paths.get(DIR_REPORTES);
			if (!Files.exists(pathFacturas))
				Files.createDirectories(pathFacturas);
			if (!Files.exists(pathReportes))
				Files.createDirectories(pathReportes);
		} catch (Exception e) {
		}
	}

	/**
	 * Generates an XML invoice file from a VentaDTO object.
	 * 
	 * @param fileName Name of the file (without extension)
	 * @param data     VentaDTO containing invoice information
	 * @return true if the XML was created successfully, false otherwise
	 */
	public boolean generarFacturaXML(String fileName, VentaDTO data) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			Element root = doc.createElement("factura");
			root.setAttribute("numero", String.valueOf(data.getIdVenta()));
			root.setAttribute("estado", data.getEstado().name());
			root.setAttribute("fecha", data.getFecha().toString());
			root.setAttribute("total", String.valueOf(data.getTotal()));
			doc.appendChild(root);

			Element emisor = doc.createElement("emisor");
			addElement(doc, emisor, "nombre", "Papelería Rosita");
			addElement(doc, emisor, "NIT", "103284183129");
			addElement(doc, emisor, "dirección", "Cra 78 #128-90");
			addElement(doc, emisor, "telefono", "3124513232");
			root.appendChild(emisor);

			Element cliente = doc.createElement("cliente");
			addElement(doc, cliente, "nombre", data.getCliente().getNombre() + " " + data.getCliente().getApellido());
			addElement(doc, cliente, "identificacion", String.valueOf(data.getCliente().getCedula()));
			addElement(doc, cliente, "dirección", data.getCliente().getDireccion());
			addElement(doc, cliente, "telefono", String.valueOf(data.getCliente().getTelefono()));
			root.appendChild(cliente);

			Element items = doc.createElement("items");
			items.setAttribute("cantidad", String.valueOf(data.getListaDetallesVenta().size()));

			for (DetalleVentaDTO detalle : data.getListaDetallesVenta()) {
				Element item = doc.createElement("item");
				addElement(doc, item, "codigo", String.valueOf(detalle.getProducto().getId_producto()));
				addElement(doc, item, "nombre", detalle.getProducto().getNombre());
				addElement(doc, item, "unidades", String.valueOf(detalle.getCantidad()));
				addElement(doc, item, "subtotal", String.valueOf(detalle.getSubtotal()));
				items.appendChild(item);
			}
			items.setAttribute("total", String.valueOf(data.getTotal()));
			root.appendChild(items);

			return saveXMLDocument(doc, fileName);

		} catch (ParserConfigurationException e) {
			return false;
		}
	}

	/**
	 * Saves a Document object as an XML file.
	 * 
	 * @param doc      The XML Document to save
	 * @param fileName Name of the file (without extension)
	 * @return true if saved successfully, false otherwise
	 */
	private boolean saveXMLDocument(Document doc, String fileName) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			String fullPath = DIR_FACTURAS + File.separator + fileName + ".xml";
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fullPath));

			transformer.transform(source, result);
			
			return true;
		} catch (Exception e) {
			
			return false;
		}
	}

	/**
	 * Generates a PDF invoice file from a VentaDTO object.
	 * 
	 * @param fileName Name of the file (without extension)
	 * @param data     VentaDTO containing invoice information
	 * @return true if the PDF was created successfully, false otherwise
	 */
	public boolean generarFacturaPDF(String fileName, VentaDTO data) {
		String fullPath = fileName + ".pdf";

		try {
			File archivoPDF = new File(fullPath);
			File directorioPadre = archivoPDF.getParentFile();
			if (directorioPadre != null && !directorioPadre.exists()) {
				directorioPadre.mkdirs();
			}

			com.itextpdf.text.Document document = new com.itextpdf.text.Document();
			PdfWriter.getInstance(document, new FileOutputStream(archivoPDF));
			document.open();

			@SuppressWarnings("deprecation")
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
			DateTimeFormatter dateOnlyFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			Paragraph titulo = new Paragraph("Factura de venta",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
			titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			document.add(titulo);
			document.add(new Paragraph("Factura No. " + data.getIdVenta()));
			document.add(new Paragraph("Fecha: " + data.getFecha().format(dateOnlyFormat)));
			document.add(new Paragraph("Estado: " + data.getEstado().name()));
			document.add(new Paragraph("Cliente: " + data.getCliente().getNombre() + " "
					+ data.getCliente().getApellido()));
			document.add(new Paragraph("Identificación: " + data.getCliente().getCedula()));
			document.add(new Paragraph("Dirección: " + data.getCliente().getDireccion()));
			document.add(new Paragraph(" "));

			PdfPTable tabla = new PdfPTable(4);
			tabla.setWidthPercentage(100);
			tabla.setWidths(new float[] { 2, 5, 2, 3 });
			for (String encabezado : new String[] { "Código", "Producto", "Cantidad", "Subtotal" }) {
				PdfPCell celda = new PdfPCell(new Phrase(encabezado,
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
				celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
				tabla.addCell(celda);
			}
			for (DetalleVentaDTO detalle : data.getListaDetallesVenta()) {
				tabla.addCell(String.valueOf(detalle.getProducto().getCodigo()));
				tabla.addCell(detalle.getProducto().getNombre());
				tabla.addCell(String.valueOf(detalle.getCantidad()));
				tabla.addCell(currencyFormat.format(detalle.getSubtotal()));
			}
			document.add(tabla);
			document.add(new Paragraph("Total: " + currencyFormat.format(data.getTotal()),
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK)));

			document.close();
			return true;

		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Helper method to add an element with text content to a parent element.
	 * 
	 * @param doc     XML Document
	 * @param parent  Parent element
	 * @param tagName Name of the new element
	 * @param context Text content of the new element
	 */
	private void addElement(Document doc, Element parent, String tagName, String context) {
		Element element = doc.createElement(tagName);
		element.setTextContent(context);
		parent.appendChild(element);
	}

	/**
	 * Returns the directory path for invoices.
	 * 
	 * @return invoice directory
	 */
	public static String getDirFacturas() {
		return DIR_FACTURAS;
	}

	/**
	 * Returns the directory path for reports.
	 * 
	 * @return report directory
	 */
	public String getDirReportes() {
		return DIR_REPORTES;
	}

	/**
	 * Generates an inventory report in PDF format.
	 * 
	 * @param fileName  Name of the file (without extension)
	 * @param productos List of products to include in the report
	 * @return true if the PDF was created successfully, false otherwise
	 */
	public boolean generarReporteInventarioPDF(String rutaCompleta, List<ProductoDTO> productos) {
		com.itextpdf.text.Document pdfDocument = null;
		FileOutputStream fileOutput = null;

		try {

			// 1. Validar que hay productos
			if (productos == null || productos.isEmpty()) {
				return false;
			}

			// 2. Crear el archivo y su directorio padre
			File archivoPDF = new File(rutaCompleta);

			// 3. Crear el directorio padre si no existe
			File directorioPadre = archivoPDF.getParentFile();
			if (directorioPadre != null && !directorioPadre.exists()) {
				boolean directorioCreado = directorioPadre.mkdirs();

				if (!directorioCreado) {

					return false;
				}
			}

			// 4. Verificar permisos de escritura
			if (directorioPadre != null && !directorioPadre.canWrite()) {

				return false;
			}

			// 5. Crear el documento PDF
			pdfDocument = new com.itextpdf.text.Document();
			fileOutput = new FileOutputStream(archivoPDF);
			PdfWriter.getInstance(pdfDocument, fileOutput);

			// 6. Abrir el documento
			pdfDocument.open();

			// 7. Formatear números y fechas
			@SuppressWarnings("deprecation")
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

			// ==================== HEADER ====================
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
			Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);

			Paragraph title = new Paragraph("REPORTE DE INVENTARIO", titleFont);
			title.setAlignment(Paragraph.ALIGN_CENTER);
			title.setSpacingAfter(10);
			pdfDocument.add(title);

			Paragraph subtitle = new Paragraph("Papelería Rosita", subtitleFont);
			subtitle.setAlignment(Paragraph.ALIGN_CENTER);
			pdfDocument.add(subtitle);

			Paragraph date = new Paragraph("Fecha de generación: " + LocalDateTime.now().format(dateFormat),
					subtitleFont);
			date.setAlignment(Paragraph.ALIGN_CENTER);
			date.setSpacingAfter(20);
			pdfDocument.add(date);

			pdfDocument.add(new Paragraph(" "));

			// ==================== SUMMARY ====================
			Font summaryFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.BLACK);

			int totalProductos = productos.size();
			int productosStockBajo = (int) productos.stream().filter(p -> p.getStock() <= p.getStock_minimo()).count();
			double valorTotalInventario = productos.stream().mapToDouble(p -> p.getPrecio_compra() * p.getStock())
					.sum();
			double valorTotalVenta = productos.stream().mapToDouble(p -> p.getPrecio_venta() * p.getStock()).sum();

			PdfPTable summaryTable = new PdfPTable(2);
			summaryTable.setWidthPercentage(100);
			summaryTable.setSpacingAfter(15);

			addSummaryCell(summaryTable, "Total de Productos:", String.valueOf(totalProductos), summaryFont);
			addSummaryCell(summaryTable, "Productos con Stock Bajo:", String.valueOf(productosStockBajo), summaryFont);
			addSummaryCell(summaryTable, "Valor Total (Compra):", currencyFormat.format(valorTotalInventario),
					summaryFont);
			addSummaryCell(summaryTable, "Valor Total (Venta):", currencyFormat.format(valorTotalVenta), summaryFont);

			pdfDocument.add(summaryTable);
			pdfDocument.add(new Paragraph(" "));

			// ==================== PRODUCTS TABLE ====================
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.WHITE);
			Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);

			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.2f, 2f, 1.5f, 1.5f, 1.2f, 1.2f, 2f, 1.5f });

			// Table headers
			String[] headers = { "Código", "Nombre", "P. Compra", "P. Venta", "Stock", "Stock Mín.", "Proveedor",
					"Estado" };
			for (String header : headers) {
				PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
				cell.setBackgroundColor(new BaseColor(52, 73, 94));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				cell.setPadding(8);
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
			}

			// Table rows
			for (ProductoDTO producto : productos) {
				boolean stockBajo = producto.getStock() <= producto.getStock_minimo();
				BaseColor rowColor = stockBajo ? new BaseColor(255, 240, 240) : BaseColor.WHITE;
				Font rowFont = stockBajo ? FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.RED) : cellFont;

				addProductCell(table, producto.getCodigo(), rowFont, rowColor, PdfPCell.ALIGN_LEFT);
				addProductCell(table, producto.getNombre(), rowFont, rowColor, PdfPCell.ALIGN_LEFT);
				addProductCell(table, currencyFormat.format(producto.getPrecio_compra()), rowFont, rowColor,
						PdfPCell.ALIGN_RIGHT);
				addProductCell(table, currencyFormat.format(producto.getPrecio_venta()), rowFont, rowColor,
						PdfPCell.ALIGN_RIGHT);
				addProductCell(table, String.valueOf(producto.getStock()), rowFont, rowColor, PdfPCell.ALIGN_CENTER);
				addProductCell(table, String.valueOf(producto.getStock_minimo()), rowFont, rowColor,
						PdfPCell.ALIGN_CENTER);
				addProductCell(table, producto.getProveedor().getNombre(), rowFont, rowColor, PdfPCell.ALIGN_LEFT);

				String estado = stockBajo ? "BAJO" : "OK";
				addProductCell(table, estado, rowFont, rowColor, PdfPCell.ALIGN_CENTER);
			}

			pdfDocument.add(table);

			// ==================== FOOTER ====================
			pdfDocument.add(new Paragraph(" "));
			Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY);
			Paragraph footer = new Paragraph("Los productos marcados en rojo requieren reabastecimiento urgente.",
					footerFont);
			footer.setAlignment(Paragraph.ALIGN_LEFT);
			pdfDocument.add(footer);

			// 8. IMPORTANTE: Cerrar el documento antes de verificar
			pdfDocument.close();

			// 9. Cerrar el FileOutputStream
			if (fileOutput != null) {
				fileOutput.close();

			}

			// 10. Verificar que el archivo se creó
			if (archivoPDF.exists() && archivoPDF.length() > 0) {

				return true;
			} else {

				return false;
			}

		} catch (DocumentException e) {
			
			e.printStackTrace();
			return false;
		} catch (IOException e) {
	
			e.printStackTrace();
			return false;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			// Asegurarse de cerrar recursos
			try {
				if (pdfDocument != null && ((com.itextpdf.text.Document) pdfDocument).isOpen()) {
					(pdfDocument).close();
			
				}
				if (fileOutput != null) {
					fileOutput.close();
			
				}
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Generates a sales report in PDF format.
	 * 
	 * @param rutaCompleta Complete path for the PDF file
	 * @param ventas       List of sales to include in the report
	 * @return true if the PDF was created successfully, false otherwise
	 */
	public boolean generarReporteVentasPDF(String rutaCompleta, List<VentaDTO> ventas) {
		com.itextpdf.text.Document pdfDocument = null;
		FileOutputStream fileOutput = null;

		try {
			// 1. Validate that there are sales
			if (ventas == null || ventas.isEmpty()) {
				return false;
			}

			// 2. Create the file and its parent directory
			File archivoPDF = new File(rutaCompleta);

			// 3. Create parent directory if it doesn't exist
			File directorioPadre = archivoPDF.getParentFile();
			if (directorioPadre != null && !directorioPadre.exists()) {
				boolean directorioCreado = directorioPadre.mkdirs();
				if (!directorioCreado) {
					return false;
				}
			}

			// 4. Check write permissions
			if (directorioPadre != null && !directorioPadre.canWrite()) {
				return false;
			}

			// 5. Create PDF document
			pdfDocument = new com.itextpdf.text.Document();
			fileOutput = new FileOutputStream(archivoPDF);
			PdfWriter.getInstance(pdfDocument, fileOutput);

			// 6. Open document
			pdfDocument.open();

			// 7. Format numbers and dates
			@SuppressWarnings("deprecation")
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter fullDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

			// ==================== HEADER ====================
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
			Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);

			Paragraph title = new Paragraph("REPORTE DE VENTAS", titleFont);
			title.setAlignment(Paragraph.ALIGN_CENTER);
			title.setSpacingAfter(10);
			pdfDocument.add(title);

			Paragraph subtitle = new Paragraph("Papelería Rosita", subtitleFont);
			subtitle.setAlignment(Paragraph.ALIGN_CENTER);
			pdfDocument.add(subtitle);

			Paragraph date = new Paragraph("Fecha de generación: " + LocalDateTime.now().format(fullDateFormat),
					subtitleFont);
			date.setAlignment(Paragraph.ALIGN_CENTER);
			date.setSpacingAfter(20);
			pdfDocument.add(date);

			pdfDocument.add(new Paragraph(" "));

			// ==================== SUMMARY ====================
			Font summaryFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.BLACK);

			int totalVentas = ventas.size();
			long ventasPagadas = ventas.stream().filter(v -> v.getTipoVenta() == TipoVenta.PAGADA).count();
			long ventasFiadas = ventas.stream().filter(v -> v.getTipoVenta() == TipoVenta.FIADA).count();

			long ventasPendientes = ventas.stream().filter(v -> v.getEstado() == EstadoVenta.PENDIENTE).count();
			long ventasContingencia = ventas.stream().filter(v -> v.getEstado() == EstadoVenta.CONTINGENCIA).count();
			double totalIngresos = ventas.stream().filter(v -> v.getTipoVenta() == TipoVenta.PAGADA)
					.mapToDouble(VentaDTO::getTotal).sum();
			double totalPorCobrar = ventas.stream().filter(v -> v.getTipoVenta() == TipoVenta.FIADA)
					.mapToDouble(VentaDTO::getTotal).sum();
			double totalGeneral = ventas.stream().mapToDouble(VentaDTO::getTotal).sum();

			PdfPTable summaryTable = new PdfPTable(2);
			summaryTable.setWidthPercentage(100);
			summaryTable.setSpacingAfter(15);

			addSummaryCell(summaryTable, "Total de Ventas:", String.valueOf(totalVentas), summaryFont);
			addSummaryCell(summaryTable, "Ventas Pagadas:", String.valueOf(ventasPagadas), summaryFont);
			addSummaryCell(summaryTable, "Ventas Fiadas:", String.valueOf(ventasFiadas), summaryFont);
			addSummaryCell(summaryTable, "Ventas Pendientes:", String.valueOf(ventasPendientes), summaryFont);
			addSummaryCell(summaryTable, "Ventas en Contingencia:", String.valueOf(ventasContingencia), summaryFont);
			addSummaryCell(summaryTable, "Total Ingresos (Pagadas):", currencyFormat.format(totalIngresos),
					summaryFont);
			addSummaryCell(summaryTable, "Total Por Cobrar (Fiadas):", currencyFormat.format(totalPorCobrar),
					summaryFont);
			addSummaryCell(summaryTable, "Total General:", currencyFormat.format(totalGeneral), summaryFont);

			pdfDocument.add(summaryTable);
			pdfDocument.add(new Paragraph(" "));

			// ==================== SALES TABLE ====================
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.WHITE);
			Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);

			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1f, 1.5f, 2.5f, 1.8f, 1.5f, 1.5f, 1.5f });

			// Table headers
			String[] headers = { "ID", "Fecha", "Cliente", "Total", "Tipo", "Estado", "Items" };
			for (String header : headers) {
				PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
				cell.setBackgroundColor(new BaseColor(52, 73, 94));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				cell.setPadding(8);
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
			}

			// Table rows
			for (VentaDTO venta : ventas) {
				boolean esFiada = venta.getTipoVenta() == TipoVenta.FIADA;
				boolean esPendiente = venta.getEstado() == EstadoVenta.PENDIENTE;

				BaseColor rowColor = esFiada ? new BaseColor(255, 245, 230)
						: esPendiente ? new BaseColor(255, 240, 240) : BaseColor.WHITE;

				Font rowFont = (esFiada || esPendiente) ? FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8,
						esFiada ? new BaseColor(255, 140, 0) : BaseColor.RED) : cellFont;

				String clienteNombre = venta.getCliente() != null
						? venta.getCliente().getNombre() + " " + venta.getCliente().getApellido()
						: "N/A";

				int cantidadItems = venta.getListaDetallesVenta() != null ? venta.getListaDetallesVenta().size() : 0;

				addVentaCell(table, String.valueOf(venta.getIdVenta()), rowFont, rowColor, PdfPCell.ALIGN_CENTER);
				addVentaCell(table, venta.getFecha().format(dateFormat), rowFont, rowColor, PdfPCell.ALIGN_CENTER);
				addVentaCell(table, clienteNombre, rowFont, rowColor, PdfPCell.ALIGN_LEFT);
				addVentaCell(table, currencyFormat.format(venta.getTotal()), rowFont, rowColor, PdfPCell.ALIGN_RIGHT);
				addVentaCell(table, venta.getTipoVenta().name(), rowFont, rowColor, PdfPCell.ALIGN_CENTER);
				addVentaCell(table, venta.getEstado().name(), rowFont, rowColor, PdfPCell.ALIGN_CENTER);
				addVentaCell(table, String.valueOf(cantidadItems), rowFont, rowColor, PdfPCell.ALIGN_CENTER);
			}

			pdfDocument.add(table);

			// ==================== FOOTER ====================
			pdfDocument.add(new Paragraph(" "));
			Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY);

			Paragraph footer1 = new Paragraph("Las ventas fiadas (naranja) están pendientes de cobro.", footerFont);
			footer1.setAlignment(Paragraph.ALIGN_LEFT);
			pdfDocument.add(footer1);

			Paragraph footer2 = new Paragraph("Las ventas pendientes (rojo) requieren validación.", footerFont);
			footer2.setAlignment(Paragraph.ALIGN_LEFT);
			pdfDocument.add(footer2);

			// 8. Close document before verification
			pdfDocument.close();

			// 9. Close FileOutputStream
			if (fileOutput != null) {
				fileOutput.close();
			}

			// 10. Verify file was created
			if (archivoPDF.exists() && archivoPDF.length() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pdfDocument != null && pdfDocument.isOpen()) {
					pdfDocument.close();
				}
				if (fileOutput != null) {
					fileOutput.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Helper method to add a cell to the sales table.
	 */
	private void addVentaCell(PdfPTable table, String text, Font font, BaseColor bgColor, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBackgroundColor(bgColor);
		cell.setHorizontalAlignment(alignment);
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setPadding(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}

	/**
	 * Helper method to add a cell to the summary table.
	 */
	private void addSummaryCell(PdfPTable table, String label, String value, Font font) {
		PdfPCell labelCell = new PdfPCell(new Phrase(label, font));
		labelCell.setBorder(Rectangle.NO_BORDER);
		labelCell.setPadding(5);
		labelCell.setBackgroundColor(new BaseColor(236, 240, 241));
		table.addCell(labelCell);

		PdfPCell valueCell = new PdfPCell(new Phrase(value, font));
		valueCell.setBorder(Rectangle.NO_BORDER);
		valueCell.setPadding(5);
		valueCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		valueCell.setBackgroundColor(BaseColor.WHITE);
		table.addCell(valueCell);
	}

	/**
	 * Helper method to add a cell to the products table.
	 */
	private void addProductCell(PdfPTable table, String text, Font font, BaseColor bgColor, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBackgroundColor(bgColor);
		cell.setHorizontalAlignment(alignment);
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setPadding(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	}


/**
 * Generates a comprehensive general report in PDF format combining sales and inventory data.
 * 
 * @param rutaCompleta Complete path for the PDF file
 * @param ventas List of sales to include in the report
 * @param productos List of products to include in the report
 * @return true if the PDF was created successfully, false otherwise
 */
public boolean generarReporteGeneralPDF(String rutaCompleta, List<VentaDTO> ventas, List<ProductoDTO> productos) {
	com.itextpdf.text.Document pdfDocument = null;
	FileOutputStream fileOutput = null;

	try {
		// 1. Validate data
		if ((ventas == null || ventas.isEmpty()) && (productos == null || productos.isEmpty())) {
			System.err.println("❌ No hay datos para generar el reporte");
			return false;
		}

		// 2. Create file and parent directory
		File archivoPDF = new File(rutaCompleta);
	
		
		File directorioPadre = archivoPDF.getParentFile();
		
		if (directorioPadre != null && !directorioPadre.exists()) {
			
			boolean directorioCreado = directorioPadre.mkdirs();
			if (!directorioCreado) {
			
				return false;
			}
			
		}

		if (directorioPadre != null && !directorioPadre.canWrite()) {
			
			return false;
		}

		// 3. Create PDF document

		pdfDocument = new com.itextpdf.text.Document();
		fileOutput = new FileOutputStream(archivoPDF);
		
	
		pdfDocument.open();
	

		// 4. Format numbers and dates
		
		@SuppressWarnings("deprecation")
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
		DateTimeFormatter fullDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		// ==================== HEADER ====================
		
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
		Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
		Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, new BaseColor(52, 73, 94));

		Paragraph title = new Paragraph("REPORTE GENERAL DEL NEGOCIO", titleFont);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		title.setSpacingAfter(10);
		pdfDocument.add(title);

		Paragraph subtitle = new Paragraph("Papelería Rosita", subtitleFont);
		subtitle.setAlignment(Paragraph.ALIGN_CENTER);
		pdfDocument.add(subtitle);

		Paragraph date = new Paragraph("Fecha de generación: " + LocalDateTime.now().format(fullDateFormat),
				subtitleFont);
		date.setAlignment(Paragraph.ALIGN_CENTER);
		date.setSpacingAfter(25);
		pdfDocument.add(date);

		// ==================== EXECUTIVE SUMMARY ====================
		
		Paragraph execSummaryTitle = new Paragraph("RESUMEN EJECUTIVO", sectionFont);
		execSummaryTitle.setSpacingBefore(10);
		execSummaryTitle.setSpacingAfter(15);
		pdfDocument.add(execSummaryTitle);

		Font summaryFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.BLACK);
		
		

		int totalProductos = productos != null ? productos.size() : 0;
		int productosStockBajo = productos != null ? 
				(int) productos.stream().filter(p -> p.getStock() <= p.getStock_minimo()).count() : 0;
		double valorInventarioCompra = productos != null ? 
				productos.stream().mapToDouble(p -> p.getPrecio_compra() * p.getStock()).sum() : 0;
		double valorInventarioVenta = productos != null ? 
				productos.stream().mapToDouble(p -> p.getPrecio_venta() * p.getStock()).sum() : 0;
		double margenPotencial = valorInventarioVenta - valorInventarioCompra;
		
		int totalVentas = ventas != null ? ventas.size() : 0;
		double totalIngresosReales = ventas != null ? 
				ventas.stream().filter(v -> v.getTipoVenta() == TipoVenta.PAGADA)
						.mapToDouble(VentaDTO::getTotal).sum() : 0;
		double totalPorCobrar = ventas != null ? 
				ventas.stream().filter(v -> v.getTipoVenta() == TipoVenta.FIADA)
						.mapToDouble(VentaDTO::getTotal).sum() : 0;
		double totalVentasGeneral = ventas != null ? 
				ventas.stream().mapToDouble(VentaDTO::getTotal).sum() : 0;
		
		// Cash flow y liquidez
		double liquidezDisponible = totalIngresosReales;
		double activosCirculantes = valorInventarioVenta + totalPorCobrar + liquidezDisponible;
		double rotacionPotencial = totalVentasGeneral > 0 && valorInventarioVenta > 0 ? 
				totalVentasGeneral / valorInventarioVenta : 0;


		
		PdfPTable execTable = new PdfPTable(2);
		execTable.setWidthPercentage(100);
		execTable.setSpacingAfter(20);

		// Inventory Section
		addSectionHeader(execTable, "INVENTARIO", summaryFont);
		addSummaryCell(execTable, "Total de Productos:", String.valueOf(totalProductos), summaryFont);
		addSummaryCell(execTable, "Productos con Stock Bajo:", String.valueOf(productosStockBajo), summaryFont);
		addSummaryCell(execTable, "Valor Inventario (Compra):", currencyFormat.format(valorInventarioCompra), summaryFont);
		addSummaryCell(execTable, "Valor Inventario (Venta):", currencyFormat.format(valorInventarioVenta), summaryFont);
		addSummaryCell(execTable, "Margen Potencial:", currencyFormat.format(margenPotencial), summaryFont);

		// Sales Section
		addSectionHeader(execTable, "VENTAS", summaryFont);
		addSummaryCell(execTable, "Total de Ventas:", String.valueOf(totalVentas), summaryFont);
		addSummaryCell(execTable, "Ingresos Reales (Pagadas):", currencyFormat.format(totalIngresosReales), summaryFont);
		addSummaryCell(execTable, "Por Cobrar (Fiadas):", currencyFormat.format(totalPorCobrar), summaryFont);
		addSummaryCell(execTable, "Total Ventas General:", currencyFormat.format(totalVentasGeneral), summaryFont);

		// Financial Health
		addSectionHeader(execTable, "SALUD FINANCIERA", summaryFont);
		addSummaryCell(execTable, "Liquidez Disponible:", currencyFormat.format(liquidezDisponible), summaryFont);
		addSummaryCell(execTable, "Activos Circulantes:", currencyFormat.format(activosCirculantes), summaryFont);
		addSummaryCell(execTable, "Rotación de Inventario:", String.format("%.2fx", rotacionPotencial), summaryFont);

		pdfDocument.add(execTable);


		// ==================== TOP PRODUCTS BY SALES ====================
		if (ventas != null && !ventas.isEmpty()) {
			
			Paragraph topProductsTitle = new Paragraph("TOP 10 PRODUCTOS MÁS VENDIDOS", sectionFont);
			topProductsTitle.setSpacingBefore(15);
			topProductsTitle.setSpacingAfter(10);
			pdfDocument.add(topProductsTitle);

			// Calculate top products
			java.util.Map<String, Integer> productosVendidos = new java.util.HashMap<>();
			java.util.Map<String, Double> ingresosProducto = new java.util.HashMap<>();
			
			for (VentaDTO venta : ventas) {
				if (venta.getListaDetallesVenta() != null) {
					for (DetalleVentaDTO detalle : venta.getListaDetallesVenta()) {
						if (detalle != null && detalle.getProducto() != null && detalle.getProducto().getNombre() != null) {
							String nombreProducto = detalle.getProducto().getNombre();
							productosVendidos.merge(nombreProducto, detalle.getCantidad(), Integer::sum);
							ingresosProducto.merge(nombreProducto, (double) detalle.getSubtotal(), Double::sum);
						}
					}
				}
			}
			

			// Only create table if there are products
			if (!productosVendidos.isEmpty()) {
				java.util.List<java.util.Map.Entry<String, Integer>> topProductos = productosVendidos.entrySet().stream()
						.sorted(java.util.Map.Entry.<String, Integer>comparingByValue().reversed())
						.limit(10)
						.collect(java.util.stream.Collectors.toList());

				Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.WHITE);
				Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);

				PdfPTable topTable = new PdfPTable(4);
				topTable.setWidthPercentage(100);
				topTable.setWidths(new float[] { 1f, 3f, 1.5f, 2f });
				topTable.setSpacingAfter(20);

				String[] headers1 = { "#", "Producto", "Unidades", "Ingresos" };
				for (String header : headers1) {
					PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
					cell.setBackgroundColor(new BaseColor(41, 128, 185));
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					cell.setPadding(8);
					cell.setBorder(Rectangle.NO_BORDER);
					topTable.addCell(cell);
				}

				int rank = 1;
				for (java.util.Map.Entry<String, Integer> entry : topProductos) {
					BaseColor rowColor = rank % 2 == 0 ? new BaseColor(236, 240, 241) : BaseColor.WHITE;
					
					addTopProductCell(topTable, String.valueOf(rank), cellFont, rowColor, PdfPCell.ALIGN_CENTER);
					addTopProductCell(topTable, entry.getKey(), cellFont, rowColor, PdfPCell.ALIGN_LEFT);
					addTopProductCell(topTable, String.valueOf(entry.getValue()), cellFont, rowColor, PdfPCell.ALIGN_CENTER);
					
					Double ingresos = ingresosProducto.get(entry.getKey());
					String ingresosStr = ingresos != null ? currencyFormat.format(ingresos) : "$0";
					addTopProductCell(topTable, ingresosStr, cellFont, rowColor, PdfPCell.ALIGN_RIGHT);
					rank++;
				}

				pdfDocument.add(topTable);
				
			} else {
				
			}
		}

		// ==================== CRITICAL ALERTS ====================
		
		Paragraph alertsTitle = new Paragraph("ALERTAS Y RECOMENDACIONES", sectionFont);
		alertsTitle.setSpacingBefore(15);
		alertsTitle.setSpacingAfter(10);
		pdfDocument.add(alertsTitle);

		Font alertFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
		Font alertBoldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);

		// Stock alerts
		if (productosStockBajo > 0) {
			Paragraph alert1 = new Paragraph();
			alert1.add(new Chunk("⚠ STOCK BAJO: ", alertBoldFont));
			alert1.add(new Chunk(productosStockBajo + " productos requieren reabastecimiento urgente.", alertFont));
			alert1.setSpacingAfter(8);
			pdfDocument.add(alert1);
		}

		// Accounts receivable alerts
		if (totalPorCobrar > 0) {
			long ventasFiadas = ventas.stream().filter(v -> v.getTipoVenta() == TipoVenta.FIADA).count();
			Paragraph alert2 = new Paragraph();
			alert2.add(new Chunk("💰 CUENTAS POR COBRAR: ", alertBoldFont));
			alert2.add(new Chunk(ventasFiadas + " ventas fiadas por un total de " + 
					currencyFormat.format(totalPorCobrar) + ".", alertFont));
			alert2.setSpacingAfter(8);
			pdfDocument.add(alert2);
		}

		// Pending sales alerts
		if (ventas != null) {
			long ventasPendientes = ventas.stream()
					.filter(v -> v.getEstado() == EstadoVenta.PENDIENTE).count();
			if (ventasPendientes > 0) {
				Paragraph alert3 = new Paragraph();
				alert3.add(new Chunk("📋 VENTAS PENDIENTES: ", alertBoldFont));
				alert3.add(new Chunk(ventasPendientes + " ventas requieren validación.", alertFont));
				alert3.setSpacingAfter(8);
				pdfDocument.add(alert3);
			}
		}

		// Positive indicators
		if (rotacionPotencial > 2) {
			Paragraph alert4 = new Paragraph();
			alert4.add(new Chunk("✓ BUENA ROTACIÓN: ", alertBoldFont));
			alert4.add(new Chunk("El inventario tiene una rotación saludable de " + 
					String.format("%.2fx", rotacionPotencial) + ".", alertFont));
			alert4.setSpacingAfter(8);
			pdfDocument.add(alert4);
		}

		if (margenPotencial > 0 && valorInventarioCompra > 0) {
			double porcentajeMargen = (margenPotencial / valorInventarioCompra) * 100;
			Paragraph alert5 = new Paragraph();
			alert5.add(new Chunk("✓ MARGEN SALUDABLE: ", alertBoldFont));
			alert5.add(new Chunk("Margen de ganancia potencial del " + 
					String.format("%.1f%%", porcentajeMargen) + ".", alertFont));
			alert5.setSpacingAfter(8);
			pdfDocument.add(alert5);
		}

		// ==================== FOOTER ====================
		
		pdfDocument.add(new Paragraph(" "));
		pdfDocument.add(new Paragraph(" "));
		Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY);
		Paragraph footer = new Paragraph("Este reporte proporciona una visión general del estado del negocio. " +
				"Se recomienda revisar los reportes detallados de inventario y ventas para análisis específicos.",
				footerFont);
		footer.setAlignment(Paragraph.ALIGN_CENTER);
		pdfDocument.add(footer);
		


		// Close document BEFORE verification

		pdfDocument.close();

		
		if (fileOutput != null) {
			fileOutput.close();

		}

		// Verify file creation

		if (archivoPDF.exists()) {
			long tamanio = archivoPDF.length();

			
			if (tamanio > 0) {

				return true;
			} else {

				return false;
			}
		} else {

			return false;
		}

	} catch (DocumentException e) {

		e.printStackTrace();
		return false;
	} catch (IOException e) {

		e.printStackTrace();
		return false;
	} catch (Exception e) {

		e.printStackTrace();
		return false;
	} finally {
		try {
			if (pdfDocument != null && pdfDocument.isOpen()) {
				pdfDocument.close();

			}
			if (fileOutput != null) {
				fileOutput.close();
	
			}
		} catch (Exception e) {

		}
	}
}

/**
 * Helper method to add a section header spanning two columns.
 */
private void addSectionHeader(PdfPTable table, String title, Font font) {
	PdfPCell cell = new PdfPCell(new Phrase(title, font));
	cell.setColspan(2);
	cell.setBackgroundColor(new BaseColor(52, 73, 94));
	cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	cell.setPadding(8);
	cell.setBorder(Rectangle.NO_BORDER);
	Font whiteFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
	cell.setPhrase(new Phrase(title, whiteFont));
	table.addCell(cell);
}

/**
 * Helper method to add a cell to the top products table.
 */
private void addTopProductCell(PdfPTable table, String text, Font font, BaseColor bgColor, int alignment) {
	PdfPCell cell = new PdfPCell(new Phrase(text, font));
	cell.setBackgroundColor(bgColor);
	cell.setHorizontalAlignment(alignment);
	cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	cell.setPadding(6);
	cell.setBorder(Rectangle.NO_BORDER);
	table.addCell(cell);
}
}
