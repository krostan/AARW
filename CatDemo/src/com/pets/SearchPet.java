package com.pets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Pet;
import com.google.gson.Gson;
import com.managers.PetManager;

/**
 * Servlet implementation class UpdatePet
 */
@WebServlet("/search-pet")
public class SearchPet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String adminUrl = (String) request.getAttribute("adminUrl");
		// 判斷是否從管理介面來的
		if (adminUrl != null) {
			
			String updatePetNo = request.getParameter("updatePetNo");
			int petNo = Integer.parseInt(updatePetNo);
			session.setAttribute("petId", petNo);
			
			Pet pet = PetManager.getInstance().findById(petNo);
			
			int userId = pet.getMember().getUserId();
			session.setAttribute("userId", userId);
			
			Map<String, String> map = new HashMap<>();

			map.put("petName", pet.getPetName());
			map.put("breed", pet.getBreed());
			map.put("petGender", pet.getGender());
			map.put("coatColor", pet.getCoatColor());
			map.put("age", pet.getAge());
			map.put("location", pet.getLocation());
			map.put("species", pet.getSpecies());
			map.put("size", pet.getSize());
			map.put("quest", pet.getQuest());
			map.put("photos", pet.getPhotos());
			
			Gson gson = new Gson();
			String json = gson.toJson(map);

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
			
		} else {

			String tmpId = request.getParameter("petid");
			int petId = Integer.parseInt(tmpId);

			// findById();
			Pet pet = new Pet();
			pet = PetManager.getInstance().findById(petId);

			request.setAttribute("pet", pet);

			request.getRequestDispatcher("/pets/update-pet.jsp").forward(request, response);
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
