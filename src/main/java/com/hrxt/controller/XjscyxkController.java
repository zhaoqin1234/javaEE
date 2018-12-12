package com.hrxt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Csjh;
import com.hrxt.pojo.MonthPZ;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.Xjscyxk;
import com.hrxt.pojo.XjscyxkReport;
import com.hrxt.pojo.XjscyxkType;
import com.hrxt.service.XjscyxkService;
import com.hrxt.utils.ExcelUtil;
import com.hrxt.utils.JSONutils;
/**
 * 心井生产运行科 controller
 * @author ZhaoQin
 *
 */
@Controller
@RequestMapping("/xjscyxk")
public class XjscyxkController {

	@Autowired
	private XjscyxkService xjscyxkService;
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insetXjscyxk(Xjscyxk xjscyxk){
		Map<String,Object> map = this.xjscyxkService.insertOrUpdate(xjscyxk);
		return JSON.toJSONString(map);
	}
	
	
	@RequestMapping("/insertList")
	@ResponseBody
	public String insetXjscyxkList( Xjscyxk xjscyxk){	
		System.out.println(JSONutils.Object2String(xjscyxk));
		List<Xjscyxk> list11 = new ArrayList<Xjscyxk>();
		list11.add(xjscyxk);
		List<String> list = xjscyxkService.batchInsertOrUpdate(list11);
		return JSONutils.Object2String(list);
	}
	
	
	@RequestMapping("/delOneXjscyxk")
	@ResponseBody
	public String delOneXjscyxk(String seq){	
		Map<String,Object> map = xjscyxkService.delOneYPZBB(seq);
		return JSON.toJSONString(map);
	}

	
	@RequestMapping("/delXjscyxkList")
	@ResponseBody
	public String delXjscyxkList(String seqlist){	
		Map<String,Object> map = xjscyxkService.delListYPZBB(seqlist);
		return JSON.toJSONString(map);
	}
	
	
	@RequestMapping("/getXjscyxkList")
	@ResponseBody
	public String getXjscyxkList(Xjscyxk xjscyxk,Page<List<Xjscyxk>> page){	
		return JSONutils.Object2String(xjscyxkService.getXjscyxkList(xjscyxk, page));
	}
	
	
	/**
	 *   /xjscyxk/getXjscyxkReportData?stime=2018-11
	 *   获取新井生产运行科数据   
	 * @param stime    xxxx-xx
	 * @param site_id   工区ids  逗号分隔
	 * @return
	 */
	@RequestMapping("/getXjscyxkReportData")
	@ResponseBody
	public String getXjscyxkReportData(String stime,String site_id){	
		return JSONutils.Object2String(xjscyxkService.getXjscyxkReport(stime, site_id));
	}
	
	
	
	/**
	 * 		/xjscyxk/getXjscyxkTypeData
	 * 		获取新井生产运行科  所有的油井 水井  水井转注  等类型
	 * @param type_code
	 * @param type_name
	 * @param new_old_flag
	 * @param oil_water_flag
	 * @return
	 */
	@RequestMapping("/getXjscyxkTypeData")
	@ResponseBody
	public String getXjscyxkTypeData(String type_code,String type_name,String new_old_flag,String oil_water_flag){	
		List<XjscyxkType> datalist = xjscyxkService.getXjscyxkType(type_code, type_name, new_old_flag, oil_water_flag);
		return JSONutils.Object2String(datalist);
	}
	
	/**    /xjscyxk
	 * 新增 新井生产运行科  井类型
	 * @param xjscyxkType
	 * @return
	 */
	@RequestMapping("/insertXjscyxkType")
	@ResponseBody
	public String insertXjscyxkType(XjscyxkType xjscyxkType){	
		Map<String,Object> retmap = xjscyxkService.insertXjscyxkType(xjscyxkType);
		return JSONutils.Object2String(retmap);
	}
	
	/**    /xjscyxk
	 * 	更新新井运行科  井类型
	 * @param xjscyxkType
	 * @return
	 */
	@RequestMapping("/updateXjscyxkType")
	@ResponseBody
	public String updateXjscyxkType(XjscyxkType xjscyxkType){	
		Map<String,Object> retmap = xjscyxkService.updateXjscyxkType(xjscyxkType);
		return JSONutils.Object2String(retmap);
	}
	
