package com.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Adoption;
import com.entities.Member;
import com.entities.Pet;
import com.entities.Publish;
import com.managers.AdoptionManager;
import com.managers.MemberManager;
import com.managers.PetManager;
import com.managers.PublishManager;

/**
 * Servlet implementation class Page
 */
@WebServlet("/do-page")
public class Page extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	static public String isNull(String temp) {
		return temp == null ? "" : temp;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do-page");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		// 取得session Attribute
		double num = (double) session.getAttribute("pageSize");
		int size = (int) num;

		int userId = (int) session.getAttribute("userId");

		// url path
		String previousUrl = request.getHeader("Referer");
		URL urlPath = new URL(previousUrl);
		String path = urlPath.getPath();

		// 頁數相關
		String tempPage = request.getParameter("page");
		String pageNum = (tempPage == null) ? "1" : request.getParameter("page");
		int currentPage = Integer.parseInt(pageNum);

		// 判斷按鈕相關
		Publish publish;

		// 設定浪浪列表 和 總頁數 和 設定預約列表
		List<Pet> pet = new ArrayList<>();
		List<Member> member = new ArrayList<>();
		int pageTotal = 0;
		List<Adoption> adoptionList = new ArrayList<>();

		// 設定不同分頁的屬性
		if (urlPath.getPath().equals("/CatDemo/search-adoption")) {

			adoptionList = (List<Adoption>) session.getAttribute("adoptions");
			pageTotal = adoptionList.size();

			// 取得 預約寵物的 相關訊息
			// 取得 預約會員的 相關訊息
			for (Adoption a : adoptionList) {
				pet.add(PetManager.getInstance().findById(a.getPet().getPetId()));
				member.add(MemberManager.getInstance().findById(a.getMember().getUserId()));
			}

		} else {
			pet = (List<Pet>) session.getAttribute("pets");
			pageTotal = pet.size();

		}

		// 如果有預約 且 預約結果是等候申請結果 則不顯示預約按鈕
		List<Adoption> adopList = AdoptionManager.getInstance().findByMemberId(userId);
		String adopAns = null;
		for (Adoption adop : adopList) {
			if (adop.getAdoptionResult().equals("等候申請結果")) {
				adopAns = "等候申請結果";
			}
		}

