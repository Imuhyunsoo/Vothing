package edu.global.ex.Command;

import edu.global.ex.Dao.VotingDao;
import edu.global.ex.Dto.RankDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RankCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("RankCommand ..");

        VotingDao votingDao = new VotingDao();
        List<RankDto> rankList = votingDao.rankList();

        request.setAttribute("rankList", rankList);
    }
}
