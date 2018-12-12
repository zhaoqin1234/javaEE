package com.hrxt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Csjh;
import com.hrxt.pojo.CsjhOption;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.ReportCSJH;
import com.hrxt.pojo.ReportNote;
import com.hrxt.pojo.Site;
import com.hrxt.service.CsjhService;
import com.hrxt.service.OrgService;
import com.hrxt.utils.ExcelUtil;
import com.hrxt.utils.JSONutils;
/**
 * 措施计划 controller
 * @author ZhaoQin
 *
 */
@Controller
@RequestMapping("/csjh")
public class CsjhController {

	@Autowired
	private CsjhService csjhService;
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insetCsjh(Csjh csjh){	
		Map<String,Object> map = this.csjhService.insertOrUpdate(csjh);
		return JSON.toJSONString(map);
	}
	
	
	@RequestMapping("/insertList")
	@ResponseBody
	public String insetCsjhList( Csjh csjh){	
		//System.out.println(JSONutils.Object2String(csjh));
		List<Csjh> list11 = new ArrayList<Csjh>();
		list11.add(csjh);
		List<String> list = csjhService.batchInsertOrUpdate(list11);
		return JSONutils.Object2String(list);
	}
	
	
	@RequestMapping("/delOneCsjh")
	@ResponseBody
	public String delOneCsjh(String seq){	
		  Map<String,Object> map = this.csjhService.delOneYPZBB(seq);
		  return JSON.toJSONString(map);
	}

	
	@RequestMapping("/delCsjhList")
	@ResponseBody
	public String delCsjhList(String seqlist){	
		Map<String,Object> map = this.csjhService.delListYPZBB(seqlist);
		return JSON.toJSONString(map);
	}
	
	
	@RequestMapping("/getCsjhList")
	@ResponseBody
	public String getCsjhList(Csjh csjh,Page<List<Csjh>> page){	
		return JSONutils.Object2String(csjhService.getMonthPZList(csjh, page));
	}
	
	
	/**  /csjh/auditCsjh
	 * 记录审核  方法
	 * @param seqlist
	 * @return
	 */
	@RequestMapping("/auditCsjh")
	@ResponseBody
	public String auditCsjh(String seqlist){	
		return csjhService.auditOneCsjh(seqlist);
	}
	
	@RequestMapping("/unAuditCsjh")
	@ResponseBody
	public String unAuditCsjh(String seqlist){	
		return csjhService.unAuditOneCsjh(seqlist);
	}
	/**  /csjh/submitCsjh
	 * 【提交】  方法
	 * @param seqlist
	 * @return
	 */
	@RequestMapping("/submitCsjh")
	@ResponseBody
	public String submitMonthPZ(String seqlist){	
		return csjhService.submitCsjh(seqlist);
	}
	@RequestMapping("/unSubmitCsjh")
	@ResponseBody
	public String unSubmitCsjhH(String seqlist){	
		return csjhService.unSubmitCsjh(seqlist);
	}
	
	
	
