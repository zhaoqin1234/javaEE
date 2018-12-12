package com.hrxt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Csjh;
import com.hrxt.pojo.OilJCJH;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.WaterJCJH;
import com.hrxt.service.OilJCJHService;
import com.hrxt.service.WaterJCJHService;
import com.hrxt.utils.ExcelUtil;
import com.hrxt.utils.JSONutils;
/**
 * 【水井检测】计划
 * 	 controller
 * @author ZhaoQin
 *
 */
@Controller
@RequestMapping("/waterjcjh")
public class WaterJCJHController {

	@Autowired
	private WaterJCJHService waterJCJHService;
	@Autowired
	private OilJCJHService  oilJCJHService ;
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insetOilJCJH(WaterJCJH waterJCJH){	
		Map<String,Object> map = this.waterJCJHService.insertOrUpdate(waterJCJH);
		return JSON.toJSONString(map);
		
	}
	
	
	@RequestMapping("/insertList")
	@ResponseBody
	public String insetOilJCJHList( WaterJCJH oilJCJH){	
		List<WaterJCJH> list11 = new ArrayList<WaterJCJH>();
		list11.add(oilJCJH);
		List<String> list = waterJCJHService.batchInsertOrUpdate(list11);
		return JSONutils.Object2String(list);
	}
	
	
	@RequestMapping("/delOneWaterJCJH")
	@ResponseBody
	public String delOneOilJCJH(String seq){	
		Map<String,Object> map = this.waterJCJHService.delOneWaterJCJH(seq);
		return JSON.toJSONString(map);
	}

	/**
	 * 不适用
	 * @param seqlist
	 * @return
	 */
	@RequestMapping("/delWaterJCJHList")
	@ResponseBody
	public String delOilJCJHList(String seqlist){	
		Map<String,Object> map = this.waterJCJHService.delListWaterJCJH(seqlist);
		return JSON.toJSONString(map);
	}
	
	
	@RequestMapping("/getWaterJCJHList")
	@ResponseBody
	public String getOilJCJHList(WaterJCJH waterJCJH,Page<List<WaterJCJH>> page){	
		return JSONutils.Object2String(waterJCJHService.getWaterJCJHList(waterJCJH, page));
	}
	
	
	/**
	 * 记录审核  方法
	 * @param seqlist
	 * @return
	 */
	@RequestMapping("/auditWaterJCJH")
	@ResponseBody
	public String auditOilJCJH(String seqlist){	
		return waterJCJHService.auditOneWaterJCJH(seqlist);
	}
	
