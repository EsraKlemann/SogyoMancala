import React from "react";
import { Link } from "react-router-dom";
import "./Header.css";
import urlLogo from "./logo.jpg";

/**
 * A Header component with a Sogyo logo, the name of the application and several links to different pages
 */
export function Header() {
    return <header className="main-header">
        <div>
            <img src={urlLogo} />
            Mancala
        </div>
        <div className="main-navigation">
            <Link id="playnavigation" to="/">Play</Link>
            <Link id="aboutnavigation" to="/about">About</Link>
        </div>
    </header>
}
