package com.adoption;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Adoption;
import com.entities.Pet;
import com.entities.Publish;
import com.managers.AdoptionManager;
import com.managers.MemberManager;
import com.managers.PublishManager;

/**
 * Servlet implementation class SearchAdoption
 */
@WebServlet("/search-adoption")
public class SearchAdoption extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAdoption() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Adoption> memberAdop = new ArrayList<>();
		List<Adoption> adoptionList = new ArrayList<>();
		List<Publish> publish =new ArrayList<>();
		List<Adoption> result = new ArrayList<>();
		String str = "等候申請結果";

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		
		// 總共頁面
		int pageTotal;
		// 每一頁的數量
		double pageSize = 6.0;
		// 取得當前頁面
		String tempPage = request.getParameter("page");
		String currentPage = (tempPage == null) ? "1" : request.getParameter("page");
		
		// 找到 與該會員刊登的寵物 有關的 預約訊息表
		// 找到該會員全部的寵物
		// 找到每隻寵物的全部預約
		// 如果該預約結果是同意的 則加入result
		List<Pet> pets = MemberManager.getInstance().getPetsByMemberId(userId);
		for(Pet p : pets) {
			List<Adoption> petAdop = AdoptionManager.getInstance().findAllByPetId(p.getPetId());
			
			for(Adoption adop : petAdop) {
				if(adop.getAdoptionResult().equals("同意")) {
					result.add(adop);
				}
			}
		}
		
		
		// 找到與該會員有關的 預約訊息表
		// 如 預約某一隻浪浪 等訊息
		memberAdop = AdoptionManager.getInstance().findByMemberId(userId);
		for(Adoption adop:memberAdop) {
			if((adop != null) ) {
				result.add(adop);
			}
		}
		
		// 或者 刊登的某一隻浪浪被預約
		publish = PublishManager.getInstance().findAllOfMember(userId);
		
		for(Publish temp : publish) {
			// 得到每個刊登的petID
			int tempPetId = temp.getPet().getPetId();
			// 使用該petID 取得該浪浪的全部預約
			adoptionList = AdoptionManager.getInstance().findAllByPetId(tempPetId);
			// 當預約列表不為空時
			if(!adoptionList.isEmpty()) {
				// 跑迴圈  判斷AdoptionResult是否已有結果(同意或不同意)
				// 沒有結果 則 加入
				adoptionList.forEach((adop) ->{
					if(adop.getAdoptionResult().equals(str)) {
						result.add(adop);
					}
				});
			}
		}
		
		pageTotal = (int) Math.ceil(result.size() / pageSize);

		session.setAttribute("adoptions", result);
		session.setAttribute("pageSize", pageSize);
		request.setAttribute("pageTotal", pageTotal);
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/members/member.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
