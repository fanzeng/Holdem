import { Player } from "./player/Player";
import { Board } from "./board/Board";
import { useState, useEffect } from "react";

export function HoldemGame() {
  const [holdemState, setHoldemState] = useState({});
  const [playerPrivateCards, setPlayerPrivateCards] = useState([
    ['red_back', 'red_back'],
    ['red_back', 'red_back'],
  ]);
  const [playerStackValues, setPlayerStackValues] = useState([20, 19]);
  const [cardStage, setCardStage] = useState(0);
  const [potValue, setPotValue] = useState(100);
  const [showDownResult, setShowDownResult] = useState('.');


  useEffect(() => {
    fetch('http://localhost:8080/new-game')
      .then(response => response.json())
      .then(json => {
        console.log(json)
        setPlayerStackValues([json[0].stack, json[1].stack]);
      })
      .catch(error => console.error(error));
    fetch('http://localhost:8080/shuffle')
      .then(response => response.json())
      .then(json => {
        setPlayerPrivateCards([
          [json[0], json[1]],
          [json[2], json[3]]
        ]);
      })
      .catch(error => console.error(error));
  }, []);

  const onShowDownBtnClick = () => {
    fetch('http://localhost:8080/show-down')
      .then(response => response.json())
      .then(json => setShowDownResult(json))
      .catch(error => console.error(error));
  }

  const onHoldemStateChange = () => {
    fetch('http://localhost:8080/next-holdem-state')
      .then(response => response.json())
      .then(json => {
        console.log('holdem state =', json)
        setHoldemState(json)
      })
      .catch(error => console.error(error));
  }

  useEffect(() => {
    onHoldemStateChange();
  }, [playerStackValues, potValue])

  const onPlayerBet = (id, betValue) => {
    fetch('http://localhost:8080/player-bet', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify({
        "id": id,
        "betValue": betValue
      })
    })
      .then(response => response.json())
      .then(json => {
        console.log('player bets =', json)
        setHoldemState(json)
      })
      .catch(error => console.error(error));
    setPotValue(potValue + betValue);
  }

  return <>
    <div className="background">
      <div className="holdem-game">
        <Board cardStage={holdemState.cardStage} />
        <button>Shuffle</button>
        <div className="label-text">Potsize: {potValue}</div>
      </div>
      <Player key="0" id="0"
        onPlayerBet={onPlayerBet}
        privateCards={playerPrivateCards[0]}
        stackValue={playerStackValues[0]}
      /><br />
      <Player key="1" id="1"
        onPlayerBet={onPlayerBet}
        privateCards={playerPrivateCards[1]}
        stackValue={playerStackValues[1]}
      />
      <div className="label-text">Show Down: {showDownResult}</div>
      <button onClick={onShowDownBtnClick}>Shown Down</button>
      <div className="label-text">Player {holdemState.playerStage}'s turn</div>
      <button onClick={onHoldemStateChange}>Next</button>
    </div>
  </>
}