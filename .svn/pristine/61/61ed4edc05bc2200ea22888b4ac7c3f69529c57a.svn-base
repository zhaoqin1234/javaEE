package com.hrxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.DzsPzReportSummarkRemarkMapper;
import com.hrxt.pojo.DzsPzReportSummarkRemark;
import com.hrxt.pojo.DzsPzReportSummarkRemarkExample;
import com.hrxt.pojo.DzsPzReportSummarkRemarkExample.Criteria;
import com.hrxt.service.DzsPzReportSummarkRemarkService;
@Service
@Transactional
public class DzsPzReportSummarkRemarkServiceImpl implements DzsPzReportSummarkRemarkService{
	
	@Autowired
	private DzsPzReportSummarkRemarkMapper  dzsPzReportSummarkRemarkMapper;
	
	@Override
	public List<DzsPzReportSummarkRemark> getSummarkRemark(String date) {
		DzsPzReportSummarkRemarkExample example=new DzsPzReportSummarkRemarkExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSummarkDateEqualTo(date);
		List<DzsPzReportSummarkRemark> selectByExample = dzsPzReportSummarkRemarkMapper.selectByExample(example);
		return selectByExample;
	}

	@Override
	public int updateSummarkremarkByDate(DzsPzReportSummarkRemark record,String date) {
		DzsPzReportSummarkRemarkExample example=new DzsPzReportSummarkRemarkExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andSummarkDateEqualTo(date);
		int i= dzsPzReportSummarkRemarkMapper.updateByExampleSelective(record,example);
		return i;
	}

	@Override
	public int insterDzsPzReportSummarkRemark(DzsPzReportSummarkRemark record) {
		int i = dzsPzReportSummarkRemarkMapper.insert(record);
		return i;
	}

}