	//
	/**  /xjscyxk
	 *   删除新井生产运行科  井累心
	 * @param xjscyxkType
	 * @return
	 */
	@RequestMapping("/deleteXjscyxkType")
	@ResponseBody
	public String deleteXjscyxkType(XjscyxkType xjscyxkType){	
		Map<String,Object> retmap = xjscyxkService.deleteXjscyxkType(xjscyxkType);
		return JSONutils.Object2String(retmap);
	}
	
	
	
	
	
	
	
	/**
	 * /xjscyxk/downXjscyxkReport
	 * 
	 * @param stime
	 * @param site_id
	 * @return
	 */
	@RequestMapping("/downXjscyxkReport")
	@ResponseBody
	public String downXjscyxkReport(String stime,String site_id,String fileName){	
		
		xjscyxkService.getXjscyxkReport(stime, site_id);
		
		return null;
	}
	
	
	
	
	
	/**
	 * 批量上传数据
	 * @param fileMult
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/excelUpdateXjscyxk")
	public String updateXjscyxkList(MultipartFile fileMult,String fileName){	
		List<Map<String, Object>> list = null;
		if(fileMult==null) {
			//System.out.println("没有数据流");
			return "没有数据流";
		}
		try {
			String name =  fileMult.getOriginalFilename();
			InputStream is = fileMult.getInputStream();
			//System.out.println(name+is);
			String [] fileds = {"site_name","yc_name","well_name","dzpz","khpz","stime","mark"};
			if(is != null) {
									//sheet0  第一行   iostream 传入文件名  读取字段名字
				list = ExcelUtil.getDataListByexcel(0, 1, is, name, fileds);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Xjscyxk xjscyxk = new Xjscyxk();
			xjscyxk.setSite_name((String)map.get("site_name"));
			xjscyxk.setYc_name((String)map.get("yc_name"));
			//xjscyxk.setDzpz((Integer.parseInt((String) map.get("dzpz"))));
			xjscyxk.setWell_name((String)map.get("well_name"));
			xjscyxk.setStime((String)map.get("stime"));
			xjscyxk.setMark((String)map.get("mark"));
			System.out.println(JSONutils.Object2String(map));
			this.xjscyxkService.insertOrUpdate(xjscyxk);
		}
		
		return "";
	}
	
	
	
	/** 
	 *  /xjscyxk/dwnloadXjscyxkTmp
	 * 下载导入模板      心井运行
	 */
	@RequestMapping("/dwnloadXjscyxkTmp")
	public void dwnloadTmpMB(Xjscyxk xjscyxk,Page<List<Xjscyxk>> page,HttpServletResponse response,String fileName){
		
		//获取配注的计划信息
		//List<MonthPZ> monthPZlist = this.monthPZService.getMonthPZMB(monthPZ);
		Map<String,Object>  map = this.xjscyxkService.getXjscyxkList(xjscyxk, page);
		List<Xjscyxk> datalist = (List<Xjscyxk>)map.get("data");
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        if(fileName ==  null) {
        	fileName = "月配注模板导出";//设置要导出的文件的名字
        }
        
        //新增数据行，并且设置单元格数据
        int rowNum = 1;

        String[] headers = { "工区", "断块", "井名", "井别","预计日产油","目前工况","备注"};
        
        sheet.setColumnWidth(0, 35*150);
        sheet.setColumnWidth(1, 35*150);
        sheet.setColumnWidth(2, 35*150);
        sheet.setColumnWidth(3, 35*150);
        sheet.setColumnWidth(4, 35*150);
        sheet.setColumnWidth(5, 35*150);
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            
        }

        //在表中存放查询到的数据放入对应的列
        for (Xjscyxk welltmp : datalist) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(welltmp.getSite_name());
            row1.createCell(1).setCellValue(welltmp.getYc_name());
            row1.createCell(2).setCellValue(welltmp.getWell_name());

