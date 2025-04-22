import React from "react";
import "./Card.css";

export function Card({ name }) {
  const getCardDetails = (name) => {
    if (!name) return null;

    const suit = name.slice(-1).toLowerCase();
    const rank = name.slice(0, -1).replace("T", "10");
    const suitSymbols = {
      h: "♥",
      d: "♦",
      s: "♠",
      c: "♣",
    };

    return {
      rank,
      suit: suitSymbols[suit] || "?",
    };
  };

  const cardDetails = getCardDetails(name);

  const renderCardPattern = () => {
    if (!name) return null;
    const { rank, suit } = cardDetails;

    const patterns = {
      2: [
        { x: 50, y: 20 },
        { x: 50, y: 80 },
      ],
      3: [
        { x: 50, y: 20 },
        { x: 50, y: 50 },
        { x: 50, y: 80 },
      ],
      4: [
        { x: 30, y: 20 },
        { x: 70, y: 20 },
        { x: 30, y: 80 },
        { x: 70, y: 80 },
      ],
      5: [
        { x: 30, y: 20 },
        { x: 70, y: 20 },
        { x: 50, y: 50 },
        { x: 30, y: 80 },
        { x: 70, y: 80 },
      ],
      6: [
        { x: 30, y: 20 },
        { x: 70, y: 20 },
        { x: 30, y: 50 },
        { x: 70, y: 50 },
        { x: 30, y: 80 },
        { x: 70, y: 80 },
      ],
      7: [
        { x: 30, y: 20 },
        { x: 70, y: 20 },
        { x: 30, y: 50 },
        { x: 70, y: 50 },
        { x: 30, y: 80 },
        { x: 70, y: 80 },
        { x: 50, y: 35 },
      ],
      8: [
        { x: 30, y: 20 },
        { x: 70, y: 20 },
        { x: 30, y: 50 },
        { x: 70, y: 50 },
        { x: 30, y: 80 },
        { x: 70, y: 80 },
        { x: 50, y: 35 },
        { x: 50, y: 65 },
      ],
      9: [
        { x: 30, y: 15 },
        { x: 70, y: 15 },
        { x: 30, y: 40 },
        { x: 70, y: 40 },
        { x: 30, y: 60 },
        { x: 70, y: 60 },
        { x: 30, y: 85 },
        { x: 70, y: 85 },
        { x: 50, y: 35 },
      ],
      10: [
        { x: 32, y: 15 },
        { x: 68, y: 15 },
        { x: 32, y: 40 },
        { x: 68, y: 40 },
        { x: 32, y: 60 },
        { x: 68, y: 60 },
        { x: 32, y: 85 },
        { x: 68, y: 85 },
        { x: 50, y: 30 },
        { x: 50, y: 75 },
      ],
    };

    if (patterns[rank]) {
      return (
        <div className="card-pattern">
          {patterns[rank].map((pos, index) => (
            <span
              key={index}
              className="card-symbol"
              style={{
                top: `${pos.y}%`,
                left: `${pos.x}%`,
                fontSize: `${rank === "1" ? 5 : Math.max(2, 3 - rank * 0.2)}rem`,
              }}
            >
              {suit}
            </span>
          ))}
        </div>
      );
    }

    if (["J", "Q", "K", "A"].includes(rank)) {
      return (
        <div className="face-card">
          <div
            className="face-symbol"
            style={{
              top: "50%",
              left: "50%",
              fontSize: "5rem",
            }}
          >
            {suit}
          </div>
          <div className="face-rank">{rank}</div>
        </div>
      );
    }

    return null;
  };

  return (
    <div className={`card ${name ? "" : "card-back"}`}>
      {name ? (
        <>
          <div
            className={`card-rank-top ${cardDetails.suit === "♥" || cardDetails.suit === "♦" ? "red-suit" : ""}`}
            style={{
              transform: cardDetails.rank === "10" ? "scaleX(0.4)" : "",
            }}
          >
            {cardDetails.rank}
          </div>
          <div
            className={`card-pattern ${cardDetails.suit === "♥" || cardDetails.suit === "♦" ? "red-suit" : ""}`}
          >
            {renderCardPattern()}
          </div>
          <div
            className={`card-rank-bottom ${cardDetails.suit === "♥" || cardDetails.suit === "♦" ? "red-suit" : ""}`}
            style={{
              transform:
                cardDetails.rank === "10" ? "scaleX(0.4) rotate(180deg)" : "",
            }}
          >
            {cardDetails.rank}
          </div>
        </>
      ) : (
        <div className="card-back-design">★</div>
      )}
    </div>
  );
}
