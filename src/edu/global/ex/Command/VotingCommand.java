package edu.global.ex.Command;

import edu.global.ex.Dao.VotingDao;
import edu.global.ex.Dto.VotingNumDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class VotingCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("VotingCommand ..");

        VotingDao votingDao = new VotingDao();
        List<VotingNumDto> votingNumsList = votingDao.votingName();

        request.setAttribute("votingList", votingNumsList);
    }
}
