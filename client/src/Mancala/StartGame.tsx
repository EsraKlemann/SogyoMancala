import React, { useState } from "react";
import type { GameState } from "../gameState";
import "./StartGame.css";

type StartGameProps = {
    setGameState(newGameState: GameState): void;
}

/**
 * Allows the players to enter their name. A name is required for both players. They can't have the same names.
 */
export function StartGame({ setGameState }: StartGameProps) {

    const [errorMessage, setErrorMessage] = useState("");
    const [playerOne, setPlayerOne] = useState("");
    const [playerTwo, setPlayerTwo] = useState("");

    async function tryStartGame(e: React.FormEvent) {
        e.preventDefault(); // Prevent default browser behavior of submitting forms
        if (!playerOne) {
            setErrorMessage("A name is required for player 1");
            return;
        }
        if (!playerTwo) {
            setErrorMessage("A name is required for player 2");
            return;
        }
        if (playerOne === playerTwo) {
            setErrorMessage("Each player should have a unique name");
            return;
        }
        setErrorMessage("");

        try {
            const response = await fetch('mancala/api/start', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nameplayer1: playerOne, nameplayer2: playerTwo })
            });

            if (response.ok) {
                const gameState = await response.json();
                setGameState(gameState);
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }
    }

    return (
        <form onSubmit={(e) => tryStartGame(e)}>
            <div className="form__group field">
                <input required className="form__field" name="player1" id="player1" value={playerOne}
                    placeholder="Name Player 1"
                    onChange={(e) => setPlayerOne(e.target.value)}
                />
                <label htmlFor="player1" className="form__label">Player one</label>
            </div>
            <div className="form__group field">
                <input required className="form__field" name="player2" id="player2" value={playerTwo}
                    placeholder="Name Player 2"
                    onChange={(e) => setPlayerTwo(e.target.value)}
                />
                <label htmlFor="player2" className="form__label">Player two</label>
            </div>
            <p className="errorMessage">{errorMessage}</p>

            <button className="startGameButton" type="submit">
                Play Mancala!
            </button>
        </form>
    )
}
