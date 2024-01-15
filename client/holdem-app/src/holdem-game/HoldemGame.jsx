import { useState, useEffect } from "react";
import { Player } from "./player/Player";
import { Board } from "./board/Board";
import { RefreshPrompt } from "./refresh-prompt/RefreshPrompt";

export function HoldemGame() {
  const serverAddr = process.env.NODE_ENV === 'production' ? 'https://holdem-app.onrender.com' : 'http://localhost:8080';
  const [gameSessionId, setGameSessionId] = useState('');
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
  const [potValue, setPotValue] = useState(0);
  const [showDownResult, setShowDownResult] = useState('.');
  const [gameSessionStatus, setGameSessionStatus] = useState('Session expired');
  const [retryCount, setRetryCount] = useState(0);

  const E_SESSION_EXPIRED = new Error('SESSION_EXPIRED');

  const getPlayers = () => {
    if (gameSessionId.length === 0) return;
    return fetch(`${serverAddr}/players?gameSessionId=${gameSessionId}`)
      .then(response => response.json())
      .then(json => {
        console.log(json)
        setPlayerStackValues([json[0].stack, json[1].stack]);
      });
  }

  const handleError = error => {
    console.error(error);
    if (error.message === 'SESSION_EXPIRED' || true) { // at the moment assume all errors caused by session expiration
      setGameSessionStatus(gameSessionId.length > 0 ? 'Session expired' : 'Session uninitialised');
      setTimeout(() => {
        setGameSessionId('');
      }, 0);
    }
  }

  const createNewGame = () => {
    return fetch(`${serverAddr}/new-game`)
      .then(response => response.text())
      .then(newGameSessionId => {
        console.log('newGameSessionId =', newGameSessionId)
        setGameSessionId(newGameSessionId);
        return newGameSessionId;
      })
      .catch(handleError)
      .finally(() => Promise.resolve(gameSessionId));
  }

  const tryCreateNewGame = () => {
    return createNewGame().then(newGameSessionId => {
      if (newGameSessionId?.length > 0) {
        console.log('successfully created new session:', gameSessionId);
        setRetryCount(0);
      }
      else {
        setTimeout(() => {
          console.log('retrying in 2 secs.')
          console.log('newGameSessionId', newGameSessionId);
          setRetryCount(c => c + 1);
          tryCreateNewGame();
        }, 2000)
      }
    })
  }

  useEffect(() => {
    setGameSessionStatus('Session uninitialised');
    tryCreateNewGame();
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  useEffect(() => {
    console.log('gameSessionId =', gameSessionId)
    getPlayers();
    onShuffleBtnClick();
  }, [gameSessionId]); // eslint-disable-line react-hooks/exhaustive-deps

  const onShuffleBtnClick = () => {
    if (gameSessionId.length === 0) return;
    fetch(`${serverAddr}/shuffle?gameSessionId=${gameSessionId}`)
      .then(response => response.json())
      .then(json => {
        setPlayerPrivateCards([
          [json[0], json[1]],
          [json[2], json[3]]
        ]);
        setShowDownResult('');
        return onHoldemStateShouldChange();
      })
      .catch(handleError);
  }

  const updateCommunityCards = cardStage => {
    console.log('cardStage =', cardStage)
    if (cardStage === 'PRE_FLOP') {
      setCommunityCards([
        'red_back', 'red_back', 'red_back', 'red_back', 'red_back'
      ]);
    }
    else if (cardStage === 'FLOP') {
      fetch(`${serverAddr}/get-flop?gameSessionId=${gameSessionId}`)
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          setCommunityCards([...json]);
        })
        .catch(handleError);
    }
    else if (cardStage === 'TURN') {
      fetch(`${serverAddr}/deal-turn?gameSessionId=${gameSessionId}`)
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          let communityCardsTemp = [...communityCards];
          communityCardsTemp[3] = json[0];
          setCommunityCards(communityCardsTemp);
        })
        .catch(handleError);
    }
    else if (cardStage === 'RIVER') {
      fetch(`${serverAddr}/deal-river?gameSessionId=${gameSessionId}`)
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          let communityCardsTemp = [...communityCards];
          communityCardsTemp[4] = json[0];
          setCommunityCards(communityCardsTemp);
        })
        .catch(handleError);
    }
    else if (cardStage === 'SHOW_DOWN') {
      fetch(`${serverAddr}/get-community-cards?gameSessionId=${gameSessionId}`)
        .then(response => response.json())
        .then(json => {
          console.log(json, typeof json)
          setCommunityCards(json);
          onShowDown();
        })
        .catch(handleError);
    }
  }

  const givePotToWinner = showDownResult => {
    const winnderIds = JSON.parse(showDownResult[2].match(/Player (\[.+\])/)[1]);
    console.log('winnerIds is', winnderIds);
    let playerStackValuesTemp = playerStackValues;
    // handle tie
    if (winnderIds.length === 2) {
      playerStackValuesTemp[0] += potValue / 2.0;
      playerStackValuesTemp[1] += potValue / 2.0;
    }
    else {
      playerStackValuesTemp[parseInt(winnderIds[0]) - 1] += potValue;
    }
    setPotValue(0);
    setPlayerStackValues(playerStackValuesTemp);
  }

  const onShowDown = () => {
    fetch(`${serverAddr}/show-down?gameSessionId=${gameSessionId}`)
      .then(response => response.json())
      .then(json => {
        setShowDownResult(json.join('. '));
        givePotToWinner(json);
      })
      .catch(handleError);
  }

  const onHoldemStateShouldChange = () => {
    if (gameSessionId.length === 0) return;
    fetch(`${serverAddr}/get-holdem-state?gameSessionId=${gameSessionId}`)
      .then(response => response.json())
      .then(json => {
        console.log('holdem state json =', json)
        if (json.status === 404) {
          throw E_SESSION_EXPIRED;
        }
        else {
          setHoldemState(json);
        }
      })
      .catch(handleError);
  }

  useEffect(() => {
    onHoldemStateShouldChange();
  }, []) // eslint-disable-line react-hooks/exhaustive-deps

  useEffect(() => {
    console.log('holdem state =', holdemState)
    updateCommunityCards(holdemState.cardStage);
  }, [holdemState]) // eslint-disable-line react-hooks/exhaustive-deps

  const onPlayerBet = (id, betValue, isFold = false) => {
    fetch(`${serverAddr}/player-bet?gameSessionId=${gameSessionId}`, {
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
      .catch(handleError);
    if (isFold) {
      onPlayerFold(id);
    }
    else {
      setPotValue(potValue + betValue);
    }
  }

  const onPlayerFold = id => {
    const winnerId = 1 - id;
    let playerStackValuesTemp = playerStackValues;
    playerStackValuesTemp[winnerId] += potValue;
    setPotValue(0);
    setPlayerStackValues(playerStackValuesTemp);
  }

  return <>
    <div className="background">
      <div className="placeholder-top" style={{ height: '10vh' }}></div>
      <div className="holdem-game">
        <Board cardStage={holdemState.cardStage} communityCards={communityCards} />
        <div className="center-flex" style={{ 'flexDirection': 'column' }}>
          <button className="btn-shuffle" onClick={onShuffleBtnClick}>Shuffle</button>
          <div className="label-text" style={{ width: 'fit-content' }}>Potsize: {potValue}</div>
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
      <br />
      <div className="label-text" hidden={holdemState.cardStage !== 'SHOW_DOWN'} style={{ 'width': '50vw', 'margin': 'auto' }}>{showDownResult}</div>
      <div className="center-flex" style={{ 'flexDirection': 'column' }}>
        <div className="label-text" style={{ width: 'fit-content' }} hidden={true}>Player {holdemState.playerStage}'s turn</div>
        <button className="btn-next" hidden={holdemState.cardStage !== 'SHOW_DOWN'} onClick={onShuffleBtnClick}>Next</button>
      </div>
      <div style={{
        position: 'fixed',
        top: 10,
        left: 10,
        color: '#33aa33',
      }}>{gameSessionId}</div>
      {gameSessionId.length > 0 ? null : <RefreshPrompt gameSessionStatus={gameSessionStatus} retryCount={retryCount}/>}
    </div>
  </>
}