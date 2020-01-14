package com.reactiveworks.restipl.services.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.reactiveworks.restipl.dao.ITempMatchDao;
import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.db.impl.TempMatchDBDaoImpl;
import com.reactiveworks.restipl.model.Match;
import com.reactiveworks.restipl.services.interfaces.ITempMatchServices;

@Path("matchresource")
public class TempMatchServicesImpl implements ITempMatchServices {
	ITempMatchDao matchdao = new TempMatchDBDaoImpl();
	
	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.services.impl.IMatchServices#getAllMatch()
	 */
	@Override
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Match> getAllMatch() throws DataAccessException {
		return matchdao.getAllMatches();
	}

	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.services.impl.IMatchServices#getMatchById(int)
	 */
	@Override
	@GET
	@Path("match/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Match getMatchById(@PathParam("id") int id) throws DataAccessException {
		return matchdao.getMatchById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.services.impl.IMatchServices#addMatch(com.reactiveworks.restipl.model.Match)
	 */
	@Override
	@POST
	@Path("match")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public int addMatch(Match match) throws DataAccessException {
		return matchdao.addMatch(match);
	}
	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.services.impl.IMatchServices#updateMatchById(com.reactiveworks.restipl.model.Match)
	 */
	@Override
	@PATCH
	@Path("match")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void updateMatchById(Match match) throws DataAccessException {
		matchdao.updateMatchById(match);
	}
	
	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.services.impl.IMatchServices#deleteMatchById(int)
	 */
	@Override
	@DELETE
	@Path("match/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deleteMatchById(@PathParam("id") int id) throws DataAccessException {
		matchdao.deleteMatchById(id);
	}

}
