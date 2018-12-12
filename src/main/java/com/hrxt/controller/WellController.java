package com.hrxt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Well;
import com.hrxt.service.WellService;
import com.hrxt.utils.JSONutils;

@Controller
@RequestMapping("/well")
public class WellController {

	@Autowired
	private WellService wellService;
	
	
	//获取井信息
	/**   /well/getWellMsg
	 * 
	 * @param well
	 * @return
	 */
	@RequestMapping("/getWellMsg")
	@ResponseBody
	public String getWell(Well well){
		List<Well> welllist = this.wellService.callWellList(well);
		System.out.println(welllist.toString());
		return JSON.toJSONString(welllist);
	}
	

	
	
	
	
	
	/**
	 * 下载导入模板
	 * @param well
	 * @return
	 */
	@RequestMapping("/dwnloadTmp")
	public void dwnloadTmpMB(Well well,HttpServletResponse response){
		
		List<Well> welllist = this.wellService.callWellList(well);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        String fileName = "月配注模板导出"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "工区", "断块", "井名", "地质配注","考核配注","时间","备注"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Well welltmp : welllist) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(welltmp.getOrgName());
            row1.createCell(1).setCellValue(welltmp.getSiteName());
            row1.createCell(2).setCellValue(welltmp.getWellCommonName());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
			response.flushBuffer();
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	
	
	
	
}
