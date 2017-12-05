package service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import map.CartItemBean;
import bao.BookBean;

/**
 * Servlet implementation class AddBookToCart
 */
@WebServlet("/AddBookToCart")
public class AddBookToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get session
		HttpSession session = request.getSession(false);
		Map cart = (Map) session.getAttribute("cart");

		
		

		if (cart == null) {//if do not have this book ,take it  num=1

	    	cart =  new HashMap();
	        session.setAttribute("cart", cart);
		}
		  //take up the book 
	  	BookBean book = (BookBean)session.getAttribute("bookToAdd"); 
	    CartItemBean cartItem = (CartItemBean) cart.get(book.getISBN());
	    //if Exist the book 
	    if (cartItem != null){ 
	        cartItem.setQuantity(cartItem.getQuantity() + 1);
	    }else{
			//botain book name and number to map
	    	cart.put(book.getISBN(), new CartItemBean(book, 1));
	    }

		//map save in session
		session.setAttribute("cart", cart);
		
		// Turn to viewCart.jsp list Shopping cart
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCart.jsp");
		dispatcher.forward(request, response);

	}

}
