<%@ page contentType="image/jpeg" pageEncoding="UTF-8"%>
<%@ page import="java.awt.*,java.awt.image.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.imageio.*"%>

<%
//設定頁面不使用緩衝區
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

//在記憶體中建立圖像
//int width = 90, height = 30;
int width = 120, height = 40;
//建立BufferedImage物件 
BufferedImage image;
image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//取得Graphics物件
Graphics g = image.getGraphics();

//建立亂數產生器物件   
Random random = new Random();

//設定顏色,繪製填滿顏色的矩形
// 未完成

g.setColor(Color.WHITE);

g.fillRect(0, 0, width, height);

int x1, y1, x2, y2;
for (int i = 0; i < 4; i++) {
	g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
	x1 = random.nextInt(width);
	y1 = random.nextInt(30);
	x2 = random.nextInt(30);
	y2 = random.nextInt(height);

	g.drawLine(x1, y1, x2, y2);
}

//設定字體
g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

//隨機產生的認證碼(4位數)
//寫入矩形內,每位數顏色不同
String sRand = "";

for (int i = 0; i < 4; i++) {
	String rand = String.valueOf(random.nextInt(10));

	// 將認證碼寫入到圖像中

	g.setColor(new Color(random.nextInt(128), random.nextInt(128), random.nextInt(128)));

	//g.drawString(rand, 22 * i + 8, 20); 
	g.drawString(rand, 27 * i + 15, 27); 

	sRand += rand;
}

// 將認證碼存入 session 物件
session.setAttribute("rnd", sRand);
// 釋放圖像資源
g.dispose();
// Google瀏覽器
out.clear();
out = pageContext.pushBody();

// 輸出圖像到頁面
ImageIO.write(image, "JPEG", response.getOutputStream());
%>
