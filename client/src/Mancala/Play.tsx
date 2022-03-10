import React from "react";
import type { GameState } from "../gameState";
import "./Play.css";

type PlayProps = {
  gameState: GameState;
  setGameState(newGameState: GameState): void;
};

export function Play({ gameState, setGameState }: PlayProps) {
  async function tryMakeMove(index:number) {
    if (gameState.gameStatus.endOfGame) return;

    //check playerturn, then
    try {
      const response = await fetch("mancala/api/play", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ index: index }),
      });

      if (response.ok) {
        const gameState = await response.json();
        setGameState(gameState);
      } else {
        console.error(response.statusText);
      }
    } catch (error) {
      console.error(error);
    }
  }



  const player1 = gameState.players[0];
  const player2 = gameState.players[1];
  const player1Kalaha = player1.pits[6];
  const player2Kalaha = player2.pits[6];

  let winner = "";
  if (gameState.gameStatus.endOfGame) {
    winner = gameState.gameStatus.winner + " has won the game!";
  }

  let endGame = "";
  if (gameState.gameStatus.endOfGame) {
    endGame = "Game finished!"
  }

  let atTurn = "";
    if (player1.hasTurn) {
    atTurn = player1.name + " chooses a bowl to play"
    } if (player2.hasTurn) {
        atTurn = player2.name + " chooses a bowl to play"
    } if (gameState.gameStatus.endOfGame) {
        atTurn = ""
    }


  return (
    <div className="game">
        <p>{endGame}</p>
        <p>{winner}</p>
        <p>{atTurn}</p>
        <div className="namep2"><div className="textp2">{player2.name}</div></div>
        <div className="board">
            <div className="kalaha" id="kalahap2">{player2Kalaha.nrOfStones}</div>
            <div className="row-wrapper">
                <div className="topboard">
                {player2.pits.slice(0,-1).reverse().map(pit =>
                    <button className="bowl" id="bowlsp2" onClick={()=> { if(player2.hasTurn)tryMakeMove(pit.index)}}>{pit.nrOfStones}</button>
                )}
                </div>
                <div className="bottomboard">
                {player1.pits.slice(0,-1).map(pit =>
                    <button className="bowl" id="bowlsp1" onClick={()=> { if(player1.hasTurn) tryMakeMove(pit.index)}}>{pit.nrOfStones}</button>
                )}
                </div>
            </div>
            <div className="kalaha" id="kalahap1">{player1Kalaha.nrOfStones}</div>
        </div>
        <div className="namep1"><div className="textp1">{player1.name}</div></div>
    </div>

  );



}
