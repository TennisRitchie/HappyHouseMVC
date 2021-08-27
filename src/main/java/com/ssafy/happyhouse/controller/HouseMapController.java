package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//주석
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.ArticleDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.ArticleService;
import com.ssafy.happyhouse.model.service.ArticleServiceImpl;
import com.ssafy.happyhouse.model.service.HouseMapServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;


@WebServlet("/map")
public class HouseMapController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private UserService loginService;
//	private ArticleService articleService;
//	
//	@Override
//	public void init() throws ServletException {
//		super.init();
//		loginService = new UserServiceImpl();
//		articleService = new ArticleServiceImpl();
//	}
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		process(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		process(request, response);
//	}
//	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String root = request.getContextPath();
//		response.setCharacterEncoding("UTF-8");
//		String act = request.getParameter("act");
//		
//		if("mvlogin".equals(act)) {
//			response.sendRedirect(root + "/user/login.jsp");
//		} else if("mvjoin".equals(act)) {
//			response.sendRedirect(root + "/user/join.jsp");
//		} else if("login".equals(act)) {
//			login(request, response);
//		} else if("logout".equals(act)) {
//			logout(request, response);
//		} else if("mvwrite".equals(act)) {
//			response.sendRedirect(root + "/article/write.jsp");
//		} else if("write".equals(act)) {
//			writeArticle(request, response);
//		} else if("list".equals(act)) {
//			listArticle(request, response);
//		} else if("mvarticle".equals(act)) {
//			moveModifyArticle(request, response);
//		} else if("mvmodify".equals(act)) {
//			moveModifyArticle(request, response);
//		} else if("modify".equals(act)) {
//			modifyArticle(request, response);
//		} else if("delete".equals(act)) {
//			deleteArticle(request, response);
//		}
//		
//		// si gun gu
//		else if("sido".equals(act)) {
//			PrintWriter out = response.getWriter();
//			List<SidoGugunCodeDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = HouseMapServiceImpl.getHouseMapService().getSido();
//				for(SidoGugunCodeDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("sido_code", dto.getSidoCode());
//					obj.put("sido_name", dto.getSidoName());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//sido
//		else if("gugun".equals(act)) {
//			String sido = request.getParameter("sido");
//			PrintWriter out = response.getWriter();
//			List<SidoGugunCodeDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = HouseMapServiceImpl.getHouseMapService().getGugunInSido(sido);
//				for(SidoGugunCodeDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("gugun_code", dto.getGugunCode());
//					obj.put("gugun_name", dto.getGugunName());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//gugun
//		else if("dong".equals(act)) {
//			String gugun = request.getParameter("gugun");
//			PrintWriter out = response.getWriter();
//			List<HouseInfoDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = HouseMapServiceImpl.getHouseMapService().getDongInGugun(gugun);
//				for(HouseInfoDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("code", dto.getCode());
//					obj.put("dong", dto.getDong());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//dong
//		else if("apt".equals(act)) {
//			String dong = request.getParameter("dong");
//			PrintWriter out = response.getWriter();
//			List<HouseInfoDto> list = null;
//			JSONArray arr = new JSONArray();
//			try {
//				list = HouseMapServiceImpl.getHouseMapService().getAptInDong(dong);
//				for(HouseInfoDto dto : list) {
//					JSONObject obj = new JSONObject();
//					obj.put("no", dto.getNo());
//					obj.put("dong", dto.getDong());
//					obj.put("aptName", dto.getAptName());
//					obj.put("code", dto.getCode());
//					obj.put("jibun", dto.getJibun());
//					obj.put("lat", dto.getLat());
//					obj.put("lng", dto.getLng());
//					arr.add(obj);
//				}
//			} catch (Exception e) {
//				arr = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("message_code", "-1");
//				arr.add(obj);
//				e.printStackTrace();
//			} finally {
//				out.print(arr.toJSONString());
//				out.close();
//			}
//		}//dong
//		else if("deal".equals(act)) {
//			String aptName = request.getParameter("apt");
//			PrintWriter out = response.getWriter();
//			JSONObject obj = new JSONObject();
//			try {
//				HouseDealDto dto = HouseMapServiceImpl.getHouseMapService().getAptInfo(aptName);
//				obj.put("dong", dto.getDong());
//				obj.put("aptName", dto.getAptName());
//				obj.put("area", dto.getArea());
//				obj.put("dealAmount", dto.getDealAmount());
//				obj.put("buildYear", dto.getBuildYear());
//				obj.put("type", dto.getType());
//			} catch (Exception e) {
//				obj = new JSONObject();
//				obj.put("message_code", "-1");
//				e.printStackTrace();
//			} finally {
//				out.print(obj.toJSONString());
//				out.close();
//			}
//		}
//		else if("mvwrite".equals(act)) {
//			response.sendRedirect(root + "/article/write.jsp");
//		} else if("write".equals(act)) {
//			writeArticle(request, response);
//		} else if("list".equals(act)) {
//			listArticle(request, response);
//		} else if("mvarticle".equals(act)) {
//			moveModifyArticle(request, response);
//		} else if("mvmodify".equals(act)) {
//			moveModifyArticle(request, response);
//		} else if("modify".equals(act)) {
//			modifyArticle(request, response);
//		} else if("delete".equals(act)) {
//			deleteArticle(request, response);
//		}
//		else {
//			response.sendRedirect(root);
//		}
//	}//process
//	
//	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
////		session.invalidate();
//		response.sendRedirect(request.getContextPath());
//	}
//
//	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String path = "/index.jsp";
//		String userid = request.getParameter("userid");
//		String userpwd = request.getParameter("userpwd");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userid", userid);
//		map.put("userpwd", userpwd);
//		try {
//			MemberDto memberDto = loginService.login(map);
//			if(memberDto != null) {
////				session 설정
//				HttpSession session = request.getSession();
//				session.setAttribute("userinfo", memberDto);
//				
////				cookie 설정
//				String idsave = request.getParameter("idsave");
//				if("saveok".equals(idsave)) {//아이디 저장을 체크 했다면.
//					Cookie cookie = new Cookie("ssafy_id", userid);
//					cookie.setPath(request.getContextPath());
//					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
//					response.addCookie(cookie);
//				} else {//아이디 저장을 해제 했다면.
//					Cookie cookies[] = request.getCookies();
//					if(cookies != null) {
//						for(Cookie cookie : cookies) {
//							if("ssafy_id".equals(cookie.getName())) {
//								cookie.setMaxAge(0);
//								response.addCookie(cookie);
//								break;
//							}
//						}
//					}
//				}
//			} else {
//				path = "/user/login.jsp";
//				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
//			path = "/error/error.jsp";
//		}
//		request.getRequestDispatcher(path).forward(request, response);
//	}
//	
//
//	
//	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String path = "/index.jsp";
//		int articleno = Integer.parseInt(request.getParameter("articleno"));
//
//		try {
//			articleService.deleteArticle(articleno);
//			path = "/map?act=list&key=&word=";
//			response.sendRedirect(request.getContextPath() + path);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글삭제 처리 중 문제가 발생했습니다.");
//			path = "/error/error.jsp";
//			request.getRequestDispatcher(path).forward(request, response);
//		}
//	}
//
//	private void modifyArticle(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String path = "/index.jsp";
//		ArticleDto articleDto = new ArticleDto();
//		articleDto.setArticleno(Integer.parseInt(request.getParameter("articleno")));
//		articleDto.setSubject(request.getParameter("subject"));
//		articleDto.setContent(request.getParameter("content"));
//
//		try {
//			articleService.modifyArticle(articleDto);
//			path = "/map?act=list&key=&word=";
//			response.sendRedirect(request.getContextPath() + path);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
//			path = "/error/error.jsp";
//			request.getRequestDispatcher(path).forward(request, response);
//		}
//	}
//
//	private void moveModifyArticle(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String path = "/index.jsp";
//		int articleno = Integer.parseInt(request.getParameter("articleno"));
//		System.out.println(articleno);
//		try {
//			ArticleDto articleDto = articleService.getArticle(articleno);
//			request.setAttribute("article", articleDto);
//			path = "/article/modify.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글수정 처리 중 문제가 발생했습니다.");
//			path = "/error/error.jsp";
//		}
//		request.getRequestDispatcher(path).forward(request, response);
//		System.out.println(path);
//	}
//
//	private void listArticle(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String path = "/index.jsp";
//		String key = request.getParameter("key");
//		String word = request.getParameter("word");
//		try {
//			List<ArticleDto> list = articleService.listArticle(key, word);
//			request.setAttribute("articles", list);
//			path = "/article/list.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글목록을 얻어오는 중 문제가 발생했습니다.");
//			path = "/error/error.jsp";
//		}
//		request.getRequestDispatcher(path).forward(request, response);
//	}
//
//	private void writeArticle(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String path = "/index.jsp";
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		ArticleDto aritcleDto = new ArticleDto();
//		aritcleDto.setUserid(memberDto.getUserid());
//		aritcleDto.setUserid("ssafy");
//		aritcleDto.setSubject(request.getParameter("subject"));
//		aritcleDto.setContent(request.getParameter("content"));
//
//		try {
//			articleService.writeArticle(aritcleDto);
//			path = "/article/writesuccess.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
//			path = "/error/error.jsp";
//		}
//		/*if (memberDto != null) {
//			ArticleDto guestBookDto = new ArticleDto();
//			guestBookDto.setUserid(memberDto.getUserid());
//			guestBookDto.setSubject(request.getParameter("subject"));
//			guestBookDto.setContent(request.getParameter("content"));
//
//			try {
//				articleService.writeArticle(guestBookDto);
//				path = "/article/writesuccess.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
//				path = "/error/error.jsp";
//			}
//		} else {
//			request.setAttribute("msg", "로그인 후 사용 가능한 페이지입니다.");
//			path = "/error/error.jsp";
//		}*/
//		request.getRequestDispatcher(path).forward(request, response);
//	}

}