package edu.global.ex.Command;

import edu.global.ex.Dao.VotingDao;
import edu.global.ex.Dto.VoteDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String v_jumin = request.getParameter("v_jumin");
        String v_name = request.getParameter("v_name");
        String m_no = request.getParameter("m_no");
        String v_time = request.getParameter("v_time");
        String v_area = request.getParameter("v_area");
        String v_confirm = request.getParameter("v_confirm");

        System.out.println("InsertCommand ..");

        VotingDao votingDao = new VotingDao();

        votingDao.insertVote(v_jumin, v_name, m_no, v_time, v_area, v_confirm);
    }
}
