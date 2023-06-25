package com.pets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Pet;
import com.entities.Publish;
import com.managers.PublishManager;

/**
 * Servlet implementation class PetServlet
 */
@WebServlet("/pets")
public class PetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("pets");

		List<Publish> publishList = new ArrayList<>();
		List<Pet> result = new ArrayList<>();

		HttpSession session = request.getSession();

		int userId = (int) session.getAttribute("userId");

		/*********************/
		// 處理關鍵字搜尋 相關問題
		List<Pet> searchPetsResult = new ArrayList<>();
		Optional<List<Pet>> optionalSearchResult = Optional.empty();
		optionalSearchResult = Optional.ofNullable((List<Pet>) session.getAttribute("kwSearchResult"));

		// 如果 Optional 物件存在 且 內部值 不為空
		if (optionalSearchResult.isPresent() && !optionalSearchResult.get().isEmpty()) {
			searchPetsResult = optionalSearchResult.get();
			session.removeAttribute("kwSearchResult");
		} else {
			System.out.println("KeywordSearchResult is empty");
		}

		/*********************/

		/*********************/
		// 處理 排序Sort 相關問題
		List<Publish> publishBySort = new ArrayList<>();
		// 透過 Optional 避免NullPointerException
		// 並在物件為空時 執行預設處理
		Optional<List<Publish>> optionalPublishBySort = Optional.empty();
		optionalPublishBySort = Optional.ofNullable((List<Publish>) request.getAttribute("publishBySort"));

		if (optionalPublishBySort.isPresent()) {
			publishBySort = optionalPublishBySort.get();
			// 物件不為空，進行相應的處理
		} else {
			System.out.println("publishBySort is empty");
		}
		/*********************/

		// 總共頁面
		int pageTotal;
		// 每一頁的數量
		double pageSize = 6.0;
		// 取得當前頁面
		String tempPage = request.getParameter("page");
		String currentPage = (tempPage == null) ? "1" : request.getParameter("page");

		// 如果未使用排序 且未使用搜尋
		if (publishBySort.isEmpty() && searchPetsResult. isEmpty()) {
			// 找到全部刊登
			publishList = PublishManager.getInstance().findAll();
			// 顯示 除了自己以外的刊登
			for (Publish pub : publishList) {

				int id = pub.getMember().getUserId();

				if (id != userId) {
					result.add(pub.getPet());
				}
			}
		} else if (publishBySort.isEmpty()) {
			// 比對搜尋結果 是否有在刊登表上
			for (Pet pet : searchPetsResult) {
				Publish publishBySearch = PublishManager.getInstance().findByPetId(pet.getPetId());

				// 將有在刊登表上的 pet 加入
				if (publishBySearch != null) {
					result.add(pet);
				}
			}
		} else {
			// 如果搜尋結果不為空
			if (!searchPetsResult.isEmpty()) {
				// 根據 排序結果 得到每一個刊登
				for (Publish pub : publishBySort) {
					// 根據 搜尋結果 得到每一個寵物
					for (Pet pet : searchPetsResult) {
						// 比對兩者 是否有相同的 寵物編號 
						// 如果有, 則代表是有刊登的寵物
						if ((pub.getPet().getPetId() == pet.getPetId())) {
							result.add(pub.getPet());
						}
					}
				}
			} else {
				// 取得排序資料 將除了自己刊登的以外 存入result
				for (Publish pub : publishBySort) {
					int id = pub.getMember().getUserId();

					if (id != userId) {
						result.add(pub.getPet());
					}
				}

			}

		}

		pageTotal = (int) Math.ceil(result.size() / pageSize);

		session.setAttribute("pets", result);
		session.setAttribute("pageSize", pageSize);
		request.setAttribute("pageTotal", pageTotal);
		request.setAttribute("currentPage", currentPage);

		request.getRequestDispatcher("/pets/list-pets.jsp").forward(request, response);
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