            	//井别设置
            if(welltmp.getStime() != null) {
            	if(welltmp.getWell_type().equals("1") ) {
            		 row1.createCell(3).setCellValue("油井");
            	}else if (welltmp.getWell_type().equals("0") ) {
            		row1.createCell(3).setCellValue("水井");
				}
            }
            //预计日产油
            if(welltmp.getProd_plan_day() != null) {
            	row1.createCell(4).setCellValue(welltmp.getProd_plan_day());
            }
            //目前工况
            if(welltmp.getCondition() != null) {
            	row1.createCell(5).setCellValue(welltmp.getCondition());
            }
            //备注信息
            if(welltmp.getMark() != null) {
            	row1.createCell(6).setCellValue(welltmp.getMark());
            }
            
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        String newFileName = "";
		try {
			newFileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        response.setHeader("Content-disposition", "attachment;filename=" + newFileName+ ".xls");
        try {
			response.flushBuffer();
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	
	
	/**
	 * 		/xjscyxk/downloadNewWellProduction?stime=2018-11
	 *   新井生产运行科  报表 导出
	 * @param stime   xxxx-xx
	 * @param site_id  id逗号分隔
	 * @param fileName
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("downloadNewWellProduction")
	@ResponseBody
	public String downloadNueWellProduction(String stime,String site_id,String fileName,HttpServletResponse response) throws IOException{
		
		List<XjscyxkReport> datalist = xjscyxkService.getXjscyxkReport(stime, site_id);
		
		// 开始要统计  井类型要 合并的数量    工区要合并的数量  
		Map<String, Map<String, Object>> typtMap = new LinkedHashMap<String,Map<String,Object>>();
		Map<String, Map<String, Object>> sitetMap = new LinkedHashMap<String,Map<String,Object>>();
		for (int i = 0; i < datalist.size(); i++) {
			XjscyxkReport tmp = datalist.get(i);
			if(!typtMap.containsKey(tmp.getType_name()==null?"":tmp.getType_name())) {
				Map<String, Object> tmpmap = new HashMap<String, Object>();
				tmpmap.put("key", tmp.getType_name());
				tmpmap.put("start", i);
				tmpmap.put("end", i);
				typtMap.put(tmp.getType_name(), tmpmap);
			}else {
				Map<String, Object> tmpmap = typtMap.get(tmp.getType_name());
				tmpmap.put("end", i);
			}
			if(!sitetMap.containsKey(tmp.getType_name()+tmp.getSite_id()) ) {
				Map<String, Object> tmpmap2 = new HashMap<String, Object>();
				tmpmap2.put("key", tmp.getType_name());
				tmpmap2.put("start", i);
				tmpmap2.put("end", i);
				if(tmp.getSite_id() != null) {
					sitetMap.put(tmp.getType_name()+tmp.getSite_id(),  tmpmap2 );
				}
				
			}else {
				Map<String, Object> tmpmap2 = sitetMap.get(tmp.getType_name()+tmp.getSite_id());
				tmpmap2.put("end", i);
			}
		}
		
		
		HSSFWorkbook wb = null;
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null) {
			wb = new HSSFWorkbook();

		}
		
		String sheetName=fileName+"新井生产运行科";
		String titleName="新井投产/投注、老井恢复、井别调整建议表";
		if(stime != null) {
			String [] timeArray = stime.split("-");
			titleName = timeArray[0]+"年"+timeArray[1]+titleName;
		}
		
		//表头样式 1 开始
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontName("Times New Roman");
		titleFont.setFontHeightInPoints((short) 18);// 字号
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		
		// 第四步，创建单元格，并设置值表头 设置表头居中  
		HSSFCellStyle style = wb.createCellStyle(); // 表格内容样式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setBorderBottom(HSSFCellStyle.BORDER_NONE);// 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_NONE);// 左边框
		style.setBorderRight(HSSFCellStyle.BORDER_NONE);// 右边框
		style.setBorderTop(HSSFCellStyle.BORDER_NONE);// 上边框
		style.setWrapText(true); // 设置换行
		style.setFont(titleFont);	
		
		//表头样式2 开始
		HSSFFont titleFont2 = wb.createFont();
		titleFont2.setFontName("宋体");
		titleFont2.setFontHeightInPoints((short) 11);// 字号
		titleFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		HSSFCellStyle style2 = wb.createCellStyle(); // 表格内容样式
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style2.setWrapText(true); // 设置换行
		style2.setFont(titleFont2);
		
		HSSFSheet sheet2 = wb.createSheet(sheetName);
		String[] sheet2Title2 = { "井别","工区","序号","井号","预计日产油（t）/日配注（m3）","目前工况","备注"};
		
		sheet2.setColumnWidth((short) 0, 3500);
		sheet2.setColumnWidth((short) 1, 3500);
		sheet2.setColumnWidth((short) 2, 1500);
		sheet2.setColumnWidth((short) 3, 3500);
		sheet2.setColumnWidth((short) 4, 3500);
		sheet2.setColumnWidth((short) 5, 6500);
		sheet2.setColumnWidth((short) 6, 3500);
		
		HSSFRow sheet2Row0 = sheet2.createRow(0);
		HSSFRow sheet2Row1 = sheet2.createRow(1);
		sheet2Row0.setHeight((short)900);
		sheet2Row1.setHeight((short)800);
		HSSFCell sheet2Row0createCell0 = sheet2Row0.createCell(0);
		sheet2Row0createCell0.setCellStyle(style);
		sheet2Row0createCell0.setCellValue(titleName);
		sheet2.addMergedRegion(new CellRangeAddress(0,0, 0, 6));
		
		HSSFCell cell = null;
		// 创建标题
		for (int i = 0; i < sheet2Title2.length; i++) {
					cell = sheet2Row1.createCell(i);
					cell.setCellValue(sheet2Title2[i]);
					cell.setCellStyle(style2);
				}
		
		int rowNumber = 2;
		//开始装配数据
		for (Iterator iterator = datalist.iterator(); iterator.hasNext();) {
			XjscyxkReport Report = (XjscyxkReport) iterator.next();
			
			HSSFRow sheet2RowTmp = sheet2.createRow(rowNumber);
			HSSFCell sheet2Row00 = sheet2RowTmp.createCell(0);
			HSSFCell sheet2Row01 = sheet2RowTmp.createCell(1);
			HSSFCell sheet2Row02 = sheet2RowTmp.createCell(2);
			HSSFCell sheet2Row03 = sheet2RowTmp.createCell(3);
			HSSFCell sheet2Row04 = sheet2RowTmp.createCell(4);
			HSSFCell sheet2Row05 = sheet2RowTmp.createCell(5);
			HSSFCell sheet2Row06 = sheet2RowTmp.createCell(6);
			sheet2Row00.setCellValue(Report.getType_name()==null?"":Report.getType_name()); 
			sheet2Row01.setCellValue(Report.getSite_name()==null?"":Report.getSite_name());
			sheet2Row02.setCellValue(Report.getIndex_num()==null?"":Report.getIndex_num()); //序号
			sheet2Row03.setCellValue(Report.getWell_name()==null?"":Report.getWell_name());
			sheet2Row04.setCellValue(Report.getProd_plan_day()==null?(double)0:Report.getProd_plan_day());
			sheet2Row05.setCellValue(Report.getCondition()==null?"":Report.getCondition());
			sheet2Row06.setCellValue(Report.getMark()==null?"":Report.getMark());
			
			sheet2Row00.setCellStyle(style2);
			sheet2Row01.setCellStyle(style2);
			sheet2Row02.setCellStyle(style2);
			sheet2Row03.setCellStyle(style2);
			sheet2Row04.setCellStyle(style2);
			sheet2Row05.setCellStyle(style2);
			sheet2Row06.setCellStyle(style2);
			
			// 合并单元格  小计
			if(Report != null && Report.getFlag() != null ) {
				String flag = Report.getFlag() ;
				if( flag.equals("1")) {
					sheet2.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 1, 3));
				}
			}
			// 合并单元格  合计的 
			if(Report != null && Report.getFlag() != null ) {
				String flag = Report.getFlag() ;
				if( flag.equals("2")) {
					HSSFCell sheet2Row000 = sheet2RowTmp.createCell(0);
					sheet2Row000.setCellValue(Report.getSite_name()+Report.getWell_name());
					sheet2Row000.setCellStyle(style2);
					sheet2.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, 3));
				}
			}
			
			rowNumber++; //  行下移
		}

		int startNumber = 2;
		for (String key: typtMap.keySet()) { // 井类型 合并 
			Map<String,Object> map = typtMap.get(key);
			if(key != null && !key.equals("")) {
				int start = (int) map.get("start");
				int end = (int) map.get("end");
				sheet2.addMergedRegion(new CellRangeAddress(startNumber+start, startNumber+end, 0, 0));
			}
		}
		for (String key: sitetMap.keySet()) { //工区合并
			//System.out.println(key);
			Map<String,Object> map = sitetMap.get(key);
			if(key != null &&  !key.equals("") && !key.equals("nullnull")) {
				int start = (int) map.get("start");
				int end = (int) map.get("end");
				sheet2.addMergedRegion(new CellRangeAddress(startNumber+start, startNumber+end, 1, 1));
			}
		}
		//System.out.println(JSON.toJSONString(sitetMap));
		
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition","attachment; filename=xjscyxbb.xls");
		response.setContentType("application/msexcel");
		wb.write(output);
		output.flush();
		output.close();
		return null;
	}
	
}
