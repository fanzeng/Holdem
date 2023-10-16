import { useState, useEffect } from "react";
import { Player } from "./player/Player";
import { Board } from "./board/Board";

export function HoldemGame() {
  const [holdemState, setHoldemState] = useState({});
  const [playerPrivateCards, setPlayerPrivateCards] = useState([
    ['red_back', 'red_back'],
    ['red_back', 'red_back'],
  ]);
  const [communityCards, setCommunityCards] = useState([
    'red_back', 'red_back', 'red_back', 'red_back', 'red_back'
  ]);
  const [playerStackValues, setPlayerStackValues] = useState([0, 0]);
  const [playerBets, setPlayerBets] = useState([0, 0]);
  const [cardStage, setCardStage] = useState(0);
  const [potValue, setPotValue] = useState(0);
  const [showDownResult, setShowDownResult] = useState('.');


  useEffect(() => {
    fetch('http://localhost:8080/new-game')
      .then(response => response.json())
      .then(json => {
        console.log(json)
        setPlayerStackValues([json[0].stack, json[1].stack]);
        onShuffleBtnClick();
      })
      .catch(error => console.error(error));
  }, []);

  const onShuffleBtnClick = () => {
    fetch('http://localhost:8080/shuffle')
      .then(response => response.json())
      .then(json => {
        setPlayerPrivateCards([
          [json[0], json[1]],
          [json[2], json[3]]
        ]);
        setShowDownResult('');
        return onHoldemStateShouldChange();
      }).then(holdemState => {
        // console.log(holdemState.cardStage);
      })
      .catch(error => console.error(error));
  }

  const updateCommunityCards = cardStage => {
    console.log('cardStage =', cardStage)
    if (cardStage === 'PRE_FLOP') {
      setCommunityCards([
        'red_back', 'red_back', 'red_back', 'red_back', 'red_back'
      ]);
    }
    else if (cardStage === 'FLOP') {
      fetch('http://localhost:8080/get-flop')
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          setCommunityCards([...json]);
        })
        .catch(error => console.error(error));
    }
    else if (cardStage === 'TURN') {
      fetch('http://localhost:8080/deal-turn')
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          let communityCardsTemp = [...communityCards];
          communityCardsTemp[3] = json[0];
          setCommunityCards(communityCardsTemp);
        })
        .catch(error => console.error(error));
    }
    else if (cardStage === 'RIVER') {
      fetch('http://localhost:8080/deal-river')
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          let communityCardsTemp = [...communityCards];
          communityCardsTemp[4] = json[0];
          setCommunityCards(communityCardsTemp);
        })
        .catch(error => console.error(error));
    }
    else if (cardStage === 'SHOW_DOWN') {
      fetch('http://localhost:8080/get-community-cards')
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          setCommunityCards(json);
          onShowDownBtnClick();
        })
        .catch(error => console.error(error));
    }
  }

  const givePotToWinner = showDownResult => {
    const winnderId = parseInt(showDownResult[2][7]);
    console.log('winnerId is', winnderId);
    let playerStackValuesTemp = playerStackValues;
    playerStackValuesTemp[winnderId] += potValue;
    setPotValue(0);
    setPlayerStackValues(playerStackValuesTemp);
  }

  const onShowDownBtnClick = () => {
    fetch('http://localhost:8080/show-down')
      .then(response => response.json())
      .then(json => {
        setShowDownResult(json.join('. '));
        givePotToWinner(json);
      })
      .catch(error => console.error(error));
  }

  const onHoldemStateShouldChange = () => {
    fetch('http://localhost:8080/get-holdem-state')
      .then(response => response.json())
      .then(json => {
        console.log('holdem state json =', json)
        setHoldemState(json);
      })
      .catch(error => console.error(error));
  }

  useEffect(() => {
    onHoldemStateShouldChange();
  }, [])

  useEffect(() => {
    console.log('holdem state =', holdemState)
    updateCommunityCards(holdemState.cardStage);
  }, [holdemState])

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
        setPlayerBets([json[0].betValue, json[1].betValue]);
        onHoldemStateShouldChange();
      })
      .catch(error => console.error(error));
    setPotValue(potValue + betValue);
  }

  return <>
    <div className="background">
      <div className="placeholder-top" style={{height: '10vh'}}></div>
      <div className="holdem-game">
        <Board cardStage={holdemState.cardStage} communityCards={communityCards} />
        <div className="center-flex" style={{ 'flex-direction': 'column' }}>
          <button className="btn-shuffle" onClick={onShuffleBtnClick}>Shuffle</button>
          <div className="label-text" style={{ width: 'fit-content'}}>Potsize: {potValue}</div>
        </div>
      </div>
      <Player key="0" id="0"
        enable={holdemState.cardStage !== 'SHOW_DOWN' && holdemState.playerStage === 0}
        onPlayerBet={onPlayerBet}
        privateCards={playerPrivateCards[0]}
        stackValue={playerStackValues[0]}
        setStackValue={stackValue => {
          let playerStackValuesTemp = playerStackValues;
          playerStackValues[0] = stackValue;
          setPlayerStackValues(playerStackValuesTemp);
        }}
        currentBet={playerBets[1]}
      /><br />
      <Player key="1" id="1"
        enable={holdemState.cardStage !== 'SHOW_DOWN' && holdemState.playerStage === 1}
        onPlayerBet={onPlayerBet}
        privateCards={playerPrivateCards[1]}
        stackValue={playerStackValues[1]}
        setStackValue={stackValue => {
          let playerStackValuesTemp = playerStackValues;
          playerStackValues[1] = stackValue;
          setPlayerStackValues(playerStackValuesTemp);
        }}
        currentBet={playerBets[0]}
      />
      <div className="label-text" hidden={holdemState.cardStage !== 'SHOW_DOWN'}>Show Down: {showDownResult}</div>
      {/* <button onClick={onShowDownBtnClick}>Shown Down</button> */}
      <div className="center-flex" style={{ 'flex-direction': 'column' }}>
        <div className="label-text" style={{ width: 'fit-content' }} hidden="true">Player {holdemState.playerStage}'s turn</div>
        <button className="btn-next" disabled={holdemState.cardStage !== 'SHOW_DOWN'} onClick={onShuffleBtnClick}>Next</button>
      </div>
    </div>
  </>
}