	@RequestMapping("/unAuditWaterJCJH")
	@ResponseBody
	public String unAuditOilJCJH(String seqlist){	
		return waterJCJHService.unAuditOneWaterJCJH(seqlist);
	}
	/**
	 * 【提交】  方法
	 * @param seqlist
	 * @return
	 */
	@RequestMapping("/submitWaterJCJH")
	@ResponseBody
	public String submitMonthPZ(String seqlist){	
		return waterJCJHService.submitWaterJCJH(seqlist);
	}
	@RequestMapping("/unSubmitOilJCJH")
	@ResponseBody
	public String unSubmitOilJCJH(String seqlist){	
		return waterJCJHService.unSubmitWaterJCJH(seqlist);
	}
	
	
	
	
	/**
	 * 批量上传数据
	 * @param fileMult
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/excelUpdateWaterJCJH")
	@ResponseBody
	public String updateWaterJCJHList(MultipartFile fileMult,String fileName){	
		List<Map<String, Object>> list = null;
		if(fileMult==null) {
			//System.out.println("没有数据流");
			return "没有数据流";
		}
		try {
			String name =  fileMult.getOriginalFilename();
			InputStream is = fileMult.getInputStream();
			//System.out.println(name+is);
			String [] fileds = {"site_name","yc_name","well_name","cs_type_name",
					"stime","well_section","thickness","plies_num","cs_purpose",
					"cs_claim","cs_water_injection","last_success_date","recently_fail_date",
					"recently_revise_mesg","level_demand","tc_date","fk_cd_date",
					"wcd_mesg","success_date","fail_mesg"};			if(is != null) {
									//sheet0  第一行   iostream 传入文件名  读取字段名字
				list = ExcelUtil.getDataListByexcel(0, 1, is, name, fileds);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			WaterJCJH waterJCJH = new WaterJCJH();
			waterJCJH.setSite_name((String)map.get("site_name"));
			waterJCJH.setYc_name((String)map.get("yc_name"));   
			waterJCJH.setWell_name((String)map.get("well_name"));
			waterJCJH.setCs_type_name((String)map.get("cs_type_name"));
			waterJCJH.setStime(map.get("stime")!=null&&!"".equals(map.get("stime"))?((String)map.get("stime")).substring(0, 7):"");
			waterJCJH.setWell_section((String)map.get("well_section"));
			waterJCJH.setThickness(map.get("thickness")!=null&&!"".equals(map.get("thickness"))?Double.parseDouble((String)map.get("thickness")):0);
			waterJCJH.setCs_water_injection(map.get("cs_water_injection")!=null&&!"".equals(map.get("cs_water_injection"))?Double.parseDouble((String)map.get("cs_water_injection")):0);
			waterJCJH.setPlies_num(map.get("plies_num")!=null&&!"".equals(map.get("plies_num"))?Integer.parseInt((String)map.get("plies_num")):0);
			waterJCJH.setCs_purpose((String)map.get("cs_purpose"));
			waterJCJH.setCs_claim((String)map.get("cs_claim"));
			waterJCJH.setLast_success_date((String)map.get("last_success_date"));
			waterJCJH.setRecently_fail_date((String)map.get("recently_fail_date"));
			waterJCJH.setRecently_revise_mesg((String)map.get("recently_revise_mesg"));
			waterJCJH.setLevel_demand(map.get("level_demand")!=null&&!"".equals(map.get("level_demand"))?Integer.parseInt((String)map.get("level_demand")):0);
			
			DateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			
				String date=fmt.format(new Date());
			
			waterJCJH.setTc_date((String)map.get("tc_date")!=null&&(String)map.get("tc_date")!=""?(String)map.get("tc_date"):date);
			waterJCJH.setFk_cd_date((String)map.get("fk_cd_date"));
			waterJCJH.setWcd_mesg((String)map.get("wcd_mesg"));
			waterJCJH.setSuccess_date((String)map.get("success_date"));
			waterJCJH.setFail_mesg((String)map.get("fail_mesg"));
			this.waterJCJHService.insertOrUpdate(waterJCJH);
		}
		
		return "";
	}
	
	
	
	/**  
	 * 下载导入模板        措施计划, 
	 */
	@RequestMapping("/dwnloadWaterJCJHTmp")
	@ResponseBody
	public void dwnloadTmpMB(OilJCJH oilJCJH,HttpServletResponse response,String fileName){
		
		//获取措施计划数据
		List<OilJCJH> datalist = this.oilJCJHService.getOilJCJHExcelMoBan(oilJCJH);
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        if(fileName ==  null) {
        	fileName = "措施计划";//设置要导出的文件的名字
        }
        
        //新增数据行，并且设置单元格数据
        int rowNum = 1;

        String[] headers = { "工区", "油藏", "井号", "测试类型","措施月份(yyyy-mm)","生产井段（m）","厚度（m）","层数（t）","测试目的","测试要求","测试要求日注水量",
                "上次测成时间","(最近一次)未测成时间","(最近一次)整改情况","需求程度","提出日期(默认当前)","出单日期","未出单原因","测成日期","未测成原因"};        
        sheet.setColumnWidth(0, 35*150);
        sheet.setColumnWidth(1, 35*150);
        sheet.setColumnWidth(2, 35*150);
        sheet.setColumnWidth(3, 35*150);
        sheet.setColumnWidth(4, 35*150);
        sheet.setColumnWidth(5, 35*150);
        sheet.setColumnWidth(6, 35*150);
        sheet.setColumnWidth(7, 35*150);
        sheet.setColumnWidth(8, 35*150);
        sheet.setColumnWidth(9, 35*150);
        sheet.setColumnWidth(10, 35*150);
        sheet.setColumnWidth(11, 35*150);
        sheet.setColumnWidth(12, 35*150);
        sheet.setColumnWidth(13, 35*150);
        sheet.setColumnWidth(14, 35*150);
        sheet.setColumnWidth(15, 35*150);
        sheet.setColumnWidth(16, 35*150);
        sheet.setColumnWidth(17, 35*150);
        sheet.setColumnWidth(18, 35*150);
        sheet.setColumnWidth(19, 35*150);
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            
        }

