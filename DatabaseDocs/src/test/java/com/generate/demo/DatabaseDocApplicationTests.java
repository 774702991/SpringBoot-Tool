package com.generate.demo;

import com.generate.demo.dao.GenerateDao;
import com.generate.demo.entity.TableFileds;
import com.generate.demo.entity.TableInfo;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.rtf.RtfWriter2;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.List;

@SpringBootTest
@MapperScan("com.generate.demo.*")
class DatabaseDocApplicationTests {

	private static List<TableFileds> fileds;
	@Autowired
	private GenerateDao generateDao;

	@Test
	void contextLoads() {
		System.out.println("开始生成----------------");
		//数据库名称
		String dbName = "eduplat";
		//文件存放为止
		String fileName = "d:\\1/1.doc";
		createDoc(dbName, fileName);
	}

	/**
	 * 获取表结构，存储
	 * @param table 表名和描述
	 */
	private void getTableStructure(TableInfo table){
		fileds = generateDao.getTableStructure(table.getName());
	}

	/**
	 * 生成word
	 * @param dbName 	数据库名称
	 * @param fileName	文件存放地址（应放在已知目录内）
	 */
	private void createDoc(String dbName, String fileName){
		Document document = new Document(PageSize.A4);
		try {
			// 写入文件信息
			RtfWriter2.getInstance(document, new FileOutputStream(fileName));
			document.open();

			Paragraph p = new Paragraph("268教育数据库文档", new Font(Font.NORMAL, 24, Font.BOLDITALIC, new Color(0, 0, 0)));
			p.setAlignment(1);
			document.add(p);

			//获取所有表名和描述
			List<TableInfo> tables = generateDao.getTables(dbName);
			//记录多少张表
			int count = 0;
			for (TableInfo tableInfo : tables) {
				//获取表结构，存储
				getTableStructure(tableInfo);
				String all = "表名称:" + tableInfo.getName() + "（" + tableInfo.getComment() + "）";
				Table table = new Table(6);// 设置表格为6列

				int width[] = { 10,20,20,10,10,30};//每列的占比例
				table.setWidths(width);

				document.add(new Paragraph(""));

				table.setBorderWidth(1);
				table.setPadding(0);
				table.setSpacing(0);

				//添加表头的元素，并设置表头背景的颜色
				Color chade = new Color(176, 196, 222);

				Cell cell = new Cell("编号");
				addCell(table, cell, chade);
				cell = new Cell("字段名");
				addCell(table, cell, chade);
				cell = new Cell("类型");
				addCell(table, cell, chade);
				cell = new Cell("非空");
				addCell(table, cell, chade);
				cell = new Cell("主键");
				addCell(table, cell, chade);
				cell = new Cell("注释");
				addCell(table, cell, chade);

				table.endHeaders();

				// 表格的主体
				for (int k = 0; k < fileds.size(); k++) {
					addContent(table, (k + 1) + "");
					addContent(table, fileds.get(k).getField());
					addContent(table, fileds.get(k).getType());
					addContent(table, fileds.get(k).getNull().equals("YES") ? "否" : "是");
					addContent(table, !"".equals(fileds.get(k).getKey()) ? "是" : "否");
					addContent(table, fileds.get(k).getComment());
				}
				Paragraph pheae = new Paragraph(all);
				//写入表说明
				document.add(pheae);
				//生成表格
				document.add(table);
				count++;
			}
			System.out.println("表格生成成功。计划生成数："+ tables.size()+"，实际生成数："+count);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加表头到表格
	 *
	 * @param table		表格
	 * @param cell		列标题
	 * @param chade		颜色
	 */
	private void addCell(Table table, Cell cell, Color chade) {
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(chade);
		table.addCell(cell);
	}

	/**
	 * 添加内容到表格
	 *
	 * @param table		表格
	 * @param content	内容
	 */
	private void addContent(Table table, String content) {
		Cell cell = new Cell(content);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		table.addCell(cell);
	}
}
