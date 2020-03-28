package yqToexcel;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



public class FromDbToExcel {
	ChartPanel frame1;
	public  FromDbToExcel(){
		CategoryDataset dataset = getDataSet();//将获得的数据传递给CategoryDataset类的对象
       JFreeChart chart = ChartFactory.createBarChart3D(
      		                 "疫情统计表", // 图表标题
                           "统计项目", // 目录轴的显示标签
                           "人数", // 数值轴的显示标签
                           dataset, // 数据集
                           PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                           true, // 是否显示图例(对于简单的柱状图必须是false)
                           false,  // 是否生成工具
                           false  // 是否生成URL链接
                           );
       CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
       CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
         chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
         chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
         
         frame1=new ChartPanel(chart,true);  //这里也可以用chartFrame,可以直接生成一个独立的Frame
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private CategoryDataset getDataSet() {
		// TODO 自动生成的方法存根
		int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        int count7=0;
        List<Yiq> list= YiqService.getAllByDb();
        for (int i = 0; i < list.size(); i++)
        {
        	Label wuhan_i= new Label(4, i+1, list.get(i).getWuhan()+"");
        	Label hubei_i= new Label(5, i+1, list.get(i).getHubei()+"");
        	Label wuhancontact_i= new Label(6, i+1, list.get(i).getWuhancontact()+"");
        	Label hubeicontact_i= new Label(7, i+1, list.get(i).getHubeicontact()+"");
        	Label back_i= new Label(8, i+1, list.get(i).getBack()+"");
        	Label confirm_i= new Label(9, i+1, list.get(i).getConfirm()+"");
        	Label state_i= new Label(10, i+1, list.get(i).getState()+"");
        	
        	if(wuhan_i.equals("是"))
        		count1++;
        	if(hubei_i.equals("是"))
        		count2++;
        	if(wuhancontact_i.equals("是"))
        		count3++;
        	if(hubeicontact_i.equals("是"))
        		count4++;
        	if(back_i.equals("是"))
        		count5++;
        	if(confirm_i.equals("是"))
        		count6++;
        	if(state_i.equals("已审核"))
        		count7++;
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(count1, "是否在武汉", "是否在武汉");
        dataset.addValue(count2, "是否湖北（不含武汉）", "是否湖北（不含武汉）");
        dataset.addValue(count3, "是否有武汉接触史", "是否有武汉接触史");
        dataset.addValue(count4, "是否有湖北接触史", "是否有湖北接触史");
        dataset.addValue(count5, "是否返校", "是否返校");
        dataset.addValue(count6, "是否有疑似病症", "是否有疑似病症");
        dataset.addValue(count7, "是否已确认被感染", "是否已确认被感染");
        return dataset;
		
	}
	
	public ChartPanel getChartPanel(){
		   return frame1;
	}

	public static void main(String[] args) {
		try {
			WritableWorkbook wwb = null;
			// 创建可写入的Excel工作簿
            String fileName = "F://test1//yiqing.xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
          //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);
            
         // 创建工作表
            WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
            
          //查询数据库中所有的数据
            List<Yiq> list= YiqService.getAllByDb();
            //要插入到的Excel表格的行号，默认从0开始
            Label Id= new Label(0, 0, "学号");//表示第
            Label name= new Label(1, 0, "姓名");
            Label college= new Label(2, 0, "班级");
            Label class1= new Label(3, 0, "班级");
            Label wuhan= new Label(4, 0, "武汉籍");
            Label hubei= new Label(5, 0, "湖北籍");
            Label wuhancontact= new Label(6, 0, "武汉接触史");
            Label hubeicontact= new Label(7, 0, "湖北接触史");
            Label back= new Label(8, 0, "是否返校");
            Label confirm= new Label(9, 0, "疑似");
            Label state= new Label(10, 0, "确诊");
            
            ws.addCell(Id);
            ws.addCell(name);
            ws.addCell(college);
            ws.addCell(class1);
            ws.addCell(wuhan);
            ws.addCell(hubei);
            ws.addCell(wuhancontact);
            ws.addCell(hubeicontact);
            ws.addCell(back);
            ws.addCell(confirm);
            ws.addCell(state);
            
            
            
            for (int i = 0; i < list.size(); i++)
            {
            	
            	Label Id_i= new Label(0, i+1, list.get(i).getId()+"");
            	Label Name_i= new Label(1, i+1, list.get(i).getName1()+"");
            	Label college_i= new Label(2, i+1, list.get(i).getCollege()+"");
            	Label class1_i= new Label(3, i+1, list.get(i).getClass1()+"");
            	Label wuhan_i= new Label(4, i+1, list.get(i).getWuhan()+"");
            	Label hubei_i= new Label(5, i+1, list.get(i).getHubei()+"");
            	Label wuhancontact_i= new Label(6, i+1, list.get(i).getWuhancontact()+"");
            	Label hubeicontact_i= new Label(7, i+1, list.get(i).getHubeicontact()+"");
            	Label back_i= new Label(8, i+1, list.get(i).getBack()+"");
            	Label confirm_i= new Label(9, i+1, list.get(i).getConfirm()+"");
            	Label state_i= new Label(10, i+1, list.get(i).getState()+"");
            	
            	
            	
            	ws.addCell(Id_i);
            	ws.addCell(Name_i);
            	ws.addCell(college_i);
            	ws.addCell(class1_i);
            	ws.addCell(wuhan_i);
            	ws.addCell(hubei_i);
            	ws.addCell(wuhancontact_i);
            	ws.addCell(hubeicontact_i);
            	ws.addCell(back_i);
            	ws.addCell(confirm_i);
            	ws.addCell(state_i);
            }
          //写进文档
            wwb.write();
           // 关闭Excel工作簿对象
            System.out.println("数据导出成功!");
            wwb.close();
		}catch (Exception e) {
			 // TODO Auto-generated catch block
            e.printStackTrace();
		}
		JFrame frame=new JFrame("疫情统计表");
		frame.setLayout(new GridLayout(2,2,10,10));
		frame.add(new FromDbToExcel().getChartPanel());   //添加柱形图
		frame.setBounds(0, 0, 900, 800);
		frame.setVisible(true);
	}

}