	/**
	 * 批量上传数据
	 * @param fileMult
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/excelUpdateCsjh")
	public String updateCsjhList(MultipartFile fileMult,String fileName){	
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
			Csjh csjh = new Csjh();
			csjh.setSite_name((String)map.get("site_name"));
			csjh.setYc_name((String)map.get("yc_name"));
			//csjh.setDzpz((Integer.parseInt((String) map.get("dzpz"))));
			csjh.setWell_name((String)map.get("well_name"));
			csjh.setStime((String)map.get("stime"));
			csjh.setMark((String)map.get("mark"));
			System.out.println(JSONutils.Object2String(map));
			this.csjhService.insertOrUpdate(csjh);
		}
		
		return "";
	}
	
	
	
	/**  
	 * 下载导入模板        措施计划, 
	 */
	@RequestMapping("/dwnloadCsjhTmp")
	public void dwnloadTmpMB(Csjh csjh,Page<List<Csjh>> page,HttpServletResponse response,String fileName){
		
		//获取措施计划数据
		Map<String,Object>  map = this.csjhService.getMonthPZList(csjh, page);
		List<Csjh> datalist = (List<Csjh>)map.get("data");
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        if(fileName ==  null) {
        	fileName = "措施计划";//设置要导出的文件的名字
        }
        
        //新增数据行，并且设置单元格数据
        int rowNum = 1;

        String[] headers = { "工区", "断块", "井名", "地质配注","考核配注","时间","备注"};
        
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
        for (Csjh csjhTmp : datalist) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(csjhTmp.getSite_name());
            row1.createCell(1).setCellValue(csjhTmp.getYc_name());
            row1.createCell(2).setCellValue(csjhTmp.getWell_name());
            if(csjhTmp.getWell_type_name() != null) {
            	row1.createCell(3).setCellValue(csjhTmp.getWell_type_name());
            }
            if(csjhTmp.getWell_step_name() != null) {
            	row1.createCell(4).setCellValue(csjhTmp.getWell_step_name());
            }
            if(csjhTmp.getStime() != null) {
            	row1.createCell(5).setCellValue(csjhTmp.getStime());
            }
            if(csjhTmp.getMark() != null) {
            	row1.createCell(6).setCellValue(csjhTmp.getMark());
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
	 * 请求URL：  /csjh/CSJHReportData?cs_type=0 
	 * 获取测试计划  报表数据
	 * @param site_ids   工区id，逗号分隔
	 * @param cs_ids	 测试类型id，逗号分隔
	 * @param stime		测试时间，必须传  格式   yyyy-mm
	 * @param cs_type	0 常规测试  1  重点测试
	 * @return
	 */
	@RequestMapping("/CSJHReportData")
	@ResponseBody
	public String CSJHReportData(String site_ids,String cs_ids,String stime,String cs_type,String bbName){
		String defbbName = "";
		//stime = "2018-11";  
		Map<String,Object>  map = this.csjhService.getReportCSJHRecord(site_ids,cs_ids,stime,cs_type);
		@SuppressWarnings("unchecked")
		List<ReportCSJH> datalist = (List<ReportCSJH>)map.get("data");
		List<Site> sitedatalist = (List<Site>)map.get("sitedata");
		String o_out_mes = (String)map.get("o_out_mes");  //报表的备注信息
		String o_out_note_id = (String)map.get("o_out_note_id");  //报表的备注信息
		
		//System.out.println(">>>"+JSON.toJSONString(datalist));
		
		//下文需要判断的   标志位
		String oilWellFlag = "0";
		String waterWellFlag = "1";
		String otherllFlag = "";
		////开始统计数据    存放统计信息
		Map<String,Object> site_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> csType_oil_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> csType_water_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> csType_other_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> wellType_statistics = new LinkedHashMap<String,Object>();
		//对数据进行分析
		Map<String, Object> cdjhData_statistics = __fenXiZuZhuangData(datalist, 
										site_statistics, csType_oil_statistics,csType_water_statistics, 
										csType_other_statistics, wellType_statistics, oilWellFlag, waterWellFlag);
		
		
		//  开始组装前台需要的数据
		//  ret -->return_title
		//  ret -->return_data<map>
		//         map ->  项目、措施、工区1、工区2、工区3...工区n、合计、新井、老井
		//         tiele_data -> ping
		Map<String,Object> ret = new HashMap<String,Object>();
			// title数据组装
		List<Map<String,Object>> return_title = new ArrayList<Map<String,Object>>();
		Map<String,Object> tmpTitle1 = new HashMap<String,Object>();
		tmpTitle1.put("title", "项目");
		tmpTitle1.put("name", "xm");
		return_title.add(tmpTitle1);
		Map<String,Object> tmpTitle2 = new HashMap<String,Object>();
		tmpTitle2.put("title", "措施");
		tmpTitle2.put("name", "cs");
		return_title.add(tmpTitle2);
		for (Site site: sitedatalist) {
		//for (Map.Entry<String, Object> site_entry:site_statistics.entrySet()) { 
			Map<String,Object> tmpTitlei = new HashMap<String,Object>();
			//String site_id = site_entry.getKey();
			//ReportCSJH reportCSJH  =  (ReportCSJH) site_entry.getValue();
			tmpTitlei.put("title", site.getSite_name());
			tmpTitlei.put("name", site.getSite_id());
			tmpTitlei.put("title_sub1", "井号");
			tmpTitlei.put("title_sub2", "日增油(t)");
			tmpTitlei.put("name_sub1", site.getSite_id()+"_sub1");
			tmpTitlei.put("name_sub2", site.getSite_id()+"_sub2");
			return_title.add(tmpTitlei);
		}
		Map<String,Object> tmpTitlen = new HashMap<String,Object>();
		tmpTitlen.put("title", "合计");
		tmpTitlen.put("name", "heji");
		tmpTitlen.put("title_sub1", "井数");
		tmpTitlen.put("title_sub2", "日增油(t)");
		tmpTitlen.put("name_sub1", "xmjingheji");
		tmpTitlen.put("name_sub2", "xmRZYheji");
		return_title.add(tmpTitlen);
		Map<String,Object> tmpTitlen1 = new HashMap<String,Object>();
		tmpTitlen1.put("title", "遗留");
		tmpTitlen1.put("name", "xinjingheji");
		return_title.add(tmpTitlen1);
		Map<String,Object> tmpTitlen2= new HashMap<String,Object>();
		tmpTitlen2.put("title", "新增");
		tmpTitlen2.put("name", "laojingheji");
		return_title.add(tmpTitlen2);
		
		
		//  开始  准备返回的data长度
		Map<String,Object> return_data = new HashMap<String,Object>();
		List<Map<String,Object>> return_datalist = new ArrayList<Map<String,Object>>();
		int siteSize =  site_statistics.size();
		int oilCSSize =  csType_oil_statistics.size();
		int waterCSize =  csType_water_statistics.size();
		int otherCSSize =  csType_other_statistics.size();
		int wellTypeSize =  wellType_statistics.size();
		int allCSSize = oilCSSize+waterCSize+otherCSSize ;
		
		for (Map.Entry<String, Object> xms_entry:csType_oil_statistics.entrySet()) {  //油井
			String oil_cs_id = xms_entry.getKey();
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("id", oil_cs_id);
			tmp.put("type", oilWellFlag);
			return_datalist.add(tmp);
		}
		if(oilCSSize != 0) {  //油井合计
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("id", "oil_heji");
			tmp.put("type", "heji");
			return_datalist.add(tmp);
		}
		for (Map.Entry<String, Object> xms_entry:csType_water_statistics.entrySet()) { //水井
			String water_cs_id = xms_entry.getKey();
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("id", water_cs_id);
			tmp.put("type", waterWellFlag);
			return_datalist.add(tmp);
		}
		if(waterCSize != 0) {  //水井合计
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("id", "water_heji");
			tmp.put("type", "heji");
			return_datalist.add(tmp);
		}
		for (Map.Entry<String, Object> xms_entry:csType_other_statistics.entrySet()) {// 其他井
			String other_cs_id = xms_entry.getKey();
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("id", other_cs_id);
			tmp.put("type", otherllFlag);
			return_datalist.add(tmp);
		}
		if(otherCSSize != 0) {  //其他合计
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("id", "other_heji");
			tmp.put("type", "heji");
			return_datalist.add(tmp);
		}
		Map<String,Object> tmp = new HashMap<String,Object>();  //总计
		tmp.put("id", "zongji");
		tmp.put("type", "zongji");
		return_datalist.add(tmp);
		
		System.out.println(JSON.toJSONString(return_datalist));
		
		//为返回的  return_map_list 添加数据
		for (int i = 0; i < return_datalist.size(); i++) {
			Map<String,Object> datatmp = return_datalist.get(i);
			String data_id = (String) datatmp.get("id");
			String data_type = (String) datatmp.get("type");
			boolean isFind = false;
			//开始为map找数据
			for (Map.Entry<String, Object> xms_entry:cdjhData_statistics.entrySet()) { 
				String well_type_flag = xms_entry.getKey();
				ReportCSJH reportCSJH =null;
				if(data_type.equals(well_type_flag)) {
					isFind = true;//找到对应的项目类型了   
				}
				Map<String,Object> xm_map  =  (Map<String, Object>) xms_entry.getValue();
				if(well_type_flag.equals(data_type)) {
					if(oilWellFlag.equals(data_type)) {
						datatmp.put("xm", "油井");
					}else if(waterWellFlag.equals(data_type)) {
						datatmp.put("xm", "水井");
					}else if(otherllFlag.equals(data_type)) {
						datatmp.put("xm", "其他");
					}else if("oil_heji".equals(data_type)) {
						datatmp.put("xm", "油井");
					}else if("water_heji".equals(data_type)) {
						datatmp.put("xm", "水井");
					}else if("zongji".equals(data_type)) {
						datatmp.put("xm", "总计");
					}
					
				}
				
				if(data_type.equals(oilWellFlag)) {
					reportCSJH = (ReportCSJH) csType_oil_statistics.get(data_id);
					datatmp.put("cs", reportCSJH.getCs_name());
				}else if(data_type.equals(waterWellFlag)) {
					reportCSJH = (ReportCSJH) csType_water_statistics.get(data_id);
					datatmp.put("cs", reportCSJH.getCs_name());
				}else if(data_type.equals(otherllFlag)){
					reportCSJH = (ReportCSJH) csType_other_statistics.get(data_id);
					datatmp.put("cs", reportCSJH.getCs_name());
				}else if(data_type.equals("heji")) {
					datatmp.put("cs", "小计");
				}else {
					datatmp.put("cs", "合计");
				}
				
				
				int  allWellCount = 0;
        		int  newWellCount = 0;
        		int  oldWellCount = 0;
        		int  otherWellCount = 0;
        		Double allCSRiZengOilCount = (double) 0;  //
				//各个工区
				for (Map.Entry<String, Object> site_entry:xm_map.entrySet()) {  
						String site_id = site_entry.getKey();
		        		Map<String,Object> site_map  =  (Map<String, Object>) site_entry.getValue();
		        		
		        		Double csRiZengYouCount = (double) 0;
		        		//措施类型 1 2 3  
		        		for (Map.Entry<String, Object> cs_entry:site_map.entrySet()) {
		        			String cs_id = cs_entry.getKey();
		        			Map<String,Object> csjh_tmp_map = (Map<String, Object>) cs_entry.getValue();
		        			List<ReportCSJH> newWelllist = (List<ReportCSJH>) csjh_tmp_map.get("newWell");
		        			List<ReportCSJH> oldWelllist = (List<ReportCSJH>) csjh_tmp_map.get("oldWell");
		        			List<ReportCSJH> otherWellList = (List<ReportCSJH>) csjh_tmp_map.get("otherWell");
		        			
		        			if(data_id.equals(cs_id)) {
		        				String newWellNameConStr = __statisticsList(newWelllist);
		        				String oidWellNameConStr = __statisticsList(oldWelllist);
		        				String otherWellNameConStr = __statisticsList(otherWellList);
		        				String wellConStr = "<u>"+newWellNameConStr+"</u>"+"<font color=\"red\">"+oidWellNameConStr+"</font>"+otherWellNameConStr;
		        				Double csRiZengOilCount = (double) 0;  //
		        				csRiZengOilCount = __dataListRiZengOilCount(newWelllist,csRiZengOilCount);
								csRiZengOilCount = __dataListRiZengOilCount(oldWelllist,csRiZengOilCount);
								csRiZengOilCount = __dataListRiZengOilCount(otherWellList,csRiZengOilCount);
		        				int newWellSize = newWelllist.size();
		        				int oldWellSize = oldWelllist.size();
		        				int otherWellSize = otherWellList.size();
		        				int allWellSumInner = newWellSize + oldWellSize + otherWellSize ;
		        				
		        				allWellCount += allWellSumInner;
		        				newWellCount += newWellSize;
		        				oldWellCount += oldWellSize;
		        				otherWellCount += otherWellSize;
		        				allCSRiZengOilCount += csRiZengOilCount;
		        				datatmp.put(site_id+"_new", newWellSize);
		        				datatmp.put(site_id+"_old", oldWellSize);
		        				datatmp.put(site_id+"_other", otherWellSize);
		        				datatmp.put(site_id+"_sub1", wellConStr);	//井名 
		        				datatmp.put(site_id+"_sub2", csRiZengOilCount==0?"":csRiZengOilCount);//日增油数
		        				datatmp.put(site_id+"_all", allWellSumInner);
		        				//System.out.println(cs_id+"=="+allWellSum+"==="+csRiZengOilCount);
		        				break;  //找到对应csjh就跳出循环
		        			}
		        		}
		        		datatmp.put("xmjingheji", allWellCount==0?"":allWellCount);
		        		datatmp.put("xmRZYheji", allCSRiZengOilCount==0?"":allCSRiZengOilCount);
		        		datatmp.put("xinjingheji", newWellCount==0?"":newWellCount);
		        		datatmp.put("laojingheji", oldWellCount==0?"":oldWellCount);
		        		datatmp.put("qitajingheji", otherWellCount==0?"":otherWellCount);
		        		
				}
				if(isFind) {  //在第一类型中找到就不去下面的类型继续去找了
					break;
				}
				
			}//分析的数据循序结束  遍历项目  油井  水井 其他井
		}
		//开始为小计求和
		String [] fields = {"_new","_old","_other","_all","_sub2","xmjingheji","xmRZYheji","xinjingheji","laojingheji","qitajingheji"};
		String [] hejiNumKey = {"xmjingheji","xmRZYheji","xinjingheji","laojingheji"};
		Map<String,Object> oilXiaoJi = new HashMap<String,Object>();
		Map<String,Object> waterXiaoJi = new HashMap<String,Object>();
		Map<String,Object> otherXiaoJi = new HashMap<String,Object>();
		Map<String,Object> allHeJi = new HashMap<String,Object>();
		for (int i = 0; i < return_datalist.size(); i++) {
			Map<String,Object> tmpMap = return_datalist.get(i);
			String id = (String) tmpMap.get("type");
			
			__calcFieldCount(oilWellFlag,oilXiaoJi,tmpMap,id,fields);
			__calcFieldCount(waterWellFlag,waterXiaoJi,tmpMap,id,fields);
			__calcFieldCount(otherllFlag,otherXiaoJi,tmpMap,id,fields);
			__calcFieldCount("heji",allHeJi,tmpMap,id,fields);
			
		}
		
		// siteId_all  -->> siteId_sub
		int oilindex = 0;
		int waterindex = 0;
		int otherindex = 0;
		for (int i = 0; i < return_datalist.size(); i++) {
			Map<String,Object> maptmp = return_datalist.get(i);
			if(maptmp.get("id").equals("oil_heji")) {
				oilindex = i;
				__make_all2__sbu1(site_statistics,oilXiaoJi,maptmp,"_all","_sub1",hejiNumKey);
			}
			if(maptmp.get("id").equals("water_heji")) {
				waterindex = i;
				__make_all2__sbu1(site_statistics,waterXiaoJi,maptmp,"_all","_sub1",hejiNumKey);
			}
			if(maptmp.get("id").equals("other_heji")) {
				otherindex = i ;
				__make_all2__sbu1(site_statistics,otherXiaoJi,maptmp,"_all","_sub1",hejiNumKey);
			}
			if(maptmp.get("id").equals("zongji")) {
				Map<String,Object> oiltmpMap = oilindex==0?null:return_datalist.get(oilindex);
				Map<String,Object> watertmpMap = waterindex==0?null:return_datalist.get(waterindex);
				Map<String,Object> othertmpMap = otherindex==0?null:return_datalist.get(otherindex);
				__sum_heji2all_heji(site_statistics,oiltmpMap,watertmpMap,othertmpMap,maptmp,hejiNumKey);
			}
		}
		//System.out.println(JSON.toJSONString(site_statistics));
		//System.out.println(JSON.toJSONString(waterXiaoJi));
		//System.out.println(JSON.toJSONString(otherXiaoJi));
		//System.out.println(JSON.toJSONString(allHeJi));
		//System.out.println(JSON.toJSONString(return_datalist));
		
		ret.put("title", return_title);
		ret.put("data", return_datalist);
		ret.put("stime", stime);
		ret.put("bbName", bbName);
		ret.put("sitedata", sitedatalist);
		ret.put("o_out_mes", o_out_mes);
		ret.put("o_out_note_id", o_out_note_id);  // 备注信息的  id
		return JSONutils.Object2String(ret);
	}
	
	
	
	/**
	 * 将各个合计map  -->  求和放到  all_heji的map中
	 * @param siteMap   // 遍历所有的工区   mapkey 由工区id决定
	 * @param oilSumMap
	 * @param waoterSumMap
	 * @param othrerSumMap
	 * @param destSumMap   //目标地方  存放的map
	 * @param hejiNumKey  //最后合计部分的字段
	 */
	public void __sum_heji2all_heji(Map<String,Object> siteMap,Map<String,Object> oilSumMap,
									Map<String,Object> waoterSumMap,Map<String,Object> othrerSumMap,Map<String,Object> destSumMap,String [] hejiNumKey) {
		String __sub1 = "_sub1";
		String __sub2 = "_sub2";
		for (String sitekey : siteMap.keySet()) {
			Double val_sub1 = (double) 0;// srcMap.get(sitekey+__sub1);
			Double val_sub2 = (double) 0;//srcMap.get(sitekey+__sub2);
			// 工区油井数统计
			if(oilSumMap != null) {
				if(oilSumMap.get(sitekey+__sub1) != null) {
					val_sub1 += (Double)oilSumMap.get(sitekey+__sub1);
				}
			}
			if(waoterSumMap != null) {
				if(waoterSumMap.get(sitekey+__sub1) != null) {
					val_sub1 += (Double)waoterSumMap.get(sitekey+__sub1);
				}
			}
			if(othrerSumMap != null) {
				if(othrerSumMap.get(sitekey+__sub1) != null) {
					val_sub1 += (Double)othrerSumMap.get(sitekey+__sub1);
				}
			}	//日增油统计
			if(oilSumMap != null) {
				if(oilSumMap.get(sitekey+__sub2) != null) {
					val_sub2 += (Double)oilSumMap.get(sitekey+__sub2);
				}
			}
			if(waoterSumMap != null) {
				if(waoterSumMap.get(sitekey+__sub2) != null) {
					val_sub2 += (Double)waoterSumMap.get(sitekey+__sub2);
				}
			}
			if(othrerSumMap != null) {
				if(othrerSumMap.get(sitekey+__sub2) != null) {
					val_sub2 += (Double)othrerSumMap.get(sitekey+__sub2);
				}
			}
			destSumMap.put(sitekey+__sub1, val_sub1);
			destSumMap.put(sitekey+__sub2, val_sub2);
		}
		// 最后的合计字段统计
		for (int i = 0; i < hejiNumKey.length; i++) {
			String tmpkey = hejiNumKey[i];
			Double tmpVal = (double) 0;
			if(oilSumMap != null) {
				if(oilSumMap.get(tmpkey) != null) {
					tmpVal += (Double)oilSumMap.get(tmpkey);
				}
			}
			if(waoterSumMap != null) {
				if(waoterSumMap.get(tmpkey) != null) {
					tmpVal += (Double)waoterSumMap.get(tmpkey);
				}
			}
			if(othrerSumMap != null) {
				if(othrerSumMap.get(tmpkey) != null) {
					tmpVal += (Double)othrerSumMap.get(tmpkey);
				}
			}
			destSumMap.put(tmpkey, tmpVal);
		}
	}
	
	
	/**
	 * 作用将   siteId_all -->  siteId_Sub
	 * 将安项目统计好的 数字  放到合计中的  对应的key中去
	 * @param siteMap
	 * @param tmpMap
	 * @param srcKey
	 * @param destKey
	 */
	public void __make_all2__sbu1(Map<String,Object> siteMap,
								  Map<String,Object> srcMap,
								  Map<String,Object> tmpMap,
								  String srcKey,String destKey,String [] hejiNumKey) {
		//开始各个工区合计部分
		String __sub2 = "_sub2";  //  map中存放的额日增油信息
		for (String sitekey : siteMap.keySet()) {
			Double val1 = (Double) srcMap.get(sitekey+srcKey);
			Double val2 = (Double) srcMap.get(sitekey+__sub2);
			tmpMap.put(sitekey+destKey, val1);
			tmpMap.put(sitekey+__sub2, val2);
		}
		//最后的合计部分   井数统计   日增油  新井合计  老井合计
		for (int i = 0; i < hejiNumKey.length; i++) {
			String tmpkey = hejiNumKey[i];
			Double val3 = (Double) srcMap.get(tmpkey);
			tmpMap.put(tmpkey, val3);
		}
		
	}
	
	
	/**
	 *  map的指定字段进行求和    存到一个map里面
	 * @param idkey  map_id 过滤只有相同的id才进行累加
	 * @param dest	 //累加结果存到指定的map
	 * @param tmp	 // 累加的map
	 * @param tmpId  //  tmp的id  同 idkey 进行比较 相同计算
	 * @param fieldArray // 求累加和 的段  所包含的关键字
	 */
	public void __calcFieldCount(String idkey,Map<String,Object> dest,Map<String,Object> tmp,String tmpId,String[] fieldArray) {
		if(tmpId.equals(idkey)) {
			for (String key : tmp.keySet()) {  //开始遍历字段
				for (int j = 0; j < fieldArray.length; j++) {
					if(key.contains(fieldArray[j])) {  //是不是需要计算的field  _sub1是字符的计算没有意义
						if(!dest.containsKey(key)) {
							dest.put(key, tmp.get(key));
						}else {
							final String reg = "\\d+\\.{0,1}\\d*"; 
							String srcStr = (String) tmp.get(key).toString();
							String destStr = (String) dest.get(key).toString();
							Double src1 = (double) 0;
							Double src2 = (double) 0;
							if(srcStr.matches(reg)) {
								src1 = Double.parseDouble(srcStr);
							}
							if(destStr.matches(reg)) {
								src2 = Double.parseDouble(destStr);
							}
							dest.put(key, src1 +  src2  );
						}
					}
				}
			}
		}
	}
	
	
	
	
	
	/**  
	 *   /csjh/dwnloadCSJHReport
	 *   http://localhost:8080/DZSBB/csjh/dwnloadCSJHReport
	 *    down 下载导入模板        措施计划,
	 *    
	 * @param site_ids   工区id，逗号分隔
	 * @param cs_ids	 测试类型id，逗号分隔
	 * @param stime		测试时间，必须传  格式   yyyy-mm
	 * @param cs_type   0  常规测试  1  重点测试
	 * 		井别识别  0 油井 ，1水井，非0和1就识别为其他井
	 * 		程序性能O(n) 数据列表 遍历了3次
	 * 	zhaoqin 2018年11月20日17:36:59
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/dwnloadCSJHReport")
	public void dwnloadCSJHReport(String site_ids,String cs_ids,String stime,String cs_type,
								  HttpServletResponse response,String fileName){
		//stime = "2018-11";//测试直接指定信息
		//cs_type = "1";
		
		//获取工区id 和 name
		List<Site> orglist = this.orgService.getPublicSite();
		//获取措施计划数据
		Map<String,Object>  map = this.csjhService.getReportCSJHRecord(site_ids,cs_ids,stime,cs_type);
		@SuppressWarnings("unchecked")
		List<ReportCSJH> datalist = (List<ReportCSJH>)map.get("data");
		List<Site> sitedatalist = (List<Site>)map.get("sitedata");
		String o_out_mes = (String)map.get("o_out_mes");  //报表的备注信息
		
		String oilWellFlag = "0";
		String waterWellFlag = "1";
		String otherllFlag = "";
		
		////开始统计数据    存放统计信息
		Map<String,Object> site_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> csType_oil_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> csType_water_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> csType_other_statistics = new LinkedHashMap<String,Object>();
		Map<String,Object> wellType_statistics = new LinkedHashMap<String,Object>();
		
		/**  【【【【【【第一步】】】】】
		 * 遍历后台的 报表记录    ：datalist
		 * 井类型	：wellType_statistics
		 * 油井测试类型 ：csType_oil_statistics
		 * 水井测试类型：csType_water_statistics
		 * 其他井测试类型：   csType_other_statistics
		 * 工区统计信息：site_statistics
		 */
		Map<String, Object> cdjhData_statistics = __fenXiZuZhuangData(datalist, 
										site_statistics, csType_oil_statistics,csType_water_statistics, 
										csType_other_statistics, wellType_statistics, oilWellFlag, waterWellFlag);
		
		
		//      【【【【【【第二步】】】】】
		//开始为excel中添加数据
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("常规措施表");

        //表头样式 1 开始
  		HSSFFont titleFont = workbook.createFont();
  		titleFont.setFontName("微软雅黑");
  		titleFont.setFontHeightInPoints((short) 16);// 字号
  		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
      		
  		// ，创建单元格，并设置值表头 设置表头居中  
		HSSFCellStyle style = workbook.createCellStyle(); // 表格内容样式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setWrapText(true); // 设置换行
		style.setFont(titleFont);
  			
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
		
		
		HSSFFont titleFont3 = workbook.createFont();
		titleFont2.setFontName("宋体");
		titleFont2.setFontHeightInPoints((short) 11);// 字号
		titleFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		
		HSSFCellStyle style3 = workbook.createCellStyle(); // 表格内容样式
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style3.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style3.setWrapText(true); // 设置换行
		style3.setFont(titleFont3);
		
        if(fileName ==  null) {
        	fileName = "措施计划";//设置要导出的文件的名字
        }
        
        //新增数据行，并且设置单元格数据
        int rowNum = 4;
        
        String headers = "常规措施计划表";
        if(stime != null) {
        	String[] stimearray = stime.split("-");  //xxxx-xx
            headers = stimearray[0] + "年" + stimearray[1]+"月"+headers;
        }
        
        
        String[] title1 = { "项目", "措施", "合计"};//中间的  工区名字 来自map
        //每个工区的 子项目   只能改名  不能增加项
        String[] title2 = { "井号", "日增油(t)"};
        
        //第二行
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("表5");
        //第二行  //  XXXX年X月措施计划表
        row = sheet.createRow(1);  
        HSSFCell callheader =row.createCell(0);
        callheader.setCellValue(headers);
        callheader.setCellStyle(style);
        row.setHeight((short)1000);
        // 第三行   1,2列
        HSSFRow row2 = sheet.createRow(2);   
        HSSFCell calltitle1 = row2.createCell(0);//项目  + 样式
        calltitle1.setCellValue(title1[0]);
        calltitle1.setCellStyle(style2);
        HSSFCell calltitle2  =row2.createCell(1);//措施 + 样式
        calltitle2.setCellValue(title1[1]);
        calltitle2.setCellStyle(style2);
        row2.setHeight((short)600); 
        //第四行  填充  为了  边框设计样式
        HSSFRow row3 = sheet.createRow(3);		//第三行的样式
        row3.setHeight((short)600); 
        HSSFCell calltitle3 = row3.createCell(0);//项目 下一行的样式
        calltitle3.setCellStyle(style2);
        calltitle3.setCellValue("");
        HSSFCell calltitle4  =row3.createCell(1);//措施 下一行的样式
        calltitle4.setCellStyle(style2);
        calltitle4.setCellValue("");
        
        // 第三、四行   各个工区的名字    及  下面的 “油井” “井数”
        __makeBiaoTou(sitedatalist,site_statistics, sheet, style2, title2, row2, row3);
        
        ///为"合计"部分  添加求和格公式    
        __addHeJiSumFunction(sheet,site_statistics,
				 csType_oil_statistics, csType_water_statistics,csType_other_statistics, 
				 wellType_statistics,style,style2,style3);
        
      
        //  开始拼接 ab列的项目类型名称   最左侧 名字
        __makeBiaoZuoCeTou(oilWellFlag, waterWellFlag, csType_oil_statistics, csType_water_statistics,
				csType_other_statistics, cdjhData_statistics, sheet, style, style2, style3);
       
        //开始添加 数据区域
	    __makeBiaoDataFall(sitedatalist,oilWellFlag, waterWellFlag, 
	    					site_statistics, csType_oil_statistics, csType_water_statistics,
	    					csType_other_statistics, cdjhData_statistics, workbook, sheet, style2);
        
	    //右侧的统计信息
	    __makeTongJiOilWell(oilWellFlag, waterWellFlag, site_statistics, csType_oil_statistics, csType_water_statistics,
				csType_other_statistics, cdjhData_statistics, workbook, sheet, style2);
	    
	    //为报表添加备注信息
	    int RowLastNum = sheet.getLastRowNum();
	    HSSFRow lastRow =sheet.getRow(RowLastNum);
	    HSSFCell lastCell = lastRow.createCell(0);
	    //红色字体
	    Font font2 = workbook.createFont();  
	    font2.setFontHeightInPoints((short) 12); // 字体高度  
	    font2.setFontName("宋体"); // 字体  
	    font2.setColor(HSSFColor.RED.index);  //颜色
	    HSSFRichTextString ts = new HSSFRichTextString(o_out_mes);
	    ts.applyFont(0,o_out_mes.length(),font2); 
	    lastCell.setCellValue(ts);
	    
        //冻结表格 左2列 上两列
        sheet.createFreezePane(2, 4, 2, 4);
        
        //公式自动自动计算
        sheet.setForceFormulaRecalculation(true); 
        try {
			response.setContentType("application/octet-stream");
			String newFileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename=" + newFileName+ ".xls");
			response.flushBuffer();
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	

	/**
	 * 开始遍历  井  为每个工区添加数据 
	 * @param oilWellFlag
	 * @param waterWellFlag
	 * @param site_statistics
	 * @param csType_oil_statistics
	 * @param csType_water_statistics
	 * @param csType_other_statistics
	 * @param cdjhData_statistics
	 * @param workbook
	 * @param sheet
	 * @param style2
	 */
	public void __makeBiaoDataFall(List<Site> sitedatalist,
								   String oilWellFlag, String waterWellFlag, Map<String, Object> site_statistics,
								   Map<String, Object> csType_oil_statistics, Map<String, Object> csType_water_statistics,
								   Map<String, Object> csType_other_statistics, Map<String, Object> cdjhData_statistics, HSSFWorkbook workbook,
								   HSSFSheet sheet, HSSFCellStyle style2) {
		int rowNum;
		int colNum = 2 ;	//列开始为工区拼接数据
		
		// 项目  油井、水井  
        for (Map.Entry<String, Object> xms_entry:cdjhData_statistics.entrySet()) { 
        	String well_type_flag = xms_entry.getKey();
        	Map<String,Object> xm_map  =  (LinkedHashMap<String, Object>) xms_entry.getValue();
        	colNum = 2;// 每种油井类型都是从  2列开始拼接的
        	Double GongQuProdCountBy = (double) 0 ;
			int gongQuWellSum  = 0 ;//工区下 井求和
			
			
			//工区遍历
			for (Site site: sitedatalist) {
				Map<String,Object> site_map = (Map<String, Object>) xm_map.get(site.getSite_id());
				String site_id = site.getSite_id();
			//}
        	//for (Map.Entry<String, Object> site_entry:xm_map.entrySet()) {   
        	//	String site_id = site_entry.getKey();
        	//	Map<String,Object> site_map  =  (LinkedHashMap<String, Object>) site_entry.getValue();
        	//	System.out.println(site_id+"===="+JSON.toJSONString(site_map));
        		rowNum = 4;//每个工区都是从第四行开始的
        		
        		List<ReportCSJH> newWelllist = null;
				List<ReportCSJH> oldWelllist = null;
				List<ReportCSJH> otherWellList = null;
				int cs_Oil_Size = csType_oil_statistics.size();
				int cs_Water_Size = csType_water_statistics.size();
				int cs_Other_Size = csType_other_statistics.size();
				int allce_size = cs_Oil_Size + cs_Water_Size + cs_Other_Size;
				int site_Size = site_statistics.size();
				int index = 0 ;//虚幻计数
				Double gongQuWellCountByTypeCount = (double) 0;//统计某 
				Double gongQuProdCountBy = (double) 0 ;
				int xmWellSum  = 0 ;//工区下 井求和
				//某工区下面的测试类型
        		for (Map.Entry<String, Object> cs_entry:site_map.entrySet()) {
        			  
        			String cs_id = cs_entry.getKey();
        			Map<String,Object> csjh_tmp_map = (LinkedHashMap<String, Object>) cs_entry.getValue();
        			ReportCSJH reportCDJH = null;
        			HSSFRow row1 = null;
        			Double csRiZengOilCount = (double) 0;	//某措施的日增有
        			
        			//System.out.println(JSON.toJSONString(csjh_tmp_map));
        			if(well_type_flag.equals(oilWellFlag)) {  //油井数据
	        				newWelllist = (List<ReportCSJH>) csjh_tmp_map.get("newWell");
							oldWelllist = (List<ReportCSJH>) csjh_tmp_map.get("oldWell");
							otherWellList = (List<ReportCSJH>) csjh_tmp_map.get("otherWell");
	
							String newWellNameStrCon = __statisticsList(newWelllist);
							String oldWellNameStrCon = __statisticsList(oldWelllist);
							String otherWellNameStrCon = __statisticsList(otherWellList);
							String allWellNameStrCon = "";
							
							allWellNameStrCon = newWellNameStrCon+oldWellNameStrCon+otherWellNameStrCon;
							// 日增油统计
							csRiZengOilCount = __dataListRiZengOilCount(newWelllist,csRiZengOilCount);
							csRiZengOilCount = __dataListRiZengOilCount(oldWelllist,csRiZengOilCount);
							csRiZengOilCount = __dataListRiZengOilCount(otherWellList,csRiZengOilCount);
							gongQuWellCountByTypeCount = gongQuWellCountByTypeCount + csRiZengOilCount;
							
							HSSFRichTextString ts= __makeHSSFRichTextString(workbook,newWelllist,oldWelllist,otherWellList,
									  									  newWellNameStrCon,oldWellNameStrCon,otherWellNameStrCon);
							row1 = sheet.getRow(rowNum++);
						    if(ts != null && row1 != null) {
							    	HSSFCell call1 = row1.createCell(colNum);//井名称
							    	call1.setCellValue(ts); 
							    	call1.setCellStyle(style2);
							    	HSSFCell call2 = row1.createCell(colNum+1);//日增油
							    	if(csRiZengOilCount == (double)0 || csRiZengOilCount == null) {
							    		call2.setCellValue("");
							    	}else {
							    		call2.setCellValue(csRiZengOilCount);
							    	}
							    	call2.setCellStyle(style2);
						    }
						    int inerSum = newWelllist.size()+oldWelllist.size()+otherWellList.size();
						    xmWellSum += inerSum ;
						    //System.out.println(inerSum+"=="+gongQuWellSum);
						    if((index+1) == site_map.size()) { //油井小计
							    	//System.out.println(index+"oil>>>>>>"+rowNum +"=="+ colNum);
							    	row1 = sheet.getRow(rowNum);
							    	HSSFCell call1 = row1.createCell(colNum);
							    	call1.setCellType(HSSFCell.CELL_TYPE_FORMULA);
							    	call1.setCellValue(xmWellSum);	
							    	call1.setCellStyle(style2);
							    	HSSFCell call2 = row1.createCell(colNum+1);
							    	call2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
							    	//call1.setCellValue(xmWellSum);	
							    	call2.setCellStyle(style2);
							    	
							    	int startNum = rowNum  - site_map.size() ;
							    	int endNum = rowNum - 1 ;
							    	int startCol  = colNum+1 ;
							    	int endCol  = colNum+1 ;
							    	call2.setCellFormula("SUM("+__num2ZiMu(startCol)+startNum+":"+__num2ZiMu(endCol)+endNum+")");
							    	call2.setCellStyle(style2);
						    	
						    }
        			}else if(well_type_flag.equals(waterWellFlag) ) {	//水井数据
	        				int oilRows = cs_Oil_Size +1 ;
	        				int oilcols = cs_Oil_Size +1 ;
	        				//colNum -= (site_Size * 2); //列修正
	        				newWelllist = (List<ReportCSJH>) csjh_tmp_map.get("newWell");
							oldWelllist = (List<ReportCSJH>) csjh_tmp_map.get("oldWell");
							otherWellList = (List<ReportCSJH>) csjh_tmp_map.get("otherWell");
							// 油井名拼接
							String newWellNameStrCon = __statisticsList(newWelllist);
				 			String oldWellNameStrCon = __statisticsList(oldWelllist);
							String otherWellNameStrCon = __statisticsList(otherWellList);
							String allWellNameStrCon = "";
							allWellNameStrCon = newWellNameStrCon+oldWellNameStrCon+otherWellNameStrCon;
							// 日增油统计
							csRiZengOilCount = __dataListRiZengOilCount(newWelllist,csRiZengOilCount);
							csRiZengOilCount = __dataListRiZengOilCount(oldWelllist,csRiZengOilCount);
							csRiZengOilCount = __dataListRiZengOilCount(otherWellList,csRiZengOilCount);
							gongQuWellCountByTypeCount = gongQuWellCountByTypeCount + csRiZengOilCount;
							
							HSSFRichTextString ts= __makeHSSFRichTextString(workbook,newWelllist,oldWelllist,otherWellList,
																		  newWellNameStrCon,oldWellNameStrCon,otherWellNameStrCon);
	
						    row1 = sheet.getRow(oilRows+rowNum++);	//修正 插入  二次插入到 油井下面
						    if(ts != null && row1 != null) {
							    	HSSFCell call1 = row1.createCell(colNum);// 某 工区 某措施下 所有井的名字  
							    	call1.setCellValue(ts);	// allWellNameStrCon
							    	call1.setCellStyle(style2);
							    	HSSFCell call2 = row1.createCell(colNum+1);//某 工区 某措施下  水井井的数量
							    	if(csRiZengOilCount == (double)0 || csRiZengOilCount == null) {
							    		call2.setCellValue("");
							    	}else {
							    		call2.setCellValue(csRiZengOilCount);
							    	}
							    	call2.setCellStyle(style2);
						    }
						    int inerSum = newWelllist.size()+oldWelllist.size()+otherWellList.size();
						    xmWellSum += inerSum ;
						    //System.out.println(inerSum+"=="+xmWellSum);
						    if(index == site_map.size()-1  ) {  //水井小计  csjh_tmp_map
							    	//System.out.println(index+">>>>>>"+rowNum +"=="+ colNum);
							    	row1 = sheet.getRow(oilRows+rowNum);
							    	
							    	HSSFCell call1 = row1.createCell(colNum); 
							    	call1.setCellValue(xmWellSum);	
							    	call1.setCellStyle(style2);
							    	HSSFCell call2 = row1.createCell(colNum+1);
							    	call2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
							    	int startNum = oilRows+rowNum  - site_map.size() + 1  ;
							    	int endNum = oilRows+rowNum ;
							    	int startCol  = colNum+1 ;
							    	int endCol  = colNum+1 ;
							    	call2.setCellFormula("SUM("+__num2ZiMu(startCol)+startNum+":"+__num2ZiMu(endCol)+endNum+")");
							    	call2.setCellStyle(style2);
						    }
        			}else {		//其他井类型
        					// 除  油井  水井  以外的井不做处理
        			}
        			index++;
        			
        		}
        		colNum += 2; //列向后跳两列
        	}
        }
	}

	/**
	 * 统计新井  老井
	 * @param oilWellFlag
	 * @param waterWellFlag
	 * @param site_statistics
	 * @param csType_oil_statistics
	 * @param csType_water_statistics
	 * @param csType_other_statistics
	 * @param cdjhData_statistics
	 * @param workbook
	 * @param sheet
	 * @param style2
	 */
	public void __makeTongJiOilWell(   String oilWellFlag, 
									   String waterWellFlag, 
									   Map<String, Object> site_statistics,
									   Map<String, Object> csType_oil_statistics, 
									   Map<String, Object> csType_water_statistics,
									   Map<String, Object> csType_other_statistics, 
									   Map<String, Object> cdjhData_statistics, //拼装好的数据
									   HSSFWorkbook workbook,HSSFSheet sheet, HSSFCellStyle style2) {
		
			int site_Size = site_statistics.size();
			int cs_Oil_Size = csType_oil_statistics.size();
			int cs_Water_Size = csType_water_statistics.size();
			int cs_Other_Size = csType_other_statistics.size();
			
			List<ReportCSJH> newWelllist = null;
			List<ReportCSJH> oldWelllist = null;
			List<ReportCSJH> otherWellList = null;
			
			Map<String,Object> countWellNumCount = new HashMap<String,Object>();
			Map<String,Object> newWellNumCount = new HashMap<String,Object>();
			Map<String,Object> oldWellNumCount = new HashMap<String,Object>();
			Map<String,Object> otherWellNumCount = new HashMap<String,Object>();
			//----->>>>统计数据
			
			// 各个项目类型    油井  水井  
			for (Map.Entry<String, Object> xms_entry:cdjhData_statistics.entrySet()) { 
					String well_type_flag = xms_entry.getKey();
					Map<String,Object> xm_map  =  (LinkedHashMap<String, Object>) xms_entry.getValue();
					//各个工区
					for (Map.Entry<String, Object> site_entry:xm_map.entrySet()) {  
							String site_id = site_entry.getKey();
			        		Map<String,Object> site_map  =  (LinkedHashMap<String, Object>) site_entry.getValue();
			        		//措施类型 1 2 3  
			        		for (Map.Entry<String, Object> cs_entry:site_map.entrySet()) {
			        			String cs_id = cs_entry.getKey();
			        			Map<String,Object> csjh_tmp_map = (LinkedHashMap<String, Object>) cs_entry.getValue();
			        			
		        				newWelllist = (List<ReportCSJH>) csjh_tmp_map.get("newWell");
								oldWelllist = (List<ReportCSJH>) csjh_tmp_map.get("oldWell");
								otherWellList = (List<ReportCSJH>) csjh_tmp_map.get("otherWell");
								String key = well_type_flag + "-" + cs_id;
								if(!countWellNumCount.containsKey(key)) {
									countWellNumCount.put(key, newWelllist.size()+oldWelllist.size()+otherWellList.size());
								}else {
									int tmp = ( (int)newWellNumCount.get(key) + newWelllist.size()+oldWelllist.size()+otherWellList.size() );
									countWellNumCount.put(key, tmp);
								}
								
								if(!newWellNumCount.containsKey(key)) {
									newWellNumCount.put(key, newWelllist.size());
								}else {
									int tmp =  (int)newWellNumCount.get(key) + newWelllist.size();
									newWellNumCount.put(key, tmp);
								}
								
								if(!oldWellNumCount.containsKey(key)) {
									oldWellNumCount.put(key, oldWelllist.size());
								}else {
									int tmp =  (int)oldWellNumCount.get(key) + oldWelllist.size();
									oldWellNumCount.put(key, tmp);
								}
								
								if(!otherWellNumCount.containsKey(key)) {  
									otherWellNumCount.put(key, otherWellList.size());
								}else {
									int tmp =  (int)otherWellNumCount.get(key) + otherWellList.size();
									otherWellNumCount.put(key, tmp);
								}
									
			        		}
					}
			}
			//System.out.println(JSON.toJSONString(countWellNumCount));
			//System.out.println(JSON.toJSONString(newWellNumCount));
			//System.out.println(JSON.toJSONString(oldWellNumCount));
			//System.out.println(JSON.toJSONString(otherWellNumCount));
			//  开始为右侧的合计部分插入数据
			int runCount = cs_Oil_Size + cs_Water_Size + cs_Other_Size;
			int rowNum = 4 ;
			int bseRow = 4;
			int colNum = 2 ; 
			
			// 各个项目类型    油井  水井  
			for (Map.Entry<String, Object> xms_entry:cdjhData_statistics.entrySet()) {
					int index = 1;
					String well_type_flag = xms_entry.getKey();
					Map<String,Object> xm_map  =  (LinkedHashMap<String, Object>) xms_entry.getValue();
					//各个工区
					for (Map.Entry<String, Object> site_entry:xm_map.entrySet()) {  
							String site_id = site_entry.getKey();
			        		Map<String,Object> site_map  =  (LinkedHashMap<String, Object>) site_entry.getValue();
			        		//措施类型 1 2 3  
			        		for (Map.Entry<String, Object> cs_entry:site_map.entrySet()) {
			        			String cs_id = cs_entry.getKey();
			        			Map<String,Object> csjh_tmp_map = (LinkedHashMap<String, Object>) cs_entry.getValue();
			        			if(well_type_flag.equals(oilWellFlag)) {
			        				HSSFRow row = sheet.getRow(rowNum);
			        				HSSFCell cell1 = row.createCell(colNum + site_Size * 2);
			        				HSSFCell cell2 = row.createCell(colNum + site_Size * 2 + 1);
			        				HSSFCell cell3 = row.createCell(colNum + site_Size * 2 + 2);
			        				HSSFCell cell4 = row.createCell(colNum + site_Size * 2 + 3);
			        				cell1.setCellValue((int)newWellNumCount.get(well_type_flag+"-"+cs_id)+(int)oldWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell3.setCellValue((int)newWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell4.setCellValue((int)oldWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell2.setCellValue((int)countWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell1.setCellStyle(style2);
			        				cell2.setCellStyle(style2);
			        				if(index == cs_Oil_Size) {
			        					HSSFRow row_1 = sheet.getRow(rowNum+1);
			        					String jingShuStrFun =  __conCollAddressAddList(colNum,index+bseRow+1,site_Size,2);
			        					String heJiStrFun    =  __conCollAddressAddList(colNum+1,index+bseRow+1,site_Size,2);
			        					String newheJiStrFun    =  __conRowAddressAddList(rowNum-cs_Oil_Size+2,colNum + site_Size * 2 + 2,cs_Oil_Size,1);
			        					String oldheJiStrFun    =  __conRowAddressAddList(rowNum-cs_Oil_Size+2,colNum + site_Size * 2 + 2,cs_Oil_Size,1);
			        					HSSFCell cell_1 = row_1.createCell(colNum + site_Size * 2); 
			        					HSSFCell cell_2 = row_1.createCell(colNum + site_Size * 2 + 1); 
			        					HSSFCell cell_3 = row_1.createCell(colNum + site_Size * 2 + 2); 
			        					HSSFCell cell_4 = row_1.createCell(colNum + site_Size * 2 + 3); 
			        					cell_1.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_3.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_4.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_1.setCellFormula(jingShuStrFun);
			        					cell_2.setCellFormula(heJiStrFun);
			        					cell_3.setCellFormula(newheJiStrFun);
			        					cell_4.setCellFormula(oldheJiStrFun);
			        					cell_1.setCellStyle(style2);
			        					cell_2.setCellStyle(style2);
			        					cell_3.setCellStyle(style2);
			        					cell_4.setCellStyle(style2);
			        					rowNum++;
			        				}
			        			}else if(well_type_flag.equals(waterWellFlag)){
			        				HSSFRow row = sheet.getRow(rowNum);
			        				HSSFCell cell1 = row.createCell(colNum + site_Size * 2);
			        				HSSFCell cell2 = row.createCell(colNum + site_Size * 2 + 1);
			        				HSSFCell cell3 = row.createCell(colNum + site_Size * 2 + 2);
			        				HSSFCell cell4 = row.createCell(colNum + site_Size * 2 + 3);
			        				cell1.setCellValue((int)newWellNumCount.get(well_type_flag+"-"+cs_id)+(int)oldWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell3.setCellValue((int)newWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell4.setCellValue((int)oldWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell2.setCellValue((int)countWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell1.setCellStyle(style2);
			        				cell2.setCellStyle(style2);
			        				if(index == cs_Water_Size) {
			        					HSSFRow row_1 = sheet.getRow(rowNum+1);
			        					String jingShuStrFun =  __conCollAddressAddList(colNum,index+bseRow+cs_Oil_Size+2,site_Size,2);
			        					String heJiStrFun    =  __conCollAddressAddList(colNum+1,index+bseRow+cs_Oil_Size+2,site_Size,2);
			        					String newheJiStrFun =  __conRowAddressAddList(rowNum-cs_Water_Size+2,colNum + site_Size * 2 + 2,cs_Water_Size,1);
			        					String oldheJiStrFun =  __conRowAddressAddList(rowNum-cs_Water_Size+2,colNum + site_Size * 2 + 3,cs_Water_Size,1);
			        					HSSFCell cell_1 = row_1.createCell(colNum + site_Size * 2); 
			        					HSSFCell cell_2 = row_1.createCell(colNum + site_Size * 2 + 1); 
			        					HSSFCell cell_3 = row_1.createCell(colNum + site_Size * 2 + 2); 
			        					HSSFCell cell_4 = row_1.createCell(colNum + site_Size * 2 + 3);
			        					cell_1.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_3.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_4.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_1.setCellFormula(jingShuStrFun);
			        					cell_2.setCellFormula(heJiStrFun);
			        					cell_3.setCellFormula(newheJiStrFun);
			        					cell_4.setCellFormula(oldheJiStrFun);
			        					cell_1.setCellStyle(style2);
			        					cell_2.setCellStyle(style2);
			        					rowNum++;
			        				}
			        			}else {
			        				HSSFRow row = sheet.getRow(rowNum);
			        				HSSFCell cell1 = row.createCell(colNum + site_Size * 2);
			        				HSSFCell cell2 = row.createCell(colNum + site_Size * 2 + 1);
			        				HSSFCell cell3 = row.createCell(colNum + site_Size * 2 + 2);
			        				HSSFCell cell4 = row.createCell(colNum + site_Size * 2 + 3);
			        				cell1.setCellValue((int)newWellNumCount.get(well_type_flag+"-"+cs_id)+(int)oldWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell3.setCellValue((int)newWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell4.setCellValue((int)oldWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell2.setCellValue((int)countWellNumCount.get(well_type_flag+"-"+cs_id));
			        				cell1.setCellStyle(style2);
			        				cell2.setCellStyle(style2);
			        				if(index == cs_Water_Size) {
			        					HSSFRow row_1 = sheet.getRow(rowNum+1);
			        					String jingShuStrFun =  __conCollAddressAddList(colNum,index+1,site_Size,2);
			        					String heJiStrFun    =  __conCollAddressAddList(colNum+1,index+1,site_Size,2);
			        					String newheJiStrFun =  __conRowAddressAddList(rowNum-runCount,colNum+2,cs_Other_Size,1);
			        					String oldheJiStrFun =  __conRowAddressAddList(rowNum-runCount,colNum+3,cs_Other_Size,1);
			        					HSSFCell cell_1 = row_1.createCell(colNum + site_Size * 2); 
			        					HSSFCell cell_2 = row_1.createCell(colNum + site_Size * 2 + 1); 
			        					HSSFCell cell_3 = row_1.createCell(colNum + site_Size * 2 + 2); 
			        					HSSFCell cell_4 = row_1.createCell(colNum + site_Size * 2 + 3); 
			        					cell_1.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_3.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_4.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			        					cell_1.setCellFormula(jingShuStrFun);
			        					cell_2.setCellFormula(heJiStrFun);
			        					cell_3.setCellFormula(newheJiStrFun);
			        					cell_4.setCellFormula(oldheJiStrFun);
			        					cell_1.setCellStyle(style2);
			        					cell_2.setCellStyle(style2);
			        					rowNum++;
			        				}
			        			}
			        			rowNum++;
			        			index++;
			        		}
			        		break;//只跑一次  一个工区
					}
			}
			
			
			
	}
	
	/**
	 * 
	 * @param colStart  开始列
	 * @param row		行固定
	 * @param length	几次
	 * @param step		步长  每次增加多少?
	 * @return
	 */
	public String __conCollAddressAddList(int colStart,int row,int length,int step) {
		String ret = "";
		int index = colStart ;
		for(int i= 0;i<length;i++) {
			if(i == 0) {
				ret += __num2ZiMu(index) + row ;
			}else {
				ret += "+"+__num2ZiMu(index) + row ;
			}
			index += step ;
		}
		return ret;
	}

	/**
	 *  一列求和
	 * @param rowStart
	 * @param coL  固定
	 * @param length
	 * @param step
	 * @return
	 */
	public String __conRowAddressAddList(int rowStart,int coL,int length,int step) {
		String ret = "";
		int index = rowStart ;
		for(int i= 0;i<length;i++) {
			if(i == 0) {
				ret += __num2ZiMu(coL) + index ;
			}else {
				ret += "+"+__num2ZiMu(coL) + index ;
			}
			index += step ;
		}
		return ret;
	}
	
	
	
	/**
	 * 开始  表的【左侧头】    添加油水  井 和     各种措施类型
	 * @param oilWellFlag
	 * @param waterWellFlag
	 * @param csType_oil_statistics
	 * @param csType_water_statistics
	 * @param csType_other_statistics
	 * @param cdjhData_statistics
	 * @param sheet
	 * @param style
	 * @param style2
	 * @param style3
	 */
	public void __makeBiaoZuoCeTou(String oilWellFlag, String waterWellFlag, Map<String, Object> csType_oil_statistics,
								   Map<String, Object> csType_water_statistics, Map<String, Object> csType_other_statistics,
								   Map<String, Object> cdjhData_statistics, HSSFSheet sheet, HSSFCellStyle style, HSSFCellStyle style2,
								   HSSFCellStyle style3) {
		int rowNum;
		rowNum = 4;
        //开始添加
        for (Map.Entry<String, Object> xms_entry:cdjhData_statistics.entrySet()) { //获取项目类型  油井  水井  其他类型    开始遍历
        	String well_type_flag = xms_entry.getKey();
        	Map<String,Object> xm_map  =  (LinkedHashMap<String, Object>) xms_entry.getValue();
        	
        	for (Map.Entry<String, Object> site_entry:xm_map.entrySet()) { //该项目下所有的 工区  
        		String site_id = site_entry.getKey();
        		Map<String,Object> site_map  =  (LinkedHashMap<String, Object>) site_entry.getValue();
        		String xiaojiCellName = "小计";
        		
        		int rowIndex = 0;
        		int oilIndex = 0;
        		int waterIndex = 0;
        		int otherIndex = 0;
        		for (Map.Entry<String, Object> cs_entry:site_map.entrySet()) {// 项目-工区 --》》所有的测试类型
        			rowIndex++; 
        			String cs_id = cs_entry.getKey();
        			ReportCSJH reportCDJH = null;
        			int csSize = 0;
        			HSSFRow row1 = null;
        			int csRowHeight = 1500; //单元格行高设置
        			int  xiaojiRowHeight = 300;//小计的行高
        			if(well_type_flag.equals(oilWellFlag)) {
        				oilIndex++;  //油井计数
        				String xmName = "油井";
        				reportCDJH = (ReportCSJH) csType_oil_statistics.get(cs_id);
            			csSize = csType_oil_statistics.size();
            			row1 = sheet.createRow(rowNum++);
            			row1.setHeight((short)csRowHeight);
            			if(reportCDJH.getCs_name() != null) {
            				HSSFCell call = row1.createCell(0);
            				call.setCellValue(xmName);
            				call.setCellStyle(style);
            				HSSFCell call2 =row1.createCell(1);
            				call2.setCellValue(reportCDJH.getCs_name());
            				call2.setCellStyle(style2);
            			}
            			
            			if(rowIndex == csSize) {	//每个油井类型  最后添加一个小计
            				row1 = sheet.createRow(rowNum++);
            				row1.setHeight((short)xiaojiRowHeight);
            				HSSFCell call1 = row1.createCell(1);
            				call1.setCellValue(xiaojiCellName);
            				call1.setCellStyle(style2);
            				sheet.addMergedRegion(new CellRangeAddress(rowNum-csSize-1, rowNum-1, 0, 0));
            			}
        			}else if(well_type_flag.equals(waterWellFlag)) {
        				waterIndex++; // 水井计数
        				String xmName = "水井";
            			reportCDJH = (ReportCSJH) csType_water_statistics.get(cs_id);
            			csSize = csType_water_statistics.size();
            			row1 = sheet.createRow(rowNum++);
            			row1.setHeight((short)csRowHeight);
            			if(reportCDJH.getCs_name() != null) {
            				HSSFCell call = row1.createCell(0);
            				call.setCellValue(xmName);
            				call.setCellStyle(style);
            				HSSFCell call2 =row1.createCell(1);
            				call2.setCellValue(reportCDJH.getCs_name());
            				call2.setCellStyle(style2);
            			}
            			if(rowIndex == csSize) {	//每个油井类型  最后添加一个小计
            				row1 = sheet.createRow(rowNum++);
            				row1.setHeight((short)xiaojiRowHeight);
            				HSSFCell call1 = row1.createCell(1);
            				call1.setCellValue(xiaojiCellName);
            				call1.setCellStyle(style2);
            				sheet.addMergedRegion(new CellRangeAddress(rowNum-csSize-1, rowNum-1, 0, 0));
            			}
        			}else {
        				otherIndex++;//其他井计数
        				String xmName = "其他";
        				if(csType_other_statistics != null  ) {
        					reportCDJH = (ReportCSJH) csType_other_statistics.get(cs_id);
                			csSize = csType_other_statistics.size();
        				}
            			row1 = sheet.createRow(rowNum++);
            			row1.setHeight((short)csRowHeight);
            			if(reportCDJH != null && reportCDJH.getCs_name() != null) {
            				HSSFCell call = row1.createCell(0);
            				call.setCellValue(xmName);
            				call.setCellStyle(style);
            				HSSFCell call2 =row1.createCell(1);
            				call2.setCellValue(reportCDJH.getCs_name());
            				call2.setCellStyle(style2);
            			}
            			
            			if(rowIndex == csSize) {	//每个油井类型  最后添加一个小计
            				row1 = sheet.createRow(rowNum++);
            				row1.setHeight((short)xiaojiRowHeight);
            				HSSFCell call1 = row1.createCell(1);
            				call1.setCellValue(xiaojiCellName);
            				call1.setCellStyle(style2);
            				sheet.addMergedRegion(new CellRangeAddress(rowNum-csSize-1, rowNum-1, 0, 0));
            			}
        			}
        			/// 【【【【【【第四步】】】】】
        			//最后添加合计
        			__funAddSumEnd(csType_oil_statistics, csType_water_statistics, csType_other_statistics, style,style2,style3,sheet,rowNum);
                  
        		}
        		break;	//只遍历一个工区  为首列的  设置   测试类型名字
        	}
        }
	}

	
	/**
	 *    添加表头信息
	 * @param site_statistics
	 * @param sheet
	 * @param style2
	 * @param title2
	 * @param row2
	 * @param row3
	 */
	public void __makeBiaoTou(List<Site> sitedatalist,Map<String, Object> site_statistics, HSSFSheet sheet, HSSFCellStyle style2,
			String[] title2, HSSFRow row2, HSSFRow row3) {
		int tmpindex = 2;
        int tmpindex2 = 2;
        /////////////////////    合并  单元工区  单元格
        for(Entry<String, Object> entry:site_statistics.entrySet()){
        	sheet.addMergedRegion(new CellRangeAddress(2, 2, tmpindex2, tmpindex2+1));
        	tmpindex2+=2;
        }
        
        sheet.setColumnWidth(0, 20*150);			//列宽
    	sheet.setColumnWidth(1, 35*150);
    	sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 0)); //合计合并   项目
    	sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 1)); //合计合并   措施
    	for (Site site: sitedatalist) {
    		
        //for(Entry<String, Object> entry:site_statistics.entrySet()){
        	//Map<String,Object> map1 = Util.objectToMap(entry.getValue());
        	
        	sheet.setColumnWidth(tmpindex, 35*150);			//列宽
        	sheet.setColumnWidth(tmpindex+1, 15*150);
        	HSSFCell cellSite11 = row2.createCell(tmpindex);	//工区左
        	HSSFCell cellSite12 = row2.createCell(tmpindex+1);	//工区右  为了添加样式  边框
        	cellSite11.setCellStyle(style2);
        	cellSite12.setCellStyle(style2);
        	cellSite11.setCellValue(site.getSite_name());	//工区
        	//cellSite11.setCellValue(map1.get("site_name").toString());	//工区
        	//cellSite12.setCellValue("");	//工区
        	sheet.addMergedRegion(new CellRangeAddress(2, 2, tmpindex2, tmpindex2+1));
        	
        	HSSFCell cellSite21 = row3.createCell(tmpindex);	// 油井
        	cellSite21.setCellValue(title2[0]);
        	cellSite21.setCellStyle(style2);
        	HSSFCell cellSite22 = row3.createCell(tmpindex+1);	// 油井
        	cellSite22.setCellValue(title2[1]);//产油量
        	cellSite22.setCellStyle(style2);
        	
        	tmpindex+=2;
        }
        sheet.setColumnWidth(tmpindex, 35*150);
    	sheet.setColumnWidth(tmpindex+1, 15*150);
    	sheet.addMergedRegion(new CellRangeAddress(2, 2, tmpindex, tmpindex+1)); //合计合并
    	HSSFCell cellSite31 = row2.createCell(tmpindex);
    	cellSite31.setCellValue("合计");
    	cellSite31.setCellStyle(style2);
    	HSSFCell cellSite41 = row3.createCell(tmpindex);  //  合计  油井数
    	cellSite41.setCellValue("井数");
    	cellSite41.setCellStyle(style2);
    	HSSFCell cellSite42 = row3.createCell(tmpindex+1);// 合计 增油数
    	cellSite42.setCellValue(title2[1]);
    	cellSite42.setCellStyle(style2);
    	
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tmpindex+1)); //合计  油井 合并
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, tmpindex+1)); //合计 增油 合并
	}
	
	
	
	/**
	 * 左后的合计
	 * 为合计的每个工区添加  excel公式  自动求和
	 * row 在添加的项目的 都已经创建了  只需要获取
	 * 
	 * @param sheet
	 * @param site_statistics
	 * @param csType_oil_statistics
	 * @param csType_water_statistics
	 * @param csType_other_statistics
	 * @param wellType_statistics
	 */
	public void __addHeJiSumFunction(HSSFSheet sheet,
									 Map<String, Object> site_statistics,
									 Map<String, Object> csType_oil_statistics, 
									 Map<String, Object> csType_water_statistics,
									 Map<String, Object> csType_other_statistics, 
									 Map<String, Object> wellType_statistics,
									 HSSFCellStyle style1,
									 HSSFCellStyle style2,
									 HSSFCellStyle style3) {
		int  baseRow = 4;   //测试的数据区域是   4行开始计算
		int baseCol = 2; //合计的公式从第二列开始添加 
		
		int oilCSLength   = csType_oil_statistics.size();
		int waterCSLength = csType_water_statistics.size();
		int otherCSLength = csType_other_statistics.size();
		int siteLength 	  = site_statistics.size();
		
		if(otherCSLength == 0) {
			if(oilCSLength != 0 && waterCSLength != 0) {
				oilCSLength += 1;
				waterCSLength += 1;
			}
			
		}else {
			
		}
		
		int actionRowNum = baseRow + oilCSLength + waterCSLength;
		/**
		 *  必须注意   row之前没有创建      重复创建后面的会把前面的覆盖导致  无法找到    数据不显示的元婴
		 */
		HSSFRow sumRow =  sheet.createRow( actionRowNum );
		int colLogIndex = 0; //每个工区有  两个字段需要求和  7个工区 7*2 + 2  16个合计
		for(int i=0;i<= siteLength;i++) {
			
			HSSFCell call1 = sumRow.createCell(baseCol + colLogIndex); 
			HSSFCell call2 = sumRow.createCell(baseCol + colLogIndex+1); 
			call1.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			call2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			int startRow = baseRow + oilCSLength;
			int endRow = baseRow + oilCSLength+waterCSLength;
			String colStr1 = __num2ZiMu(baseCol + colLogIndex); 
			String colStr2 = __num2ZiMu(baseCol + colLogIndex + 1 );
			String funStr1 = colStr1+startRow+"+"+colStr1+endRow;
			String funStr2 = colStr2+startRow+"+"+colStr2+endRow;
	    	call1.setCellFormula(funStr1);
	    	call2.setCellFormula(funStr2);
	    	call1.setCellStyle(style2); 
	    	call2.setCellStyle(style2); 
	    	if(i==siteLength) {//最后的  心井  老井求和
	    		call1 = sumRow.createCell(baseCol + colLogIndex+2); 
				call2 = sumRow.createCell(baseCol + colLogIndex+3); 
				call1.setCellType(HSSFCell.CELL_TYPE_FORMULA);
				call2.setCellType(HSSFCell.CELL_TYPE_FORMULA);
				startRow = baseRow + oilCSLength;
				endRow = baseRow + oilCSLength+waterCSLength;
				colStr1 = __num2ZiMu(baseCol + colLogIndex + 2); 
				colStr2 = __num2ZiMu(baseCol + colLogIndex + 3 );
				funStr1 = colStr1+startRow+"+"+colStr1+endRow;
				funStr2 = colStr2+startRow+"+"+colStr2+endRow;
		    	call1.setCellFormula(funStr1);
		    	call2.setCellFormula(funStr2);
	    	}
			colLogIndex += 2;
		}
		
		HSSFRow sumRow2 =  sheet.createRow( actionRowNum + 1 );
		int coltmp = baseCol + siteLength * 2 ;
		HSSFCell tianBiaoRen = sumRow2.createCell(coltmp); //base开始数  2*工区数  偏移一个
		HSSFCell shuoMing = sumRow2.createCell(0); //base开始数  2*工区数  偏移一个
		SimpleDateFormat formetter = new SimpleDateFormat();
		
		String tianBiaoRenStr = "制表日期：" + formetter.format(new Date());
		tianBiaoRen.setCellValue(tianBiaoRenStr);
		shuoMing.setCellValue("");
		tianBiaoRen.setCellStyle(style2);
		sheet.addMergedRegion(new CellRangeAddress(actionRowNum + 1, actionRowNum + 1, coltmp, coltmp+1));
	}
	
	
	
	/**
	 * 将字母转为  EXCEL的  abc列名    
	 * 从开始计数
	 * 传入的  0 1 2 3 4 5
	 * 返回
	 * a b c d e 
	 * @param num
	 * @return
	 */
	public String __num2ZiMu(int num) {
		num++;
		String ret = "";
		if(num >= 1  && num <= 26) {
			ret += (char)(num + 64);
		}else {
			int tou = num % 26 ;  //前面拼接
			int yu = num / 26 ;
			if(tou >= 1) {
				ret += (char)(tou + 64);
			}
			ret += (char)(yu + 64);
		}
		return ret;
	}
	
	
	
	/**
	 * 
	 * 根据新井 老井  其他井    为合并后的井  添加逗号和样式
	 * 
	 * @param workbook
	 * @param newWellList
	 * @param oldWellList
	 * @param otherWellList
	 * @param newWellNameStrCon
	 * @param oldWellNameStrCon
	 * @param otherWellNameStrCon
	 * @return
	 */
	public HSSFRichTextString __makeHSSFRichTextString(HSSFWorkbook workbook,
													List<ReportCSJH> newWellList,
													List<ReportCSJH> oldWellList,
													List<ReportCSJH> otherWellList,
													String newWellNameStrCon,
													String oldWellNameStrCon,
													String otherWellNameStrCon
													) {
		HSSFRichTextString ts = null;
		
		//黑色下滑线  字体
        Font font = workbook.createFont();  
	    font.setFontHeightInPoints((short) 12); // 字体高度  
	    font.setFontName("宋体"); // 字体 
	    font.setColor(HSSFColor.BLACK.index);  //颜色
	    font.setUnderline(Font.U_SINGLE);
	    //红色字体
	    Font font2 = workbook.createFont();  
	    font2.setFontHeightInPoints((short) 12); // 字体高度  
	    font2.setFontName("宋体"); // 字体  
	    font2.setColor(HSSFColor.RED.index);  //颜色
	    
	    int newlength = 0;
	    int oldlength = 0;
	    int otherlength = 0;
	    
	    if(newWellList != null) {
	    	newlength = newWellList.size();
	    }
	    if(oldWellList != null) {
	    	oldlength = oldWellList.size();
	    }
	    if(otherWellList != null) {
	    	otherlength = otherWellList.size();
	    }
	    //  根据ArrayList的长度    添加顿号  处理样式   
	    int newStrlength = newWellNameStrCon.length();
	    int oldStrlength = oldWellNameStrCon.length();
	    int otherStrlength = otherWellNameStrCon.length();
	    
	    if(otherlength == 0) {
	    	if(newStrlength != 0 && oldStrlength != 0) {
	    		newWellNameStrCon = newWellNameStrCon + "、";
	    		newStrlength++;
	    	}else {
	    	}
	    }else {
	    	if(newStrlength != 0 && oldStrlength != 0) {
	    		newWellNameStrCon = newWellNameStrCon + "、";
	    		newStrlength++;
	    		oldWellNameStrCon = oldWellNameStrCon + "、";
	    		oldStrlength++;
	    	}else {
	    	}
	    }
	    
	    String sumStr = newWellNameStrCon + oldWellNameStrCon + otherWellNameStrCon ;
		ts= new HSSFRichTextString(sumStr); //单元
	    ts.applyFont(0,newStrlength,font);  			
	    ts.applyFont(newStrlength,(newStrlength+oldStrlength),font2); 
	    //System.out.println(newStrlength+"=="+(newStrlength+oldStrlength)+"=="+sumStr);
		return ts;
	}
	
	
	
	/**
	 * 分析  wellList  拼接数组
	 * @param dataList	要遍历 分析的数据	
	 * @param Length	字符的长度
	 * @param contStr	拼接的字符
	 * @param csRiZengOilCount 
	 */
	public String __statisticsList(List<ReportCSJH> dataList) {
		String contStr = "";
		if(dataList != null) {
			for (int i=0;i<dataList.size();i++) {
				ReportCSJH reportCSJH = dataList.get(i);
				if(i==0 && reportCSJH != null) {
					contStr += reportCSJH.getWell_name();
					if( reportCSJH.getMark() != null) {
						contStr += "("+reportCSJH.getMark()+")";
					}
				}else {
					contStr += "、"+reportCSJH.getWell_name();
					if( reportCSJH.getMark() != null) {
						contStr += "("+reportCSJH.getMark()+")";
					}
				}
			}
			return contStr;
		}
		return null;
	}
	
	
	
	/**
	 *  日增油统计
	 * @param dataList
	 * @param csRiZengOilCount
	 * @return
	 */
	public Double __dataListRiZengOilCount(List<ReportCSJH> dataList,Double csRiZengOilCount) {
		if(dataList == null) {
			return csRiZengOilCount;
		}else {
			for (int i=0;i<dataList.size();i++) {
				ReportCSJH reportCSJH = dataList.get(i);
				if(reportCSJH != null && reportCSJH.getProd_daily() != null ) {
					csRiZengOilCount += __StringNumAddToDouble(csRiZengOilCount,reportCSJH.getProd_daily());
				}
			}
		}
		return csRiZengOilCount;
	}
	
	
	/**
	 * 
	 * @param dest  Double目标数组
	 * @param strNum string数字
	 * @return
	 */
	public Double __StringNumAddToDouble(Double dest,String strNum) {
		final String reg = "\\d+\\.{0.1}\\d*";  // && strNum.matches(reg)  没有正则匹配
		if(dest == null ) {
			return Double.parseDouble(strNum);
		}else {
			if(strNum != null  ) {
				dest += Double.parseDouble(strNum);
			}
			return dest;
		}
	}
	
	
	
	/**
	 * 方法作用  就是统计后台的数据       
	 * 
	 * @param datalist	统计的目标数据
	 * @param site_statistics			工区信息存放
	 * @param csType_oil_statistics		油井的测试类型
	 * @param csType_water_statistics	水井的测试类型
	 * @param csType_other_statistics	其他井的测试类型
	 * @param wellType_statistics		井类型信息存放  油井  水井 其他	
	 * @param oilWellFlag				油井的标识  
	 * @param waterWellFlag				水井的标识
	 * @return
	 */
	public Map<String, Object> __fenXiZuZhuangData(List<ReportCSJH> datalist, Map<String, Object> site_statistics,
												Map<String, Object> csType_oil_statistics, Map<String, Object> csType_water_statistics,
												Map<String, Object> csType_other_statistics, Map<String, Object> wellType_statistics, 
												String oilWellFlag,String waterWellFlag) {
		
			String oldWellType = "1";	//老井标识
			String newWellType = "0";  // 新井标识
		for (Iterator<ReportCSJH> iterator = datalist.iterator(); iterator.hasNext();) {	//遍历所有返回的数据结构
			ReportCSJH reportCSJH = (ReportCSJH) iterator.next();
			//获取所有的工区
			if(!site_statistics.containsKey(reportCSJH.getSite_id())  && (reportCSJH.getSite_id() != null) ) {
				site_statistics.put(reportCSJH.getSite_id(), reportCSJH);
			}
			
			//获取所有的油井测试类型：补孔
			if(!csType_oil_statistics.containsKey(reportCSJH.getWell_type_flag()+"-"+reportCSJH.getCs_id())  
													&&  (reportCSJH.getCs_id() != null) 
													&& reportCSJH.getWell_type_flag().equals(oilWellFlag)	) {
				csType_oil_statistics.put(reportCSJH.getWell_type_flag()+"-"+reportCSJH.getCs_id(), reportCSJH);
			}
			
			//获取所有的水井测试类型： 卡灰等
			if(!csType_water_statistics.containsKey(reportCSJH.getWell_type_flag()+"-"+reportCSJH.getCs_id())  
													&&  (reportCSJH.getCs_id() != null) 
													&& reportCSJH.getWell_type_flag().equals(waterWellFlag)	) {
				csType_water_statistics.put(reportCSJH.getWell_type_flag()+"-"+reportCSJH.getCs_id(), reportCSJH);
			}
			
			//获取所有的其他测试类型： 
			if(!csType_other_statistics.containsKey(reportCSJH.getWell_type_flag()+"-"+reportCSJH.getCs_id())  
													&&  (reportCSJH.getCs_id() != null) 
													&& !reportCSJH.getWell_type_flag().equals(oilWellFlag)
													&& !reportCSJH.getWell_type_flag().equals(waterWellFlag)) {
				csType_other_statistics.put(reportCSJH.getWell_type_flag()+"-"+reportCSJH.getCs_id(), reportCSJH);
			}
			//获取所有的井别 类型  ：油井，水井
			if(!wellType_statistics.containsKey(reportCSJH.getWell_type_flag())  &&  (reportCSJH.getWell_type_flag() != null) ) {
				wellType_statistics.put(reportCSJH.getWell_type_flag(), reportCSJH);
			}
		}    //所有的工区列表   所有的测试列表
		
//System.out.println(">>>"+JSON.toJSONString(site_statistics));
		
		////////////////[ 拼接组织结构  ] ////////////////////////////////////////
		//site_id list ==>> well_type map ==>> site_Tytpe map ==>> CS_type map ==>> CSLIST map
		Map<String,Object> cdjhData_statistics = new LinkedHashMap<String,Object>(); 
		 
		for (Map.Entry<String, Object> wellentry:wellType_statistics.entrySet()) {
			Map<String,Object> well_type_map = new LinkedHashMap<String,Object>();
			
			for (Map.Entry<String, Object> siteentry:site_statistics.entrySet()) {
				Map<String,Object> site_type_map = new LinkedHashMap<String,Object>();
				//
				if(wellentry.getKey().equals(oilWellFlag)) {
					for (Map.Entry<String, Object> cs_entry:csType_oil_statistics.entrySet()) { //油井所有的测试类型
						Map<String,Object> csjh_tmp_map = new LinkedHashMap<String,Object>();
						List<Object> newWelllist = new ArrayList<>();
						List<Object> oilWelllist = new ArrayList<>();
						List<Object> otherWell = new ArrayList<>();
						csjh_tmp_map.put("newWell", newWelllist);
						csjh_tmp_map.put("oldWell", oilWelllist);
						csjh_tmp_map.put("otherWell", otherWell);
						site_type_map.put(cs_entry.getKey(), csjh_tmp_map);	//
					}
				}
				//
				if(wellentry.getKey().equals(waterWellFlag)) {
					for (Map.Entry<String, Object> cs_entry:csType_water_statistics.entrySet()) { // 水井的所有测试类型
						Map<String,Object> csjh_tmp_map = new LinkedHashMap<String,Object>();
						List<Object> newWelllist = new ArrayList<>();
						List<Object> oilWelllist = new ArrayList<>();
						List<Object> otherWell = new ArrayList<>();
						csjh_tmp_map.put("newWell", newWelllist);
						csjh_tmp_map.put("oldWell", oilWelllist);
						csjh_tmp_map.put("otherWell", otherWell);
						site_type_map.put(cs_entry.getKey(), csjh_tmp_map);	//
					}
				}
				//
				if(	!wellentry.getKey().equals(oilWellFlag) && !wellentry.getKey().equals(waterWellFlag)) {
					for (Map.Entry<String, Object> cs_entry:csType_other_statistics.entrySet()) { // 其他测试类型
						Map<String,Object> csjh_tmp_map = new LinkedHashMap<String,Object>();
						List<Object> newWelllist = new ArrayList<>();
						List<Object> oilWelllist = new ArrayList<>();
						List<Object> otherWell = new ArrayList<>();
						csjh_tmp_map.put("newWell", newWelllist);
						csjh_tmp_map.put("oldWell", oilWelllist);
						csjh_tmp_map.put("otherWell", otherWell);
						site_type_map.put(cs_entry.getKey(), csjh_tmp_map);	//
					}
				}
				well_type_map.put(siteentry.getKey(), site_type_map);		//  well_type 二级  添加 site_map
			}
			
			cdjhData_statistics.put(wellentry.getKey(), well_type_map);	//cdjhData_statistics 一级  添加 well_type
		}
		
		////////////[  将对应的函数添加到    对应的map 中  
		for (ReportCSJH reportCSJH : datalist) {
			if(reportCSJH.getWell_type_flag() != null 
				&& reportCSJH.getSite_id()!= null 
				&& reportCSJH.getCs_id() !=null 
				&& reportCSJH != null) {
				
				Map<String,Object> wellTypeMap = 
						(Map<String, Object>) cdjhData_statistics.get(reportCSJH.getWell_type_flag());
				Map<String,Object> siteTypeMap = 
						(Map<String, Object>) wellTypeMap.get(reportCSJH.getSite_id());
				Map<String,Object> csjhTypeMap = 
						(Map<String, Object>) siteTypeMap.get(reportCSJH.getWell_type_flag()+"-"+reportCSJH.getCs_id());
				if(reportCSJH.getWell_flag().equals(newWellType) ) {  //心井
					List<Object> newWelllist = (List<Object>) csjhTypeMap.get("newWell");
					newWelllist.add(reportCSJH);
				}else if(reportCSJH.getWell_flag().equals(oldWellType) ) {  //心井
					List<Object> oilWelllist = (List<Object>) csjhTypeMap.get("oldWell");
					oilWelllist.add(reportCSJH);
				}else {
					List<Object> otherWelllist = (List<Object>) csjhTypeMap.get("otherWell");
					otherWelllist.add(reportCSJH);
				}
			}
		}
		//System.out.println(JSON.toJSONString(cdjhData_statistics));
		return cdjhData_statistics;
	}

	
	
	/**
	 * 为excel最后添加一个合计的方法
	 * @param csType_oil_statistics
	 * @param csType_water_statistics
	 * @param csType_other_statistics
	 * @param sheet
	 * @param rowNum
	 */
	public void __funAddSumEnd(Map<String, Object> csType_oil_statistics, 
							 Map<String, Object> csType_water_statistics,
							 Map<String, Object> csType_other_statistics, 
							 HSSFCellStyle style1,
							 HSSFCellStyle style2,
							 HSSFCellStyle style3,
							 HSSFSheet sheet, int rowNum) {
		HSSFRow row1;
		//判断最后添加 【合计】的位置
		int size_oil_water_other = csType_oil_statistics.size() +csType_water_statistics.size() +csType_other_statistics.size();
		int size_oil = csType_oil_statistics.size() ;
		int size_water = csType_water_statistics.size() ;
		int size_other = csType_other_statistics.size();
		HSSFCell heJicell = null;  //合计单元格
		String hejiCellName = "合计";
		int hejiCellHeight = 300;
		int startRowNumber = 4;		// 4从4列开始算  数据区域
		if(size_other ==0  ) {//只有油水井  最多加两个小计   一个合计
			if(size_oil != 0 && size_water != 0 ) {
				if(rowNum ==( size_oil_water_other + startRowNumber + 2) ) {//
					row1 = sheet.getRow(rowNum);
					row1.setHeight((short)hejiCellHeight);
					heJicell = row1.createCell(0);
					heJicell.setCellValue(new HSSFRichTextString(hejiCellName));
					heJicell.setCellStyle(style2);
					heJicell = row1.createCell(1);
					heJicell.setCellStyle(style2);
					sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
				}
			}else if((size_oil== 0 && size_water != 0 )
					|| (size_oil!= 0 && size_water == 0 )){
				if(rowNum ==( size_oil_water_other + startRowNumber +1)) {//
					row1 = sheet.getRow(rowNum);
					row1.setHeight((short)hejiCellHeight);
					heJicell = row1.createCell(0);
					heJicell.setCellValue(new HSSFRichTextString(hejiCellName));
					heJicell.setCellStyle(style2);
					heJicell = row1.createCell(1);
					heJicell.setCellStyle(style2);
					sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
				}
			}else {
				if(rowNum ==( size_oil_water_other + startRowNumber )) {
					row1 = sheet.getRow(rowNum);
					row1.setHeight((short)hejiCellHeight);
					heJicell = row1.createCell(0);
					heJicell.setCellValue(new HSSFRichTextString(hejiCellName));
					heJicell.setCellStyle(style2);
					heJicell = row1.createCell(1);
					heJicell.setCellStyle(style2);
					sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
				}
			}
		}else {	//最多会加 3个小计  加一个合计  
			//System.out.println("other"+size_oil_water_other+">>>"+rowNum);
			if(size_oil != 0 && size_water != 0 ) {
				if(rowNum ==( size_oil_water_other + startRowNumber +3 ) ) {//
					row1 = sheet.getRow(rowNum );
					row1.setHeight((short)hejiCellHeight);
					heJicell = row1.createCell(0);
					heJicell.setCellValue(new HSSFRichTextString(hejiCellName));
					heJicell.setCellStyle(style2);
					heJicell = row1.createCell(1);
					heJicell.setCellStyle(style2);
					sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
				}
			}else if((size_oil== 0 && size_water != 0 )
					|| (size_oil!= 0 && size_water == 0 )){
				if(rowNum ==( size_oil_water_other + startRowNumber +2)) {//
					row1 = sheet.getRow(rowNum);
					row1.setHeight((short)hejiCellHeight);
					heJicell = row1.createCell(0);
					heJicell.setCellValue(new HSSFRichTextString(hejiCellName));
					heJicell.setCellStyle(style2);
					heJicell = row1.createCell(1);
					heJicell.setCellStyle(style2);
					sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
				}
			}else {
				if(rowNum ==( size_oil_water_other + startRowNumber + 1)) {//
					row1 = sheet.getRow(rowNum);
					row1.setHeight((short)hejiCellHeight);
					heJicell = row1.createCell(0);
					heJicell.setCellValue(new HSSFRichTextString(hejiCellName));
					heJicell.setCellStyle(style2);
					heJicell = row1.createCell(1);
					heJicell.setCellStyle(style2);
					sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
				}
			}
		}
	}
	

	
	
	
	
	
	
	/**  新增或者更新   csjhoption 下拉框
	 * /csjh/insertOrUpdateCsjhOption
	 */
	@RequestMapping("/insertOrUpdateCsjhOption")
	@ResponseBody
	public String insertOrUpdateCsjhOption(CsjhOption csjhOption){	
		Map<String,Object> map = this.csjhService.insertOrUpdateCsjhOption(csjhOption);
		return JSON.toJSONString(map);
	}
	

	
	/**  
	 *  删除 csjhOption  下拉框
	 * /csjh/deleteCsjhOption
	 * 	删除传递jcjh——id
	 */
	@RequestMapping("/deleteCsjhOption")
	@ResponseBody
	public String deleteCsjh(CsjhOption csjhOption){	
		Map<String,Object> map = this.csjhService.deleteCsjhOption(csjhOption.getCs_id());
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/getCsjhOption")
	@ResponseBody
	public String getCsjhOption(CsjhOption csjhOption){	
		List<CsjhOption> list = this.csjhService.getCsjhOption(csjhOption);
		return JSON.toJSONString(list);
	}
	

	//  以下是 报表的备注信息的 增删改查
	@RequestMapping("/insertReportNote")
	@ResponseBody
	public String insertReportNote(ReportNote reportNote){	
		Map<String,Object> map  =this.csjhService.insertReportNote(reportNote);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/updateReportNote")
	@ResponseBody
	public String updateReportNote(ReportNote reportNote){	
		Map<String,Object> map  =this.csjhService.updateReportNote(reportNote);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/deleteReportNote")
	@ResponseBody
	public String deleteReportNote(ReportNote reportNote){	
		Map<String,Object> map  =this.csjhService.deleteReportNote(reportNote);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/selectReportNote")
	@ResponseBody
	public String selectReportNote(ReportNote reportNote){	
		Map<String,Object> map  =this.csjhService.selectReportNote(reportNote);
		return JSON.toJSONString(map);
	}
	
	
	
	
	
	
}