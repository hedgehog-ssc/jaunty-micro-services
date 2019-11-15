package com.jaunty.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

@Service
public class ExportService {

    /**
     * 导出PDF文件
     * @param request
     * @param response
     * @throws Exception
     */
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //纸张规格：A4纸
        Rectangle rectangle = new Rectangle(PageSize.A4);
        //创建文档实例
        Document doc = new Document(rectangle);
        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //设置字体样式
        Font boldFont = new Font(bfChinese, 11, Font.BOLD);
        //15号字体，橘色
        Font secondTitleFont = new Font(bfChinese, 15, Font.BOLD, CMYKColor.ORANGE);
        //11号字体，默认颜色：黑色
        Font FontChinese11 = new Font(bfChinese, 11, Font.ITALIC);

        //PDF文件名
        String title = "PDF文件名";
        //response响应
        response.setContentType("application/pdf");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-disposition", "attachment; filename=".concat(String.valueOf(URLEncoder.encode(title + ".pdf", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        PdfWriter.getInstance(doc, out);

        doc.open();

        doc.newPage();

        //标题段落
        Paragraph p = new Paragraph("这里是标题", secondTitleFont);
        //行高
        p.setLeading(300);
        //居中
        p.setAlignment(Element.ALIGN_CENTER);
        //段落放入文档
        doc.add(p);
        //设置空段落，行高为30
        Paragraph blankRow = new Paragraph("        ", FontChinese11);
        //行高
        blankRow.setLeading(50);
        doc.add(blankRow);
        //表格1：2列
        PdfPTable table1 = new PdfPTable(2);
        //宽度
        int width1[] = {80, 60};
        table1.setWidths(width1);

        //单元格赋值，每个单元格为一个段落，段落高度为70，加粗字体
        Paragraph p11 = new Paragraph("姓名：   王彩顺", boldFont);
        PdfPCell cell11 = new PdfPCell(p11);
        //单元格高度
        cell11 .setMinimumHeight(50);
        table1.addCell(cell11);

        Paragraph p12 = new Paragraph("性别：   男", boldFont);
        PdfPCell cell12 = new PdfPCell(p12);
        //单元格高度
        cell12 .setMinimumHeight(50);
        table1.addCell(cell12);
        doc.add(table1);

        //同样方式设置剩余PDF的cell
        PdfPTable table2 = new PdfPTable(2);
        table2.setWidths(width1);
        //行高50
        PdfPCell cell21 = new PdfPCell(new Paragraph(50f, "第二行数据哦", boldFont));
        cell21.setBorder(10);

        //合并2列
        cell21.setColspan(2);
        table2.addCell(cell21);

        doc.add(table2);

        doc.close();
    }

    /**
     * 导出Excel文件
     * @param request
     * @param response
     */
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

    }
}
