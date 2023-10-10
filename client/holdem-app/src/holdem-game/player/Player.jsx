import { Card } from "../../card/Card";

export function Player({ id, onPlayerBet, privateCards, stackValue }) {
  const bb = 10;
  function onRaiseBtnClick() {
    onPlayerBet(id, bb*2);
  }
  return <>
    <span className="label-text">Player {id} &nbsp;</span>
    <span className="label-text">Stack: {stackValue}&nbsp;</span>
    <button onClick={onRaiseBtnClick}>Raise&nbsp;</button>
    <button onClick={() => onPlayerBet(id, bb)}>Call&nbsp;</button>
    <button onClick={() => onPlayerBet(id, 0)}>Fold</button>
    <div className="center-flex">
      <Card key="0" name={privateCards[0]} />
      <Card key="1" name={privateCards[1]} />
    </div>
  </>
}