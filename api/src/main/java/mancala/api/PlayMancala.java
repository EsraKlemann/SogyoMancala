package mancala.api;

import java.io.IOException;

import org.eclipse.jetty.util.Index;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.Mancala;
import mancala.domain.MancalaException;
import mancala.domain.MancalaImpl;

@Path("/play")
public class PlayMancala {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialize(
            @Context HttpServletRequest request,
            MoveDTO move) {

        var index = move.getIndex();

        var session = request.getSession(false);
        MancalaImpl mancala = (MancalaImpl) session.getAttribute("mancala");
        String namePlayer1 = (String) session.getAttribute("player1");
        String namePlayer2 = (String) session.getAttribute("player2");

        try {
            mancala.playPit(index);
        } catch (MancalaException e) {
            e.printStackTrace();
        }

        var output = new MancalaDTO(mancala, namePlayer1, namePlayer2);
        return Response.status(200).entity(output).build();
    }
}