		// 迴圈印出每一page裡面的物件
		for (int i = ((currentPage - 1) * size); i < (currentPage * size); i++) {

			if (i >= pageTotal) {
				break;
			}

			int petId = pet.get(i).getPetId();
			String petName = pet.get(i).getPetName();
			String quest = pet.get(i).getQuest();
			String breed = pet.get(i).getBreed();
			String gender = pet.get(i).getGender();
			String coatColor = pet.get(i).getCoatColor();
			String age = pet.get(i).getAge();
			String location = pet.get(i).getLocation();
			String species = pet.get(i).getSpecies();
			String petSize = pet.get(i).getSize();

			publish = PublishManager.getInstance().findByPetId(petId);

			// 透過 petId 找到全部預約, 因預約結果是同意的話, 只會有一筆資料, 所以直接設定get(0)來取得
			List<Adoption> aList = AdoptionManager.getInstance().findAllByPetId(petId);
			String ans = null;

			if (!aList.isEmpty()) {
				Adoption a = aList.get(0);
				ans = a.getAdoptionResult();
			}

			// 呈現不同分頁內容
			// 當路徑不是/CatDemo/searchadoption
			if (!path.equals("/CatDemo/search-adoption")) {

				out.print("<div class='card_item'>");

				out.println("<input type='hidden' name='petId' value='" + petId + "'>");
				if (pet.get(i).getPhotos() != null) {
					String url = pet.get(i).getPhotos();

					out.print("<img src='" + url + "' class='card_img' alt='...' />");
				} else {
					out.print("<img src='img/1.jpg' class='card_img' alt='...' />");
				}

				out.print("<div class='card_text-box'>");
				out.print("<h1 class='card_text-header' >" + isNull(petName) + "</h1>");
				if (userId == pet.get(i).getMember().getUserId() && !(path.equals("/CatDemo/search-publish"))) {
					out.print("<span class='card_text-sp'>條件:</span>");
					out.print("<p class='card_text'>" + isNull(quest) + "</p>");
				}
				out.print("</div>");

				out.print("<ul class='card_content-box'>");

				if (path.equals("/CatDemo/pets")) {
					// 日期轉字串
					LocalDateTime publishDate = publish.getPublishDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					String dateString = format.format(publishDate);

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>刊登時間:</div>");
					out.print("<div class='item_text'>" + dateString + "</div>");
					out.print("</div></li>");
				}

				out.print("<li class='card_content'><div class='content_item'>");
				out.print("<div class=''>寵物品種:</div>");
				out.print("<div class='item_text'>" + isNull(breed) + "</div>");
				out.print("</div></li>");

				out.print("<li class='card_content'><div class='content_item'>");
				out.print("<div class=''>性別:</div>");
				out.print("<div class='item_text'>" + isNull(gender) + "</div>");
				out.print("</div></li>");

				out.print("<li class='card_content'><div class='content_item'>");
				out.print("<div class=''>毛色:</div>");
				out.print("<div class='item_text'>" + isNull(coatColor) + "</div>");
				out.print("</div></li>");

				out.print("<li class='card_content'><div class='content_item'>");
				out.print("<div class=''>年齡:</div>");
				out.print("<div class='item_text'>" + isNull(age) + "</div>");
				out.print("</div></li>");

				if (userId == pet.get(i).getMember().getUserId() && !(path.equals("/CatDemo/search-publish"))) {
					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>寵物種類:</div>");
					out.print("<div class='item_text'>" + isNull(species) + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>體型:</div>");
					out.print("<div class='item_text'>" + isNull(petSize) + "</div>");
					out.print("</div></li>");
				}
				out.print("</ul>");

				out.print("<div class='card_btn-box'>");

				// 會員專區頁面
				if (userId == pet.get(i).getMember().getUserId() && !(path.equals("/CatDemo/search-publish"))) {
					// 透過Objects.equals 來比較 ans 和同意
					// 如果ans 為null 不會報錯

					if (!Objects.equals(ans, "同意")) {
						out.print("<a href='search-pet?petid=" + petId + "' class='btn btn--full'>修改</a>");
						out.print("<a href='delete?petid=" + petId + "' class='btn btn--cat'>刪除</a>");

						if (path.equals("/CatDemo/member") && (publish == null)) {
							out.print("<a href='#' class='btn btn--full' id='card-btn'>刊登</a>");
						}
					} else {
						out.print("<p class='card_btn-text'>準備送養中</p>");
					}

				}
				// 浪浪頁面
				else if (path.equals("/CatDemo/pets")) {
					if (Objects.equals(adopAns, "等候申請結果")) {
						out.print("<p class='card_btn-text'>已有正在等待結果的預約</p>");
					} else {
						out.print("<a href='#' class='btn btn--full' id='card-btn'>預約</a>");
					}
					out.print("<button class='btn btn--cat btn-check' id='btn-check'>詳細</button>");
					out.print("</div>");

					// 彈出視窗
					out.print("<div class='item_detail'>");
					out.print("<div class='item_wind'>");
					out.print("<button class='btn_detail'>");
					out.print("<ion-icon class='icon_detail' name='close-outline'></ion-icon>");
					out.print("</button>");

					out.print("<div class='card_text-box'>");

					if (pet.get(i).getPhotos() != null) {
						String url = pet.get(i).getPhotos();

						out.print("<img src='" + url + "' class='card_img' alt='...' />");
					} else {
						out.print("<img src='img/1.jpg' class='card_img text-box-img' alt='...' />");
					}

					out.print("<h1 class='card_text-header' >" + isNull(petName) + "</h1>");
					out.print("<span class='card_text-sp'>條件:</span>");
					out.print("<p class='card_text'>" + isNull(quest) + "</p>");
					out.print(" </div>");
					out.print("<ul class='card_content-box'>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>寵物品種:</div>");
					out.print("<div class='item_text'>" + isNull(breed) + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>性別:</div>");
					out.print("<div class='item_text'>" + isNull(gender) + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>毛色:</div>");
					out.print("<div class='item_text'>" + isNull(coatColor) + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>年齡:</div>");
					out.print("<div class='item_text'>" + isNull(age) + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>目前位置:</div>");
					out.print("<div class='item_text'>" + isNull(location) + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>寵物種類:</div>");
					out.print("<div class='item_text'>" + isNull(species) + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>體型:</div>");
					out.print("<div class='item_text'>" + isNull(petSize) + "</div>");
					out.print("</div></li>");
					out.print("</ul>");

					out.print("</div>");

				}
				// 修改刊登頁面
				else if (path.equals("/CatDemo/search-publish")) {
					out.print(
							"<a href='delete-publish?id=" + petId + "' class='btn btn--full' id='card-adop'>取消刊登</a>");
				}

				out.print("</div>");
				out.print("</div>");

			}
			// 預約訊息介面
			else if (path.equals("/CatDemo/search-adoption")) {
				// 預約別人的浪浪
				if (adoptionList.get(i).getMember().getUserId() == userId) {
					out.print("<div class='card_item'>");

					if (pet.get(i).getPhotos() != null) {
						String url = pet.get(i).getPhotos();
						out.print("<img src='" + url + "' class='card_img' alt='...'/>");
					} else {
						out.print("<img src='img/1.jpg' class='card_img' alt='...'/>");
					}

					out.print("<div class='card_text-box'>");
					out.print("<h1 class='card_text-header' >" + isNull(petName) + "</h1>");
					out.print("</div>");

					out.print("<ul class='card_content-box'>");

					// 申請日期
					Date dateAdoption = adoptionList.get(i).getAdoptionDate();
					DateTimeFormatter formatAdoption = DateTimeFormatter.ofPattern("yyyy/MM/dd a hh:mm:ss");
					String DateTimeAdoption = LocalDateTime.ofInstant(dateAdoption.toInstant(), ZoneId.systemDefault())
							.format(formatAdoption);

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>預約日期:</div>");
					out.print("<div class='item_text'>" + DateTimeAdoption + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>預約結果:</div>");
					out.print("<div class='item_text'>" + adoptionList.get(i).getAdoptionResult() + "</div>");
					out.print("</div></li>");

					// 申請結果
					String str = "等候申請結果";
					String agree = "同意";
					// 比對預約結果 顯示 不同的按鈕 表格
					if (adoptionList.get(i).getAdoptionResult().equals(str)) {
						out.print("</ul>");
						out.print("<div class='card_btn-box'>");
						out.print("<p class='card_btn-text'>是否取消預約 ?</p>");

						out.print("<a href='delete-adoption?id=" + adoptionList.get(i).getAdoptionId()
								+ "' class='btn btn--full'>取消預約</a>");
						out.print("</div>");
					} else {
						// 答覆日期
						Date dateRecord = adoptionList.get(i).getRecordDate();
						DateTimeFormatter formatRecord = DateTimeFormatter.ofPattern("yyyy/MM/dd a hh:mm:ss");
						String DateTimeRecord = LocalDateTime.ofInstant(dateRecord.toInstant(), ZoneId.systemDefault())
								.format(formatRecord);

						Pet pubPet = adoptionList.get(i).getPet();
						Member pubMem = pubPet.getMember();

						// 當結果是 同意 才顯示 刊登者的資料
						if (adoptionList.get(i).getAdoptionResult().equals(agree)) {
							out.print("<li class='card_content'><div class='content_item'>");
							out.print("<div class=''>刊登人姓名:</div>");
							out.print("<div class='item_text'>" + pubMem.getUsername() + "</div>");
							out.print("</div></li>");

							out.print("<li class='card_content'><div class='content_item'>");
							out.print("<div class=''>刊登人電話:</div>");
							out.print("<div class='item_text'>" + pubMem.getPhone() + "</div>");
							out.print("</div></li>");
						}

						out.print("<li class='card_content'><div class='content_item'>");
						out.print("<div class=''>答覆日期:</div>");
						out.print("<div class='item_text'>" + DateTimeRecord + "</div>");
						out.print("</div></li>");

						out.print("</ul>");
					}
				}
				// 被別人預約浪浪
				else {
					out.print("<div class='card_item'>");

					if (pet.get(i).getPhotos() != null) {
						String url = pet.get(i).getPhotos();
						out.print("<img src='" + url + "' class='card_img' alt='...'/>");
					} else {
						out.print("<img src='img/1.jpg' class='card_img' alt='...'/>");
					}

					out.print("<div class='card_text-box'>");
					out.print("<h1 class='card_text-header' >" + isNull(petName) + "</h1>");
					out.print("</div>");

					out.print("<ul class='card_content-box'>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>預約人姓名:</div>");
					out.print("<div class='item_text'>" + member.get(i).getUsername() + "</div>");
					out.print("</div></li>");

					out.print("<li class='card_content'><div class='content_item'>");
					out.print("<div class=''>預約人電話:</div>");
					out.print("<div class='item_text'>" + member.get(i).getPhone() + "</div>");
					out.print("</div></li>");

					// 如果預約結果為同意
					if (adoptionList.get(i).getAdoptionResult().equals("同意")) {
						// 答覆日期
						Date dateRecord = adoptionList.get(i).getRecordDate();
						DateTimeFormatter formatRecord = DateTimeFormatter.ofPattern("yyyy/MM/dd a hh:mm:ss");
						String DateTimeRecord = LocalDateTime.ofInstant(dateRecord.toInstant(), ZoneId.systemDefault())
								.format(formatRecord);

						out.print("<li class='card_content'><div class='content_item'>");
						out.print("<div class=''>預約結果:</div>");
						out.print("<div class='item_text'>" + adoptionList.get(i).getAdoptionResult() + "</div>");
						out.print("</div></li>");

						out.print("<li class='card_content'><div class='content_item'>");
						out.print("<div class=''>答覆日期:</div>");
						out.print("<div class='item_text'>" + DateTimeRecord + "</div>");
						out.print("</div></li>");

						out.print("</ul>");
					} else {

						out.print("<p class='card_btn-text adop-text'>是否同意 ?</p>");

						out.print("<div class='card_btn-box'>");

						out.print("<a href='update-adoption?id=" + adoptionList.get(i).getAdoptionId()
								+ "&isAgr=0' class='btn btn--full'>否</a>");

						out.print("<a href='update-adoption?id=" + adoptionList.get(i).getAdoptionId()
								+ "&isAgr=1' class='btn btn--cat'>是</a>");

						out.print("</div>");
					}

				}

				out.print("</div>");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