        //在表中存放查询到的数据放入对应的列
        for (OilJCJH csjhTmp : datalist) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(csjhTmp.getSite_name());
            row1.createCell(1).setCellValue(csjhTmp.getYc_name());
            row1.createCell(2).setCellValue(csjhTmp.getWell_name());
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
	 * 导出数据
	 * @param oilJCJH
	 * @param page
	 * @param response
	 * @param fileName
	 */
	@RequestMapping("/dwnloadQueryData")
	public void dwnloadQueryData(WaterJCJH waterJCJH,Page<List<WaterJCJH>> page,HttpServletResponse response,String fileName){
		//获取配注的计划信息
		Map<String, Object> map = this.waterJCJHService.getWaterJCJHList(waterJCJH, page);
		List<WaterJCJH> datalist = (List<WaterJCJH>)map.get("data"); 
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        if(fileName ==  null) {
        	fileName = "油井监测计划数据导出" ;//设置要导出的文件的名字
        }
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "工区", "油藏", "井名", "测试内容","测试类型","措施计划月份","生产井段(m)","厚度(m)","层数(t)","测试目的","测试要求","测试要求日注水量","上次测成时间",
        		"(最近一次)未测成时间","(最近一次整改情况","需求程度","提出日期","出单日期","未出单原因","测成日期","未测成原因","提交状态","审核状态"};
        //设置列宽
        sheet.setColumnWidth(0, 35*150);
        sheet.setColumnWidth(1, 35*150);
        sheet.setColumnWidth(2, 35*150);
        sheet.setColumnWidth(3, 35*150);
        sheet.setColumnWidth(4, 35*150);
        sheet.setColumnWidth(5, 35*150);
        sheet.setColumnWidth(6, 35*150);
        sheet.setColumnWidth(7, 35*150);
        sheet.setColumnWidth(8, 35*150);
        sheet.setColumnWidth(9, 35*150);
        sheet.setColumnWidth(10, 35*150);
        sheet.setColumnWidth(11, 35*150);
        sheet.setColumnWidth(12, 35*150);
        sheet.setColumnWidth(13, 35*150);
        sheet.setColumnWidth(14, 35*150);
        sheet.setColumnWidth(15, 35*150);
        sheet.setColumnWidth(16, 35*150);
        sheet.setColumnWidth(17, 35*150);
        sheet.setColumnWidth(18, 35*150);
        sheet.setColumnWidth(19, 35*150);
        sheet.setColumnWidth(20, 35*150);
        sheet.setColumnWidth(21, 35*150);
        //headers表示excel表中第一行的表头

    	//表头样式2 开始
		HSSFFont titleFont2 = workbook.createFont();
		titleFont2.setFontName("宋体");
		titleFont2.setFontHeightInPoints((short) 11);// 字号
		titleFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		
		HSSFCellStyle style2 = workbook.createCellStyle(); // 表格内容样式
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style2.setWrapText(true); // 设置换行
		style2.setFont(titleFont2);
        
		//内容样式3开始
		HSSFFont font= workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);// 字号
		//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		
		HSSFCellStyle style3 = workbook.createCellStyle(); // 表格内容样式
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style3.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style3.setWrapText(true); // 设置换行
		style3.setFont(font);
		
        HSSFRow row = sheet.createRow(0);
        
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
           cell.setCellStyle(style2);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            
        }

        //在表中存放查询到的数据放入对应的列
        for (WaterJCJH welltmp : datalist) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(welltmp.getSite_name());
            row1.createCell(1).setCellValue(welltmp.getYc_name());
            row1.createCell(2).setCellValue(welltmp.getWell_name());
            row1.createCell(3).setCellValue(welltmp.getCs_explaim());
            row1.createCell(4).setCellValue(welltmp.getCs_type_name());
            row1.createCell(5).setCellValue(welltmp.getStime());
            row1.createCell(6).setCellValue(welltmp.getWell_section());
            row1.createCell(7).setCellValue(welltmp.getThickness());
            row1.createCell(8).setCellValue(welltmp.getPlies_num()==null?"":welltmp.getPlies_num()+"");
            row1.createCell(9).setCellValue(welltmp.getCs_purpose());
            row1.createCell(10).setCellValue(welltmp.getCs_claim());
            row1.createCell(10).setCellValue(welltmp.getCs_water_injection()==null?"":welltmp.getCs_water_injection()+"");
            row1.createCell(11).setCellValue(welltmp.getLast_success_date());
            row1.createCell(12).setCellValue(welltmp.getRecently_fail_date());
            row1.createCell(13).setCellValue(welltmp.getRecently_revise_mesg());
            row1.createCell(14).setCellValue(welltmp.getLevel_demand()==null?"":welltmp.getLevel_demand()+"");
            row1.createCell(15).setCellValue(welltmp.getTc_date());
            row1.createCell(16).setCellValue(welltmp.getFk_cd_date());
            row1.createCell(17).setCellValue(welltmp.getWcd_mesg());
            row1.createCell(18).setCellValue(welltmp.getSuccess_date());
            row1.createCell(19).setCellValue(welltmp.getFail_mesg());
            row1.createCell(20).setCellValue(welltmp.getTj_status());
            row1.createCell(21).setCellValue(welltmp.getSh_status());
            
            rowNum++;
        }

        String newFileName = "";
		try {
			newFileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + newFileName+ ".xls");
        try {
			response.flushBuffer();
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	
}
