package edu.global.ex.Controller;

import edu.global.ex.Command.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("*.do")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet() ..");
        actionDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost() ..");
        actionDo(request, response);
    }

    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("actionDo() ..");

        request.setCharacterEncoding("UTF-8");

        String viewPage = null;
        Command command = null;

        String url = request.getRequestURI();
        String conPath = request.getContextPath();
        String com = url.substring(conPath.length());

        if(com.equals("/list.do")) {
            System.out.println("/list.do ..");

            command = new ListCommand();
            command.execute(request, response);
            viewPage = "list.jsp";
        }
        else if(com.equals("/voting.do")) {
            System.out.println("/voting.do ..");

            command = new VotingCommand();
            command.execute(request, response);
            viewPage = "voting.jsp";
        }
        else if(com.equals("/insert.do")) {
            System.out.println("/insert.do ..");

            command = new InsertCommand();
            command.execute(request, response);
            viewPage = "inquiry.do";
        }
        else if(com.equals("/inquiry.do")) {
            System.out.println("/inquiry.do ..");

            command = new InquiryCommand();
            command.execute(request, response);
            viewPage = "inquiry.jsp";
        }
        else if(com.equals("/rank.do")) {
            System.out.println("/rank.do ..");

            command = new RankCommand();
            command.execute(request, response);
            viewPage = "rank.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
        dispatcher.forward(request, response);
    }
}
