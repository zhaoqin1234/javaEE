package com.hrxt.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

	/**
	 * Excel导入
	 * return List<List<Object>>
	 */
	public static List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception {
		List<List<Object>> list = null;
		// 创建Excel工作薄判断格式
		Workbook work = getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		list = new ArrayList<List<Object>>();
		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
		
			if (sheet == null) {
				continue;
			}
			// 遍历当前sheet中的所有行
			// 包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				// 读取一行
				row = sheet.getRow(j);
				// 去掉空行和表头
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				// 遍历所有的列
				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(getCellValue(cell));
				}
				list.add(li);
			}
		}
		return list;
	}
	/**
	 * Excel导入全部sheet
	 * List<map<String, Object>>
	 */
	public static List<Map<String, Object>> getDataListByexcel(int startRow,InputStream in,String fileName,
			String[] fields) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Workbook workbook = getWorkbook(in, fileName);
		if (null == workbook) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		int i = startRow;
		for (int k = 0; k < workbook.getNumberOfSheets(); k++){
			sheet = workbook.getSheetAt(k);
			if (sheet == null) {
				continue;
			}
			while(true){
				row = sheet.getRow(i);
				if (row == null){
					break;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				Boolean flag = false;
				for(int j = 0; j < fields.length; j++){
					cell = row.getCell(j);
					Object cellValue = getCellValue(cell);
					if(null != cellValue && !cellValue.equals("")){
						flag = true;
					}
                    map.put(fields[j], cellValue);
				}
				if(flag){
					list.add(map);
				}
				i++;
			}
		}
		return list;
	}
	
	/**
	 * Excel导入指定sheet
	 * List<map<String, Object>>
	 */
	public static List<Map<String, Object>> getDataListByexcel(int sheetIndex, int startRow,InputStream in,
			String fileName,String[] fields) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Workbook workbook = getWorkbook(in, fileName);
		if (null == workbook) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		int i = startRow;
		sheet = workbook.getSheetAt(sheetIndex);
		if (sheet == null) {
			return null;
		}
		while(true){
			row = sheet.getRow(i);
			if (row == null){
				break;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			Boolean flag = false;
			for(int j = 0; j < fields.length; j++){
				cell = row.getCell(j);
				Object cellValue = getCellValue(cell);
				if(null != cellValue && !cellValue.equals("")){
					flag = true;
				}
                map.put(fields[j], cellValue);
			}
			if(flag){
				list.add(map);
			}
			i++;
		}
		
		return list;
	}
	
	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 */
	public static Workbook getWorkbook(InputStream inStr, String fileName)
			throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 */
	static int sum=0;
	public static Object getCellValue(Cell cell) {
		Object value = null;
		///DecimalFormat df = new DecimalFormat("0"); // 格式化字符类型的数字
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		//DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字
		if(cell!=null){
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if ("General".equals(cell.getCellStyle().getDataFormatString())) {
					//value = df.format(cell.getNumericCellValue());
					value=NumberToTextConverter.toText(cell.getNumericCellValue());
				} else if ("m/d/yy".equals(cell.getCellStyle()
						.getDataFormatString())) {
					value = sdf.format(cell.getDateCellValue());
				} else if ("yyyy\\-mm\\-dd".equals(cell.getCellStyle()
						.getDataFormatString())) {
					value = sdf.format(cell.getDateCellValue());
				} else {
					//value = df2.format(cell.getNumericCellValue());
					value=NumberToTextConverter.toText(cell.getNumericCellValue());  
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				break;
			}
		}
		
		return value;
	}

	/**
	 * 获取标题样式
	 * @param workBook  
	 * @return HSSFCellStyle
	 */
	public static HSSFCellStyle getCellStyle(Workbook workBook){
		HSSFCellStyle cellStyle = (HSSFCellStyle) workBook.createCellStyle();

		// 水平居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 垂直居中
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 字体样式
		HSSFFont hssfFont = (HSSFFont) workBook.createFont();
		// 设置字体颜色
		hssfFont.setColor(HSSFColor.BLACK.index);
		// 字体大小
		hssfFont.setFontHeightInPoints((short) 14);
		hssfFont.setFontName("宋体");
		// 粗体
		//hssfFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(hssfFont);
		
		return cellStyle;
	}
	/**
	 * 获取正文样式
	 * @param workBook
	 * @return HSSFCellStyle
	 */
	public static HSSFCellStyle getDataCellStyle(Workbook workBook){
		
		HSSFCellStyle dataCellStyle = (HSSFCellStyle) workBook.createCellStyle();
		// 水平居中
		dataCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 垂直居中
		dataCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 字体样式
		HSSFFont dataHssfFont = (HSSFFont) workBook.createFont();
		dataHssfFont.setFontHeightInPoints((short) 10);
		dataHssfFont.setFontName("宋体");
		dataCellStyle.setFont(dataHssfFont);
		//设置自动换行
		dataCellStyle.setWrapText(true);
		return dataCellStyle;
	}
	/**
	 * 获取正文样式 设置颜色
	 * @param workBook
	 * @return HSSFCellStyle
	 */
	public static HSSFCellStyle getDataCellStyle1(Workbook workBook){
		
		HSSFCellStyle dataCellStyle = (HSSFCellStyle) workBook.createCellStyle();
		// 水平居中
		dataCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 垂直居中
		dataCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 字体样式
		HSSFFont dataHssfFont = (HSSFFont) workBook.createFont();
		dataHssfFont.setFontHeightInPoints((short) 12);
		dataHssfFont.setFontName("宋体");
		dataHssfFont.setColor(HSSFColor.RED.index);
		dataCellStyle.setFont(dataHssfFont);
		return dataCellStyle;
	}
	
	
	/**
	 *  
	 * 导入Excel表结束 导出Excel表开始
	 * 
	 * @param sheetName
	 *            工作簿名称
	 * @param clazz
	 *            数据源model类型
	 * @param objs
	 *            excel标题列以及对应model字段名
	 * @param map
	 *            标题列行数以及cell字体样式
	 */
	
	/*public static XSSFWorkbook createExcelFile(Class clazz, List objs,
			Map<Integer, List<ExcelBean>> map, String sheetName)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException,
			IntrospectionException, ParseException {
		// 创建新的Excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
		XSSFSheet sheet = workbook.createSheet(sheetName);
		// 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
		createFont(workbook); // 字体样式
		createTableHeader(sheet, map); // 创建标题（头）
		createTableRows(sheet, map, objs, clazz); // 创建内容
		return workbook;
	}*/

	/*private static XSSFCellStyle fontStyle;
	private static XSSFCellStyle fontStyle2;

	public static void createFont(XSSFWorkbook workbook) {
		// 表头
		fontStyle = workbook.createCellStyle();
		XSSFFont font1 = workbook.createFont();
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font1.setFontName("黑体");
		font1.setFontHeightInPoints((short) 14);// 设置字体大小
		fontStyle.setFont(font1);
		fontStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		fontStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		fontStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		fontStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		fontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		// 内容
		fontStyle2 = workbook.createCellStyle();
		XSSFFont font2 = workbook.createFont();
		font2.setFontName("宋体");
		font2.setFontHeightInPoints((short) 10);// 设置字体大小
		fontStyle2.setFont(font2);
		fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
	}
*/
	/**
	 * 根据ExcelMapping 生成列头（多行列头）
	 * 
	 * @param sheet
	 *            工作簿
	 * @param map
	 *            每行每个单元格对应的列头信息
	 */
	/*public static final void createTableHeader(XSSFSheet sheet,
			Map<Integer, List<ExcelBean>> map) {
		int startIndex = 0;// cell起始位置
		int endIndex = 0;// cell终止位置
		for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
			XSSFRow row = sheet.createRow(entry.getKey());
			List<ExcelBean> excels = entry.getValue();
			for (int x = 0; x < excels.size(); x++) {
				// 合并单元格
				if (excels.get(x).getCols() > 1) {
					if (x == 0) {
						endIndex += excels.get(x).getCols() - 1;
						CellRangeAddress range = new CellRangeAddress(0, 0,
								startIndex, endIndex);
						sheet.addMergedRegion(range);
						startIndex += excels.get(x).getCols();
					} else {
						endIndex += excels.get(x).getCols();
						CellRangeAddress range = new CellRangeAddress(0, 0,
								startIndex, endIndex);
						sheet.addMergedRegion(range);
						startIndex += excels.get(x).getCols();
					}
					XSSFCell cell = row.createCell(startIndex
							- excels.get(x).getCols());
					cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容
					if (excels.get(x).getCellStyle() != null) {
						cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式
					}
					cell.setCellStyle(fontStyle);
				} else {
					XSSFCell cell = row.createCell(x);
					cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容
					if (excels.get(x).getCellStyle() != null) {
						cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式
					}
					cell.setCellStyle(fontStyle);
				}
			}
		}
	}
*/
	/**
	 * 导出EXCEL，单行表头，无单元格合并
	 * 
	 * @param fileName
	 *          文件名
	 * @param List<Map<String,Object>> 
	 *          数据
	 * @param title 
	 * 			表头
	 * @param filed
	 * 			字段
	 *           
	 */
	public static void downloadExcel(HttpServletResponse response, String fileName, List<Map<String,Object>> list, String[] title, String[] filed){
		//实例化excel
  		Workbook workBook = new HSSFWorkbook();
  		Sheet sheet = workBook.createSheet(fileName);
  		fileName = fileName + ".xls";
  		/** 正文样式1 */
  		HSSFCellStyle dataCellStyle = getDataCellStyle(workBook);
  		//表头第一行
  		Row firstRow0 = sheet.createRow(0);
  		Row dataRow0 = sheet.createRow(0);
  		for(int i = 0; i < title.length; i++){
  			firstRow0.createCell(i).setCellValue(title[i]);
  			firstRow0.getCell(i).setCellStyle(dataCellStyle);
  		}
  		dataRow0.setHeight((short) (25 * 20));// 行高
  		//第二行起为数据行
  		String value = "";
  		for(int i = 0; i < list.size(); i++){
  			Map<String,Object> map = list.get(i);
  			Row dataRow1 = sheet.createRow(i+1);
  			dataRow1.setHeight((short) (25 * 20));// 行高
  			for(int k = 0; k < filed.length; k++){
  				if(map.get(filed[k]) == null){
  	  				value = "";
  	  			}else{
  	  				value = String.valueOf(map.get(filed[k]));
  	  			}
  	  			dataRow1.createCell(k).setCellValue(value);
	  			dataRow1.getCell(k).setCellStyle(dataCellStyle);
  			}
  		}
  		OutputStream out = null;
  		try {
  			// 获取文件的下载路径
  			response.setCharacterEncoding("utf-8");
  			response.setContentType("application/vnd.ms-excel");
  			// 设置浏览器以下载的方式处理该文件默认名
  			response.setHeader("Content-Disposition", "attachment;fileName="
  					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
  			out = response.getOutputStream();
  			workBook.write(out);
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				if (out != null)
  					out.close();
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
	}
	/**
	 * 导出EXCEL，单行表头
	 * 
	 * @param fileName
	 *          文件名
	 * @param List<？> 
	 *          数据
	 * @param title 
	 * 			表头
	 * @param flag 
	 * 			是否需要自增序号
	 * @param filed
	 * 			字段
	 *           
	 */
	public static void downloadExcel(HttpServletResponse response, String fileName, List<?> list, String[] title, String[] filed,boolean flag){
		//实例化excel
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet(fileName);
		/** 标题样式 */
		HSSFCellStyle cellStyle = ExcelUtil.getCellStyle(workBook);
		/** 正文样式1 */
		HSSFCellStyle dataCellStyle = ExcelUtil.getDataCellStyle(workBook);
		/*HSSFDataFormat format = (HSSFDataFormat) workBook.createDataFormat();
		dataCellStyle.setDataFormat(format.getFormat("@"));*/
		//设置表头 合并单元格  设置样式表抬头
		Row firstRow0 = sheet.createRow(0);
  		Row dataRow0 = sheet.createRow(0);
  		CellRangeAddress cra = null;
  		cra =new CellRangeAddress(0, 0, 0, title.length - 1); // 起始行, 终止行, 起始列, 终止列  
  		sheet.addMergedRegion(cra);
  	    firstRow0.createCell(0).setCellValue(fileName);
	    firstRow0.getCell(0).setCellStyle(cellStyle);
	    dataRow0.setHeight((short) (25 * 20));// 行高
	    //表头行
	    Row titleRow = sheet.createRow(1);
    	titleRow.setHeight((short) (25 * 20));// 行高
	    for(int i = 0; i < title.length; i++){
	    	titleRow.createCell(i).setCellValue(title[i]);
	    	titleRow.getCell(i).setCellStyle(dataCellStyle);
	    }
	    //数据行
	    String value = "";     //数据
	    int rowNumber = 1;    //自增序号
	    int sign = 0;         //数据列偏移数
	    for(int i = 0; i < list.size(); i++){
	    	Map<String,Object> map = Util.objectToMap(list.get(i));
  			Row dataRow = sheet.createRow(i+2);
  			dataRow.setHeight((short) (25 * 20));// 行高
  			/**
  			 * @param flag 是否需要自增序号
  			 * 	flag == true  数据列第一列为自增序号，其它数据从第二列开始（偏移量为sign）		
  			 */
  			if(flag){ //第一列为自增序号
  				dataRow.createCell(0);
  				dataRow.getCell(0).setCellStyle(dataCellStyle);
  				dataRow.getCell(0).setCellValue(rowNumber);
  				rowNumber++;
  				sign = 1;
  			}
  			for(int k = 0; k < filed.length; k++){
				if(String.valueOf(map.get(filed[k])) == null || String.valueOf(map.get(filed[k])).contains("null")){
					value = "";
				}else{
					/*if(map.get(filed[k]).getClass() == Double.class){
						BigDecimal bigDecimal = new BigDecimal((Double)map.get(filed[k]));
						value = bigDecimal.toString();
						DecimalFormat decimalFormat = new DecimalFormat("#.#");
						value = decimalFormat.format(map.get(filed[k]));
					}else{
						value = String.valueOf(map.get(filed[k]));
					}*/
					value = String.valueOf(map.get(filed[k]));
					if(value.startsWith(".")){
						value = "0" + value;
					}
				}
				dataRow.createCell(k + sign);
				dataRow.getCell(k + sign).setCellStyle(dataCellStyle);
				dataRow.getCell(k + sign).setCellValue(value);
			}
  			
	    }
	    OutputStream out = null;
	    String fileName2 = fileName + ".xls";
  		try {
  			// 获取文件的下载路径
  			response.setCharacterEncoding("utf-8");
  			response.setContentType("application/vnd.ms-excel");
  			// 设置浏览器以下载的方式处理该文件默认名
  			response.setHeader("Content-Disposition", "attachment;fileName="
  					+ new String(fileName2.getBytes("gb2312"), "ISO8859-1"));
  			out = response.getOutputStream();
  			workBook.write(out);
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				if (out != null)
  					out.close();
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
	}
	/**
	 * 导出EXCEL，两行表头(表头只支持单列多行合并，和单行多列合并)
	 * 
	 * @param fileName
	 *          文件名
	 * @param List<？> 
	 *          数据
	 * @param title1 
	 * 			表头1
	 * @param title2 
	 * 			表头2
	 * @param flag 
	 * 			是否需要自增序号
	 * @param filed
	 * 			字段
	 *           
	 */
	public static void downloadExcel(HttpServletResponse response, String fileName, List<?> list,
			String[] title1,String[] title2, String[] filed, boolean flag){
		//实例化excel
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet(fileName);
		/** 标题样式 */
		HSSFCellStyle cellStyle = ExcelUtil.getCellStyle(workBook);
		/** 正文样式1 */
		HSSFCellStyle dataCellStyle = ExcelUtil.getDataCellStyle(workBook);
		//设置表头 合并单元格  设置样式表抬头
		Row firstRow0 = sheet.createRow(0);
  		Row dataRow0 = sheet.createRow(0);
  		CellRangeAddress cra = null;
  		cra =new CellRangeAddress(0, 0, 0, title1.length - 1); // 起始行, 终止行, 起始列, 终止列  
  		sheet.addMergedRegion(cra);
  	    firstRow0.createCell(0).setCellValue(fileName);
	    firstRow0.getCell(0).setCellStyle(cellStyle);
	    dataRow0.setHeight((short) (25 * 20));// 行高
	    //表头行
	    String firstTitle = "";
	    String currentTitle = "";
	    int index = 0;
	    for(int i = 1; i < 3; i++){   //分别循环title1(i == 1)和title2(i == 2)
	    	Row titleRow = sheet.createRow(i);
	    	titleRow.setHeight((short) (25 * 20));// 行高
	    	for(int j = 0; j < title1.length; j++){
	    		if(i == 1){  //表头第一行  title1
	    			if(j == 0){
	    				firstTitle = title1[j];   //初始化数据
	    			}else{
	    				currentTitle = title1[j];
	    				/*当前数据currentTitle 与 初始数据firstTitle进行比较判断
	    				 * 两者值不相等  则判断当前数据列j 与 初始数据列index 是否相邻，即 j - index > 1?
	    				 * 如果不相邻，则当前数据列前一列 j - 1 需要与 初始列 index 进行 列合并
	    				 * 如果相邻， 则判断是否需要行合并
	    				 * */
	    				if(!currentTitle.equals(firstTitle)){
	    					if(j - index > 1){  //需要合并列
	    						cra =new CellRangeAddress(1, 1, index, j - 1); // 起始行, 终止行, 起始列, 终止列
	    						sheet.addMergedRegion(cra);
	    					}else{ //不需要合并列
	    						if(title1[index].equals(title2[index])){
	    	    		    		cra = new CellRangeAddress(1, 2, index, index); // 起始行, 终止行, 起始列, 终止列  
	    	    		    		sheet.addMergedRegion(cra);
	    	    		    	}
	    					}
	    					titleRow.createCell(index).setCellValue(title1[index]);
	    					titleRow.getCell(index).setCellStyle(dataCellStyle);
	    					index = j;
	    					firstTitle = title1[j];
	    					if(j == title1.length - 1){ //最后一行
	    						if(title1[j].equals(title2[j])){
	    	    		    		cra = new CellRangeAddress(1, 2, j, j); // 起始行, 终止行, 起始列, 终止列  
	    	    		    		sheet.addMergedRegion(cra);
	    	    		    	}
	    						titleRow.createCell(j).setCellValue(title1[j]);
	    						titleRow.getCell(j).setCellStyle(dataCellStyle);
	    					}
	    				}else{
	    					if(j == title1.length - 1){ //最后一行
	    						cra = new CellRangeAddress(1, 1, index, j); // 起始行, 终止行, 起始列, 终止列  
    	    		    		sheet.addMergedRegion(cra);
	    						titleRow.createCell(index).setCellValue(title1[index]);
	    						titleRow.getCell(index).setCellStyle(dataCellStyle);
	    					}
	    				}
	    			}
		    	}else{  //表头第二行
		    		if(!title1[j].equals(title2[j])){
		    			titleRow.createCell(j).setCellValue(title2[j]);
		    			titleRow.getCell(j).setCellStyle(dataCellStyle);
		    		}
		    	}
	    	}
	    }
	    //表头合并
	   /* String firstTitle = title1[0];
	    String currentTitle = "";
	    int index = 0;
	    for(int i = 0; i < title1.length; i++){
	    	if(i > 0){
	    		currentTitle = title1[i];
	    		if(!currentTitle.equals(firstTitle)){
	    			firstTitle = title1[i];
	    			if(i - index > 1){
	    				cra = new CellRangeAddress(1, 1, index, i - 1); // 起始行, 终止行, 起始列, 终止列 
	    				sheet.addMergedRegion(cra);
	    			}
	    			index = i;
	    		}else if(i == title1.length - 1){
	    			if(i - index > 1){
	    				cra = new CellRangeAddress(1, 1, index, i); // 起始行, 终止行, 起始列, 终止列 
	    				sheet.addMergedRegion(cra);
	    			}
	    		}
	    	}
	    	if(title1[i].equals(title2[i])){
	    		cra = new CellRangeAddress(1, 2, i, i); // 起始行, 终止行, 起始列, 终止列  
	    		sheet.addMergedRegion(cra);
	    	}
	    }*/
	    //数据行
	    String value = "";
	    int rowNumber = 1;
	    int sign = 0;
	    for(int i = 0; i < list.size(); i++){
	    	Map<String,Object> map = Util.objectToMap(list.get(i));
  			Row dataRow = sheet.createRow(i+3);
  			dataRow.setHeight((short) (25 * 20));// 行高
  			if(flag){ //第一列为自增序号
  				dataRow.createCell(0);
  				dataRow.getCell(0).setCellStyle(dataCellStyle);
  				dataRow.getCell(0).setCellValue(rowNumber);
  				rowNumber++;
  				sign = 1;
  			}
  			for(int k = 0; k < filed.length; k++){
				if(String.valueOf(map.get(filed[k])) == null || String.valueOf(map.get(filed[k])).contains("null")){
					value = "";
				}else{
					/*if(map.get(filed[k]).getClass() == Double.class){
						BigDecimal bigDecimal = new BigDecimal((Double)map.get(filed[k]));
						value = bigDecimal.toString();
						DecimalFormat decimalFormat = new DecimalFormat("#.#");
						value = decimalFormat.format(map.get(filed[k]));
					}else{
						value = String.valueOf(map.get(filed[k]));
					}*/
					value = String.valueOf(map.get(filed[k]));
					if(value.startsWith(".")){
						value = "0" + value;
					}
				}
				dataRow.createCell(k + sign);
				dataRow.getCell(k + sign).setCellStyle(dataCellStyle);
				dataRow.getCell(k + sign).setCellValue(value);
				
			}
  			
	    }
	    OutputStream out = null;
	    String fileName2 = fileName + ".xls";
  		try {
  			// 获取文件的下载路径
  			response.setCharacterEncoding("utf-8");
  			response.setContentType("application/vnd.ms-excel");
  			// 设置浏览器以下载的方式处理该文件默认名
  			response.setHeader("Content-Disposition", "attachment;fileName="
  					+ new String(fileName2.getBytes("gb2312"), "ISO8859-1"));
  			out = response.getOutputStream();
  			workBook.write(out);
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				if (out != null)
  					out.close();
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		} 
	}
	/**
	 * 导出EXCEL，三行表头(表头只支持单列多行合并，和单行多列合并)
	 * 
	 * @param fileName
	 *          文件名
	 * @param List<？> 
	 *          数据
	 * @param title1 
	 * 			表头1
	 * @param title2 
	 * 			表头2
	 * @param title3 
	 * 			表头3
	 * @param flag 
	 * 			是否需要自增序号
	 * @param filed
	 * 			字段
	 *           
	 */
	public static void downloadExcel(HttpServletResponse response, String fileName, List<?> list,
			String[] title1,String[] title2,String[] title3, String[] filed, boolean flag){
		//实例化excel
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet(fileName);
		/** 标题样式 */
		HSSFCellStyle cellStyle = ExcelUtil.getCellStyle(workBook);
		/** 正文样式1 */
		HSSFCellStyle dataCellStyle = ExcelUtil.getDataCellStyle(workBook);
		//设置表头 合并单元格  设置样式表抬头
		Row firstRow0 = sheet.createRow(0);
  		Row dataRow0 = sheet.createRow(0);
  		CellRangeAddress cra = null;
  		cra =new CellRangeAddress(0, 0, 0, title1.length - 1); // 起始行, 终止行, 起始列, 终止列  
  		sheet.addMergedRegion(cra);
  	    firstRow0.createCell(0).setCellValue(fileName);
	    firstRow0.getCell(0).setCellStyle(cellStyle);
	    dataRow0.setHeight((short) (25 * 20));// 行高
	    //表头行
	    String firstTitle = "";
	    String currentTitle = "";
	    int index = 0;
	    /*表头合并逻辑
	     * 第一行， 首先判断是否有单行多列合并情况，如果没有，则判断是否存在单列多行合并情况
	     * 第二行， 首先判断是否有单行多列合并情况，如果没有，则判断二、三行数据是否存在合并情况(一二行不需要合并)
	     * 第三行， 不存在单独合并情况
	     * */
	    for(int i = 1; i < 4; i++){
	    	Row titleRow = sheet.createRow(i);
	    	titleRow.setHeight((short) (25 * 20));// 行高
	    	for(int j = 0; j < title1.length; j++){
	    		if(i == 1){ //表头第一行
	    			if(j == 0){  //初始化数据
	    				firstTitle = title1[j];
	    			}else{
	    				currentTitle = title1[j];
	    				if(!currentTitle.equals(firstTitle)){  //值不同
	    					if(j - index > 1){  //需要合并列
	    						cra =new CellRangeAddress(i, i, index, j - 1); // 起始行, 终止行, 起始列, 终止列
	    						sheet.addMergedRegion(cra);
	    					}else{  //判断是否需要合并行
	    						if(title1[index].equals(title2[index])){  //一行和二行相同
	    							if(title2[index].equals(title3[index])){  //二行和三行相同
	    								cra =new CellRangeAddress(i, 3, index, index); // 起始行, 终止行, 起始列, 终止列
	    	    						sheet.addMergedRegion(cra);
	    							}else{ //二行和三行不相同
	    								cra =new CellRangeAddress(i, 2, index, index); // 起始行, 终止行, 起始列, 终止列
	    	    						sheet.addMergedRegion(cra);
	    							}
	    						}
	    					}
	    					//数据填充，设置格式
	    					titleRow.createCell(index).setCellValue(title1[index]);
        					titleRow.getCell(index).setCellStyle(dataCellStyle);
        					index = j;
        					firstTitle = title1[j];
        					//判断是否最后一列数据
        					if(j == title1.length - 1){
        						if(title1[j].equals(title2[j])){
        							if(title2[index].equals(title3[index])){
        								cra = new CellRangeAddress(i, 3, j, j); // 起始行, 终止行, 起始列, 终止列  
            	    		    		sheet.addMergedRegion(cra);
        							}else{
        								cra = new CellRangeAddress(i, 2, j, j); // 起始行, 终止行, 起始列, 终止列  
            	    		    		sheet.addMergedRegion(cra);
        							}
        	    		    	}
        						titleRow.createCell(index).setCellValue(title1[index]);
        						titleRow.getCell(index).setCellStyle(dataCellStyle);
        					}
	    				}else{  //值相同
	    					if(j == title1.length - 1){  //最后一列
	    						cra =new CellRangeAddress(i, i, index, j); // 起始行, 终止行, 起始列, 终止列
	    						sheet.addMergedRegion(cra);
	    						titleRow.createCell(index).setCellValue(title1[index]);
        						titleRow.getCell(index).setCellStyle(dataCellStyle);
	    					}
	    				}
	    			}
	    		}else if(i == 2){ //表头第二行
	    			if(j == 0){  //初始化数据
	    				firstTitle = title2[j];
	    			    currentTitle = "";
	    			    index = 0;
	    			}else{
	    				currentTitle = title2[j];
	    				if(!currentTitle.equals(firstTitle)){
	    					if(j - index > 1){  //需要合并列
	    						cra =new CellRangeAddress(i, i, index, j - 1); // 起始行, 终止行, 起始列, 终止列
	    						sheet.addMergedRegion(cra);
	    					}else{  //判断呢是否需要合并行
	    						if(title2[index].equals(title3[index])){//二行和三行相同
	    							if(!title1[index].equals(title2[index])){ //一行和二行不相同
	    								cra =new CellRangeAddress(i, 3, index, index); // 起始行, 终止行, 起始列, 终止列
	    	    						sheet.addMergedRegion(cra);
	    							}
	    						}
	    					}
	    					//数据填充，设置格式
	    					titleRow.createCell(index).setCellValue(title2[index]);
        					titleRow.getCell(index).setCellStyle(dataCellStyle);
        					index = j;
        					firstTitle = title2[j];
        					//判断是否最后一列数据
        					if(j == title1.length - 1){
        						if(title2[index].equals(title3[index])){//二行和三行相同
	    							if(!title1[index].equals(title2[index])){ //一行和二行不相同
	    								cra =new CellRangeAddress(i, 3, index, index); // 起始行, 终止行, 起始列, 终止列
	    	    						sheet.addMergedRegion(cra);
	    							}
	    						}
        						titleRow.createCell(index).setCellValue(title2[index]);
        						titleRow.getCell(index).setCellStyle(dataCellStyle);
        					}
	    				}else{
	    					if(j == title1.length - 1){
	    						cra =new CellRangeAddress(i, i, index, j); // 起始行, 终止行, 起始列, 终止列
	    						sheet.addMergedRegion(cra);
	    						titleRow.createCell(index).setCellValue(title2[index]);
        						titleRow.getCell(index).setCellStyle(dataCellStyle);
	    					}
	    				}
	    			}
	    		}else{ //表头第三行
	    			if(!title2[j].equals(title3[j])){
	    				titleRow.createCell(j).setCellValue(title3[j]);
						titleRow.getCell(j).setCellStyle(dataCellStyle);
	    			}
	    		}
	    	}
	    }
	    //数据行  第五行开始
	    String value = "";
	    int rowNumber = 1;
	    int sign = 0;
	    for(int i = 0; i < list.size(); i++){
	    	Map<String,Object> map = Util.objectToMap(list.get(i));
  			Row dataRow = sheet.createRow(i+4);
  			dataRow.setHeight((short) (25 * 20));// 行高
  			if(flag){ //第一列为自增序号
  				dataRow.createCell(0);
  				dataRow.getCell(0).setCellStyle(dataCellStyle);
  				dataRow.getCell(0).setCellValue(rowNumber);
  				rowNumber++;
  				sign = 1;
  			}
  			for(int k = 0; k < filed.length; k++){
				if(String.valueOf(map.get(filed[k])) == null || String.valueOf(map.get(filed[k])).contains("null")){
					value = "";
				}else{
					value = String.valueOf(map.get(filed[k]));
					if(value.startsWith(".")){
						value = "0" + value;
					}
				}
				dataRow.createCell(k + sign);
				dataRow.getCell(k + sign).setCellStyle(dataCellStyle);
				dataRow.getCell(k + sign).setCellValue(value);
				
			}
  			
	    }
	    OutputStream out = null;
	    String fileName2 = fileName + ".xls";
  		try {
  			// 获取文件的下载路径
  			response.setCharacterEncoding("utf-8");
  			response.setContentType("application/vnd.ms-excel");
  			// 设置浏览器以下载的方式处理该文件默认名
  			response.setHeader("Content-Disposition", "attachment;fileName="
  					+ new String(fileName2.getBytes("gb2312"), "ISO8859-1"));
  			out = response.getOutputStream();
  			workBook.write(out);
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				if (out != null)
  					out.close();
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		} 
	}
}
