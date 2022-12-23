package edu.global.ex.Command;

import edu.global.ex.Dao.VotingDao;
import edu.global.ex.Dto.VoteDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InquiryCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("InquiryCommand ..");

        VotingDao votingDao = new VotingDao();
        List<VoteDto> voteList = votingDao.voteList();

        request.setAttribute("voteList", voteList);

    }
}
