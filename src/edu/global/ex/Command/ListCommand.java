package edu.global.ex.Command;

import edu.global.ex.Dao.VotingDao;
import edu.global.ex.Dto.CandidateDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("ListCommand ..");

        VotingDao votingDao = new VotingDao();
        List<CandidateDto> dtoList = votingDao.list();

        request.setAttribute("list", dtoList);
    }
}
